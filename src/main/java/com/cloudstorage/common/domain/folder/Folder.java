package com.cloudstorage.common.domain.folder;

import com.cloudstorage.common.domain.enumeration.DeleteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Folder {
    private String name;
    private Integer itemsQuantity;
    private String userUuid;
    private Size itemsSize;
    private ShareParameters shareParameters;
    private DeleteStatus deleteStatus;
}
