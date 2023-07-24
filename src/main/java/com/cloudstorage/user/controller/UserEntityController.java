package com.cloudstorage.user.controller;

import com.cloudstorage.common.controller.impl.BaseCrudController;
import com.cloudstorage.common.service.api.CrudService;
import com.cloudstorage.user.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "user-api", description = "Public user crud api")
public class UserEntityController extends BaseCrudController<UserDto> {
    protected UserEntityController(CrudService<UserDto> service) {
        super(service);
    }
}
