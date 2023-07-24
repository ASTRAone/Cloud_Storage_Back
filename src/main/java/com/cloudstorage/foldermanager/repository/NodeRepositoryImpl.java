package com.cloudstorage.foldermanager.repository;

import com.cloudstorage.foldermanager.model.FolderNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Repository
@RequiredArgsConstructor
public class NodeRepositoryImpl implements NodeGraphLookUpRepository {
    private static final long MAX_DEPTH_SUPPORTED = 10000L;

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<List<FolderNode>> getSubTree(String nodeUuid, String rootUuid, Long maxDepth) {
        final Criteria byNodeId = new Criteria("uuid").is(nodeUuid);
        final Criteria byTreeId = new Criteria("rootUuid").is(rootUuid);

        final MatchOperation matchStage = Aggregation.match(byTreeId.andOperator(byNodeId));


        GraphLookupOperation graphLookupOperation = GraphLookupOperation.builder()
                .from("folders")
                .startWith("$uuid")
                .connectFrom("uuid")
                .connectTo("parentUuid")
                .restrict(new Criteria("rootUuid").is(rootUuid))
                .maxDepth(isNull(maxDepth) ? MAX_DEPTH_SUPPORTED : maxDepth)
                .as("nestedFolders");
        Aggregation aggregation = Aggregation.newAggregation(matchStage, graphLookupOperation);
        List<FolderNode> folders = mongoTemplate.aggregate(aggregation, "folders", FolderNode.class).getMappedResults();
        return Optional.of(folders);
    }
}
