package com.project.aegis.service;

import com.project.aegis.model.Produk;
import org.springframework.data.domain.Page;

public interface ProdukService {

  Page<Produk> findByParameter(String keyword, int pageNumber, int pageSize, String sortBy, String orderBy);

}
