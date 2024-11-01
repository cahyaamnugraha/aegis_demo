package com.project.aegis.service;

import com.project.aegis.model.Result;
import com.project.aegis.request.ReturRequest;
import org.springframework.http.ResponseEntity;

public interface ReturService {

  ResponseEntity<Result> insertRetur(ReturRequest request);

}
