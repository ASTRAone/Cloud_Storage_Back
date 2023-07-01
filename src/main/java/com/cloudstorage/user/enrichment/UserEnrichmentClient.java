package com.cloudstorage.user.enrichment;

import com.cloudstorage.enrichment.EnrichmentClient;
import com.cloudstorage.enrichment.enumeration.CommonEnrichmentType;
import com.cloudstorage.enrichment.model.IdentityEnrichmentType;
import com.cloudstorage.user.dto.UserDto;
import com.cloudstorage.user.service.crud.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserEnrichmentClient implements EnrichmentClient<UserDto> {
    private final UserService userService;

    @Override
    public boolean support(IdentityEnrichmentType type) {
        return CommonEnrichmentType.USER.equals(type);
    }

    @Override
    public List<UserDto> fetch(Collection<String> uuids) {
        return userService.findByUuids(uuids);
    }
}
