package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BackingServiceHealthIndicator implements HealthIndicator {
    private BackingService service;

    public BackingServiceHealthIndicator(BackingService service) {
        this.service = service;
    }

    @Override
    public Health health() {
        Health.Builder health = new Health.Builder();
        if (this.service.ping()) {
            health.up();
        } else {
            health.down();
        }
        return health.build();
    }
}
