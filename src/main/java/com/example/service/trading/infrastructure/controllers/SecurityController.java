package com.example.service.trading.infrastructure.controllers;

import com.example.service.trading.infrastructure.adapters.api.models.security.SaveSecurityBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.security.SecurityDto;
import com.example.service.trading.infrastructure.adapters.api.security.ChangeSecurityEndpointAdapter;
import com.example.service.trading.infrastructure.adapters.api.security.FindSecurityEndpointAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/securities")
public class SecurityController {

    private final ChangeSecurityEndpointAdapter changeSecurityEndpointAdapter;

    private final FindSecurityEndpointAdapter findSecurityEndpointAdapter;

    SecurityController(ChangeSecurityEndpointAdapter changeSecurityEndpointAdapter,
                   FindSecurityEndpointAdapter findSecurityEndpointAdapter) {
        this.changeSecurityEndpointAdapter = changeSecurityEndpointAdapter;
        this.findSecurityEndpointAdapter = findSecurityEndpointAdapter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SecurityDto saveSecurity(@RequestBody @Valid SaveSecurityBodyDto saveSecurityBodyDto) {
        return changeSecurityEndpointAdapter.saveSecurity(saveSecurityBodyDto);
    }

    @PutMapping("/{security_id}")
    @ResponseStatus(HttpStatus.OK)
    public SecurityDto updateSecurity(@PathVariable("security_id") int securityId,
                              @RequestBody @Valid SaveSecurityBodyDto saveSecurityBodyDto) {
        return changeSecurityEndpointAdapter.updateSecurity(securityId, saveSecurityBodyDto);
    }

    @GetMapping("/{security_id}")
    @ResponseStatus(HttpStatus.OK)
    public SecurityDto fetchSecurityById(@PathVariable("security_id") Integer securityId) {
        return findSecurityEndpointAdapter.fetchSecurityById(securityId);
    }

    @DeleteMapping("/{security_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSecurityById(@PathVariable("security_id") Integer securityId) {
        changeSecurityEndpointAdapter.deleteSecurity(securityId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<SecurityDto> fetchAllSecuritys() {
        return findSecurityEndpointAdapter.fetchAllSecurities();
    }

}
