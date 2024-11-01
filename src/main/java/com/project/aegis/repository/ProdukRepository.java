package com.project.aegis.repository;

import com.project.aegis.model.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Long> {

    @Query(value = "SELECT * FROM produk WHERE (:keyword is null OR ("
            + "LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))))",
            countQuery = "SELECT * FROM produk WHERE (:keyword is null OR ("
                    + "LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))))",
            nativeQuery = true)
    public Page<Produk> findByParameter(
            @Param("keyword") String keyword,
            @Param("pageable") Pageable pageable
    );

    @Modifying
    @Transactional
    @Query("update Produk p set p.stock = stock + ?1 where p.id = ?2")
    void increaseStock(int qty, Long id);

    @Modifying
    @Transactional
    @Query("update Produk p set p.stock = stock - ?1 where p.id = ?2")
    void decreaseStock(int qty, Long id);
}
