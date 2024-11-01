package com.project.aegis.serviceImpl;

import com.project.aegis.model.Result;
import com.project.aegis.model.Transaksi;
import com.project.aegis.model.TransaksiDetail;
import com.project.aegis.repository.ProdukRepository;
import com.project.aegis.repository.TransaksiRepository;
import com.project.aegis.request.TransaksiDetailRequest;
import com.project.aegis.request.TransaksiRequest;
import com.project.aegis.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    private TransaksiRepository repository;

    @Autowired
    private ProdukRepository produkRepository;

    @Override
    public ResponseEntity<Result> insertTransaksi(TransaksiRequest request) {

        String uuid = UUID.randomUUID().toString();

        Transaksi transaksi = new Transaksi(uuid, setTransaksiDetail(uuid, request.getOrderDetails()), request.getSubtotal(),
        request.getDiscount(), request.getTotal(), request.getPayment(), request.getChanges(), request.getCreatedBy());

        repository.save(transaksi);
        updateStock(transaksi.getOrderDetails());

        return ResponseEntity.ok(new Result());
    }

    private List<TransaksiDetail> setTransaksiDetail(String uuid, List<TransaksiDetailRequest> transaksiDetailRequestList){
        List<TransaksiDetail> transaksiDetails = new ArrayList<>();
        transaksiDetailRequestList.forEach(transaksiDetailRequest -> {
            transaksiDetails.add(new TransaksiDetail(uuid, transaksiDetailRequest.getIdProduk(), transaksiDetailRequest.getName(),
                    transaksiDetailRequest.getPrice(), transaksiDetailRequest.getQty()));

        });
        return transaksiDetails;
    }

    private void updateStock(List<TransaksiDetail> transaksiDetailList){
        transaksiDetailList.forEach(transaksiDetail -> produkRepository.decreaseStock(transaksiDetail.getQty(), transaksiDetail.getIdProduk()));
    }
}
