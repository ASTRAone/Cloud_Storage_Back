package com.cloudstorage.user.dto;

import com.cloudstorage.common.domain.user.FIO;
import com.cloudstorage.common.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Identifiable {
    private String uuid;
    private String email;
    private FIO fio;
    private Integer age;
    private String avatarS3Uuid;
}
