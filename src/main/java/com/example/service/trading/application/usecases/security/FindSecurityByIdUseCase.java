package com.example.service.trading.application.usecases.security;

import com.example.service.trading.domain.security.Security;

public interface FindSecurityByIdUseCase {

    Security findById(Integer securityId);
}
