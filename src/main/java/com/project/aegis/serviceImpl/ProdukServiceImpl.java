package com.project.aegis.serviceImpl;

import com.project.aegis.model.Produk;
import com.project.aegis.model.Result;
import com.project.aegis.repository.ProdukRepository;
import com.project.aegis.service.ProdukService;
import com.project.aegis.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProdukServiceImpl implements ProdukService {

  @Autowired
  ProdukRepository repository;

  @Autowired
  StringUtil stringUtil;
  
  private Result result;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public Page<Produk> findByParameter(String keyword, int pageNumber, int pageSize, String sortBy, String orderBy) {
    return repository.findByParameter(keyword,
            PageRequest.of(pageNumber, pageSize, orderBy.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()));
  }
}
