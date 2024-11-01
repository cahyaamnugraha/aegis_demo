package com.project.aegis.controller;

import com.project.aegis.model.Result;
import com.project.aegis.request.ReturRequest;
import com.project.aegis.service.ReturService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/retur")
public class ReturController {

    @Autowired
    private ReturService service;

    @PostMapping("/insert-retur")
    @ResponseBody
    @ApiOperation(value = "insert data retur")
    public ResponseEntity<Result> insert(@RequestBody ReturRequest request) {
        return service.insertRetur(request);
    }

}
