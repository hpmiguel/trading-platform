package com.example.service.trading.infrastructure.adapters.api.security;

import com.example.service.trading.application.ports.api.security.ChangeSecurityEndpointPort;
import com.example.service.trading.application.usecases.security.ChangeExistingSecurityUseCase;
import com.example.service.trading.application.usecases.security.DeleteSecurityByIdUseCase;
import com.example.service.trading.application.usecases.security.SubmitNewSecurityUseCase;
import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.adapters.api.mappers.SecurityDtoMapper;
import com.example.service.trading.infrastructure.adapters.api.models.security.SaveSecurityBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.security.SecurityDto;
import com.example.service.trading.infrastructure.annotations.Adapter;

@Adapter
public class ChangeSecurityEndpointAdapter implements ChangeSecurityEndpointPort {
    private final SubmitNewSecurityUseCase submitNewSecurityUseCase;

    private final ChangeExistingSecurityUseCase changeExistingSecurityUseCase;

    private final DeleteSecurityByIdUseCase deleteSecuritiesByIdUseCase;

    private final SecurityDtoMapper securityDtoMapper;

    ChangeSecurityEndpointAdapter(SubmitNewSecurityUseCase submitNewSecurityUseCase,
                              ChangeExistingSecurityUseCase changeExistingSecurityUseCase,
                              DeleteSecurityByIdUseCase deleteSecuritiesByIdUseCase,
                              SecurityDtoMapper securityDtoMapper) {
        this.submitNewSecurityUseCase = submitNewSecurityUseCase;
        this.changeExistingSecurityUseCase = changeExistingSecurityUseCase;
        this.deleteSecuritiesByIdUseCase = deleteSecuritiesByIdUseCase;
        this.securityDtoMapper = securityDtoMapper;
    }

    @Override
    public SecurityDto saveSecurity(SaveSecurityBodyDto saveSecurityBodyDto) {
        Security security = securityDtoMapper.toDomainFromSaveBody(saveSecurityBodyDto);
        Security savedSecurity = submitNewSecurityUseCase.saveSecurity(security);
        return securityDtoMapper.toDto(savedSecurity);
    }

    @Override
    public SecurityDto updateSecurity(Integer id, SaveSecurityBodyDto saveSecurityBodyDto) {
        Security security = securityDtoMapper.toDomainFromSaveBody(id, saveSecurityBodyDto);
        Security updatedSecurity = changeExistingSecurityUseCase.updateSecurity(security);
        return securityDtoMapper.toDto(updatedSecurity);
    }


    @Override
    public void deleteSecurity(Integer securityId) {
        deleteSecuritiesByIdUseCase.deleteById(securityId);
    }

}
