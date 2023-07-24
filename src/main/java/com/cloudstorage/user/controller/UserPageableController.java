package com.cloudstorage.user.controller;

import com.cloudstorage.common.controller.impl.BasePageableController;
import com.cloudstorage.user.dto.UserDto;
import com.cloudstorage.user.service.pagable.UserPageableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/page")
@Tag(name = "user-pageable-api", description = "Public user pageable api")
public class UserPageableController extends BasePageableController<UserDto> {
    protected UserPageableController(UserPageableService service) {
        super(service);
    }
}
