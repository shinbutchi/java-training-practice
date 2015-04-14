package vn.smartdev.javatrainingpractice.springmvcpractice.entities;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {
    public String getCurrentAuditor() {

        return "Dat";
    }
}
