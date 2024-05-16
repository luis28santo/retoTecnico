package com.org.lasantos.api.fast.repositories;

import com.org.lasantos.api.fast.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByDocumentNumber(String documentNumber);

    @Query(value = "SELECT CASE " +
            "WHEN count(*) > 0 THEN true " +
            "WHEN count(*) = 0 THEN false " +
            "END FROM CLIENTES " +
            "WHERE DOCUMENT_NUMBER = (:documentNumber) " +
            "AND ID <> (:id)", nativeQuery = true)
    Boolean existClientByDocumentNumberAndDifferentId(String documentNumber, String id);

}
