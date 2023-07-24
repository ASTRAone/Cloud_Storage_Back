package com.cloudstorage.foldermanager.repository;

import com.cloudstorage.foldermanager.model.FolderNode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NodeRepository extends MongoRepository<FolderNode, Object>, NodeGraphLookUpRepository {
    Optional<List<FolderNode>> findDistinctByNodeUuid(String nodeUuid);
}
