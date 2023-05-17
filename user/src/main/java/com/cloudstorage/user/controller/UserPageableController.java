package com.cloudstorage.user.controller;

import com.cloudstorage.common.controller.impl.BasePageableController;
import com.cloudstorage.user.api.dto.UserDto;
import com.cloudstorage.user.service.pagable.UserPageableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/page")
public class UserPageableController extends BasePageableController<UserDto> {
    protected UserPageableController(UserPageableService service) {
        super(service);
    }
}
