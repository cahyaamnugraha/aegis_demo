package com.project.aegis.initializer;

import com.project.aegis.model.Produk;
import com.project.aegis.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProdukSeeder implements Ordered {
    @Autowired
    private ProdukRepository repository;

    @Override
    public int getOrder() {
        return 3; // Specify the order of execution
    }

    @PostConstruct
    public void seed() {
        if (repository.count() == 0) {
            repository.save(new Produk(1L, "Macbook M1", 10000000.0, 5, "admin"));
            repository.save(new Produk(2L, "Macbook M1 Pro", 15000000.0, 7, "admin"));
            repository.save(new Produk(3L, "Macbook M2", 14000000.0, 5, "admin"));
            repository.save(new Produk(4L, "Macbook M2 Pro", 19000000.0, 7, "admin"));
        }
    }
}
