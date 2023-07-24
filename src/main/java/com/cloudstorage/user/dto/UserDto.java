package com.cloudstorage.user.dto;

import com.cloudstorage.common.domain.user.FIO;
import com.cloudstorage.common.model.Identifiable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "user")
public class UserDto implements Identifiable {
    private String uuid;
    private String email;
    private FIO fio;
    private Integer age;
    private String avatarS3Uuid;
}
