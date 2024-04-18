package de.ulrichcech.mbcc.server.platform.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessHealthCheck implements HealthCheck {


    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("application alive").up().build();
    }
}
