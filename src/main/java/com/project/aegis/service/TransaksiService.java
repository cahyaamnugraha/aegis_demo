package com.project.aegis.service;

import com.project.aegis.model.Result;
import com.project.aegis.request.TransaksiRequest;
import org.springframework.http.ResponseEntity;

public interface TransaksiService {

  ResponseEntity<Result> insertTransaksi(TransaksiRequest request);

}
