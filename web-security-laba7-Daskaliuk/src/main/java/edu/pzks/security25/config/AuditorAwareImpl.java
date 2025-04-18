package edu.pzks.security25.config;

/*
@author   Daskaliuk
@project  security25
@class  Item
@version  1.0.0
@since ${DATE}  02.04.2025 -15.05
*/


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {


        return Optional.of(System.getProperty("user.name"));
    }
}
