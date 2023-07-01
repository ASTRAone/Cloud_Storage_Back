package com.cloudstorage.common.domain.folder;

import com.cloudstorage.common.domain.enumeration.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {
    private String name;
    private String folderUuid;
    private Size fileSize;
    private FileType fileType;
}
