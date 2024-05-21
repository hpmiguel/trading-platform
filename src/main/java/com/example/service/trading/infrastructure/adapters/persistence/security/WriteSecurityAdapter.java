package com.example.service.trading.infrastructure.adapters.persistence.security;

import com.example.service.trading.application.ports.persistence.security.WriteSecurityPort;
import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.adapters.persistence.mappers.SecurityJpaMapper;
import com.example.service.trading.infrastructure.adapters.persistence.models.SecurityData;
import com.example.service.trading.infrastructure.annotations.Adapter;
import com.example.service.trading.infrastructure.repositories.SecurityRepository;

import java.util.Optional;


@Adapter
class WriteSecurityAdapter implements WriteSecurityPort {

    private final SecurityRepository securityRepository;

    private final SecurityJpaMapper securityJpaMapper;

    public WriteSecurityAdapter(SecurityRepository securityRepository, SecurityJpaMapper securityJpaMapper) {
        this.securityRepository = securityRepository;
        this.securityJpaMapper = securityJpaMapper;
    }

    @Override
    public Security saveNew(Security security) {
        SecurityData securityData = securityJpaMapper.toJpaEntity(security);
        SecurityData securitySaved = securityRepository.save(securityData);
        return securityJpaMapper.toDomain(securitySaved);
    }

    @Override
    public Optional<Security> update(Security security) {
        return securityRepository.findById(security.getId())
                .map(persistedSecurityData -> securityJpaMapper.toJpaEntity(security, persistedSecurityData))
                .map(securityRepository::save)
                .map(securityJpaMapper::toDomain);
    }

    @Override
    public void deleteById(Integer securityId) {
        securityRepository.deleteById(securityId);
    }


}
