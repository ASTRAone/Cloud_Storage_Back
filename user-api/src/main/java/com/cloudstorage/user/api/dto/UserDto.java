package com.cloudstorage.user.api.dto;

import com.cloudstorage.common.domain.user.FIO;
import com.cloudstorage.common.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Identifiable {
    private String uuid;
    private FIO fio;
    private String nickname;
    private Integer age;
    private String avatarS3Uuid;
}
