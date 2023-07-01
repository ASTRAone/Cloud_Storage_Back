package com.cloudstorage.common.domain.user;

import com.cloudstorage.common.domain.folder.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStorage {
    private Size maxSize;
    private Size currentSize;
    private Size leftSize;
    private Integer countOfFiles;
}
