package com.example.service.trading.infrastructure.adapters.api.security;

import com.example.service.trading.application.ports.api.security.FindSecurityEndpointPort;
import com.example.service.trading.application.usecases.security.FindAllSecuritiesUseCase;
import com.example.service.trading.application.usecases.security.FindSecurityByIdUseCase;
import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.adapters.api.mappers.SecurityDtoMapper;
import com.example.service.trading.infrastructure.adapters.api.models.security.SecurityDto;
import com.example.service.trading.infrastructure.annotations.Adapter;

import java.util.Collection;
import java.util.stream.Collectors;

@Adapter
public class FindSecurityEndpointAdapter implements FindSecurityEndpointPort {

    private final FindAllSecuritiesUseCase findAllSecuritiesUseCase;

    private final FindSecurityByIdUseCase findSecurityByIdUseCase;

    private final SecurityDtoMapper securityDtoMapper;

    FindSecurityEndpointAdapter(FindAllSecuritiesUseCase findAllSecuritiesUseCase,
                                FindSecurityByIdUseCase findSecurityByIdUseCase,
                                SecurityDtoMapper securityDtoMapper) {
        this.findAllSecuritiesUseCase = findAllSecuritiesUseCase;
        this.findSecurityByIdUseCase = findSecurityByIdUseCase;
        this.securityDtoMapper = securityDtoMapper;
    }

    @Override
    public SecurityDto fetchSecurityById(Integer securityId) {
        Security foundSecurity = findSecurityByIdUseCase.findById(securityId);
        return securityDtoMapper.toDto(foundSecurity);
    }

    @Override
    public Collection<SecurityDto> fetchAllSecurities() {
        return findAllSecuritiesUseCase.fetchAllPersisted()
                .stream()
                .map(securityDtoMapper::toDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
