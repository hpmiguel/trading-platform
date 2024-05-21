package com.example.service.trading.application.ports.api.security;

import com.example.service.trading.infrastructure.adapters.api.models.security.SaveSecurityBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.security.SecurityDto;

public interface ChangeSecurityEndpointPort {

    SecurityDto saveSecurity(SaveSecurityBodyDto saveSecurityBodyDto);

    SecurityDto updateSecurity(Integer id, SaveSecurityBodyDto saveSecurityBodyDto);

    void deleteSecurity(Integer securityId);

}
