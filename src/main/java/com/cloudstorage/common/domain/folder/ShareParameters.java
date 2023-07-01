package com.cloudstorage.common.domain.folder;

import com.cloudstorage.common.domain.enumeration.ShareScope;
import com.cloudstorage.common.domain.enumeration.ShareStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareParameters {
    private ShareStatus shareStatus;
    private ShareScope shareScope;

    @Nullable
    private List<String> sharedUserUuids;

    @Nullable
    private String shareLink;
}
