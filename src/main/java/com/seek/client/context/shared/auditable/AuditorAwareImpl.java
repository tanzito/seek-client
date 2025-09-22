package com.seek.client.context.shared.auditable;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        en caso de implementar security, obtener el usuario de security context
        return Optional.of("system");
    }
}
