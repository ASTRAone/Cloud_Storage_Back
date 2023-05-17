package com.cloudstorage.user.controller;

import com.cloudstorage.common.controller.impl.BaseCrudController;
import com.cloudstorage.common.service.api.CrudService;
import com.cloudstorage.user.api.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserEntityController extends BaseCrudController<UserDto> {
    protected UserEntityController(CrudService<UserDto> service) {
        super(service);
    }
}
