package com.example.service.trading.infrastructure.adapters.api.mappers;

import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.adapters.api.models.security.SaveSecurityBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.security.SecurityDto;
import com.example.service.trading.infrastructure.annotations.Mapper;

@Mapper
public class SecurityDtoMapper {

    SecurityDtoMapper() {
        super();
    }

    public SecurityDto toDto(Security security) {
        return SecurityDto.builder()
                .name(security.getName())
                .build();
    }

    public Security toDomainFromSaveBody(SaveSecurityBodyDto saveSecurityBodyDto) {
        return Security.builder()
                .name(saveSecurityBodyDto.getName())
                .build();
    }

    public Security toDomainFromSaveBody(Integer securityId, SaveSecurityBodyDto saveSecurityBodyDto) {
        return Security.builder()
                .id(securityId)
                .name(saveSecurityBodyDto.getName())
                .build();
    }
}
