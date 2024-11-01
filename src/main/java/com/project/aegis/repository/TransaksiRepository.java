package com.project.aegis.repository;

import com.project.aegis.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, String> {

}
