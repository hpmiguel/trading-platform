package com.example.service.trading.infrastructure.repositories;

import com.example.service.trading.infrastructure.adapters.persistence.models.SecurityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityData, Integer> {

    Collection<SecurityData> findByName(String name);
}
