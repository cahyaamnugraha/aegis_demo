package com.project.aegis.repository;

import com.project.aegis.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, String> {

//    @Query(value = "SELECT * FROM produk WHERE (:keyword is null OR ("
//            + "LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))))",
//            countQuery = "SELECT * FROM produk WHERE (:keyword is null OR ("
//                    + "LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))))",
//            nativeQuery = true)
//    public Page<Produk> findByParameter(
//            @Param("keyword") String keyword,
//            @Param("pageable") Pageable pageable
//    );

    @Query(value = "SELECT t FROM Transaksi t WHERE "
            + "(:startTime is null or t.createdAt >= :startTime) "
            + "and (:endTime is null or t.createdAt <= :endTime)")
    public List<Transaksi> findByRange(
            @Param("startTime") Timestamp startTime,
            @Param("endTime") Timestamp endTime
    );
}
