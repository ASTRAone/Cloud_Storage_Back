package com.cloudstorage.common.domain.folder;

import com.cloudstorage.common.domain.enumeration.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Size {
    private Unit unit;
    private Double size;
}
