package com.project.aegis.controller;

import com.project.aegis.model.Result;
import com.project.aegis.request.TransaksiRequest;
import com.project.aegis.service.TransaksiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService service;

    @PostMapping("/insert-transaksi")
    @ResponseBody
    @ApiOperation(value = "insert data transaksi")
    public ResponseEntity<Result> insert(@RequestBody TransaksiRequest request) {
        return service.insertTransaksi(request);
    }

}
