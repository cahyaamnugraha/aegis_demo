package com.project.aegis.repository;

import com.project.aegis.model.TransaksiDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiDetailRepository extends JpaRepository<TransaksiDetail, String> {

    List<TransaksiDetail> findByIdTransaksi(String idTransaksi);

//    @Query(value = "SELECT * FROM produk WHERE (:keyword is null OR ("
//            + "LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))))",
//            countQuery = "SELECT * FROM produk WHERE (:keyword is null OR ("
//                    + "LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))))",
//            nativeQuery = true)
//    public Page<Produk> findByParameter(
//            @Param("keyword") String keyword,
//            @Param("pageable") Pageable pageable
//    );


}
