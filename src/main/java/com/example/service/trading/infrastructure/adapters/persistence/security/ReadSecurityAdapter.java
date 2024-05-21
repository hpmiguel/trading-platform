package com.example.service.trading.infrastructure.adapters.persistence.security;

import com.example.service.trading.application.ports.persistence.security.ReadSecurityPort;
import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.adapters.persistence.mappers.SecurityJpaMapper;
import com.example.service.trading.infrastructure.annotations.Adapter;
import com.example.service.trading.infrastructure.repositories.SecurityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
class ReadSecurityAdapter implements ReadSecurityPort {

    private final SecurityRepository securityRepository;

    private final SecurityJpaMapper securityJpaMapper;

    public ReadSecurityAdapter(SecurityRepository securityRepository, SecurityJpaMapper securityJpaMapper) {
        this.securityRepository = securityRepository;
        this.securityJpaMapper = securityJpaMapper;
    }

    @Override
    public Boolean existsSecurityByName(Security security) {
        return !securityRepository.findByName(security.getName())
                .isEmpty();
    }

    @Override
    public Boolean existsSecurityById(Integer securityId) {
        return securityRepository.existsById(securityId);
    }

    @Override
    public Optional<Security> fetchById(Integer securityId) {
        return securityRepository.findById(securityId)
                .map(securityJpaMapper::toDomain);
    }

    @Override
    public List<Security> fetchAll() {
        return securityRepository.findAll()
                .stream()
                .map(securityJpaMapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }
}
