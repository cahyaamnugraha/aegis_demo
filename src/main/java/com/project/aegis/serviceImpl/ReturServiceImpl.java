package com.project.aegis.serviceImpl;

import com.project.aegis.model.*;
import com.project.aegis.repository.ProdukRepository;
import com.project.aegis.repository.ReturRepository;
import com.project.aegis.repository.TransaksiDetailRepository;
import com.project.aegis.repository.TransaksiRepository;
import com.project.aegis.request.ReturRequest;
import com.project.aegis.service.ReturService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReturServiceImpl implements ReturService {

    @Autowired
    private TransaksiRepository repository;

    @Autowired
    private TransaksiDetailRepository transaksiDetailRepository;

    @Autowired
    private ReturRepository returRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Override
    public ResponseEntity<Result> insertRetur(ReturRequest request) {

        String uuid = UUID.randomUUID().toString();

        Retur retur = returRepository.save(new Retur(uuid, new Transaksi(request.getIdTransaksi()), request.getCreatedBy()));
        updateStock(retur.getTransaksi().getId());

        return ResponseEntity.ok(new Result());
    }


    private void updateStock(String idTransaksi) {
        List<TransaksiDetail> produkList = transaksiDetailRepository.findByIdTransaksi(idTransaksi);
        if (!produkList.isEmpty()) {
            produkList.forEach(transaksiDetail -> produkRepository.increaseStock(transaksiDetail.getQty(), transaksiDetail.getIdProduk()));
        }

    }
}
