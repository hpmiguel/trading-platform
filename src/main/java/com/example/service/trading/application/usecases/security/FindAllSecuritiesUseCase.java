package com.example.service.trading.application.usecases.security;

import com.example.service.trading.domain.security.Security;

import java.util.Collection;

public interface FindAllSecuritiesUseCase {

    Collection<Security> fetchAllPersisted();
}
