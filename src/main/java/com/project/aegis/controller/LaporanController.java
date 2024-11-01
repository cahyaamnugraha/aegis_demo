package com.project.aegis.controller;

import com.project.aegis.serviceImpl.LaporanServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/transaksi")
public class LaporanController {

    @Autowired
    private LaporanServiceImpl laporanService;

    @PostMapping("/create-laporan-transaksi")
    @ResponseBody
    @ApiOperation(value = "create laporan transaksi")
    public ResponseEntity<Resource> createLaporanTransaksi(
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime
    ) {
        return laporanService.createLaporanTransaksi(startTime, endTime);
    }

}
