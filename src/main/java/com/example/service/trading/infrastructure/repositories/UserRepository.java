package com.example.service.trading.infrastructure.repositories;

import com.example.service.trading.infrastructure.adapters.persistence.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {

    Collection<UserData> findByUsername(String username);
}
