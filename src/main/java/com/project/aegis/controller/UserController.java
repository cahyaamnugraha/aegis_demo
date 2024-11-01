package com.project.aegis.controller;

import com.project.aegis.model.Result;
import com.project.aegis.service.UserService;
import com.project.aegis.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    StringUtil stringUtil;

    @Autowired
    HttpServletRequest request;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/set-active")
    public Result active(
            @RequestParam(value = "id", defaultValue = "0", required = true) long id,
            @RequestParam(value = "isActive", defaultValue = "true", required = true) boolean isActive
    ) {
        String uri = stringUtil.getLogParam(request);
        logger.info(uri);

        return service.active(id, isActive, uri);
    }
}
