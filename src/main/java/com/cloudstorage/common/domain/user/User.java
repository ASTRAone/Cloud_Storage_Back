package com.cloudstorage.common.domain.user;

import com.cloudstorage.common.domain.enumeration.Gender;
import lombok.Data;

@Data
public class User {
    private FIO fio;
    private Gender gender;
    private String email;
    private Integer age;
    private String avatarUuid;
    private UserStorage userStorage;
    private String phoneNumber;
    private String BIO;
    private String address;
}
