package com.project.aegis.controller;


import com.project.aegis.model.Produk;
import com.project.aegis.service.ProdukService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private ProdukService service;

    @GetMapping("/get-produk")
    @ResponseBody
    @ApiOperation(value = "Get list of produk")
    public Page<Produk> getListProduk(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "created_at", required = false) String sortBy,
            @RequestParam(value = "orderBy", defaultValue = "desc", required = false) String orderBy
    ) {
        return service.findByParameter(keyword, pageNumber, pageSize, sortBy, orderBy);
    }
}
