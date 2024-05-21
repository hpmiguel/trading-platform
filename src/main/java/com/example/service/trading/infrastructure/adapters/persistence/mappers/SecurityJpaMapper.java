package com.example.service.trading.infrastructure.adapters.persistence.mappers;

import com.example.service.trading.domain.security.Security;
import com.example.service.trading.infrastructure.adapters.persistence.models.SecurityData;
import com.example.service.trading.infrastructure.annotations.Mapper;

@Mapper
public class SecurityJpaMapper {

    SecurityJpaMapper() {
        super();
    }

    public SecurityData toJpaEntity(Security security) {
        return SecurityData.builder()
                .id(security.getId())
                .name(security.getName())
                .build();
    }

    public SecurityData toJpaEntity(Security security, SecurityData persistedSecurityData) {
        return persistedSecurityData.toBuilder()
                .name(security.getName())
                .build();
    }

    public Security toDomain(SecurityData securityData) {
        return Security.builder()
                .id(securityData.getId())
                .name(securityData.getName())
                .build();
    }

}
