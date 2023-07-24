package com.cloudstorage.foldermanager.model;

import com.cloudstorage.common.domain.folder.Folder;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "folders")
public class FolderNode {
    @Id
    private String uuid;
    private String nodeUuid;
    private Folder data;
    private String parentUuid;
    private String rootUuid;

}
