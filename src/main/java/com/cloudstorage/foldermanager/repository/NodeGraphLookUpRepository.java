package com.cloudstorage.foldermanager.repository;

import com.cloudstorage.foldermanager.model.FolderNode;

import java.util.List;
import java.util.Optional;

public interface NodeGraphLookUpRepository {
    Optional<List<FolderNode>> getSubTree(String nodeUuid, String treeUuid, Long maxDepth);
}
