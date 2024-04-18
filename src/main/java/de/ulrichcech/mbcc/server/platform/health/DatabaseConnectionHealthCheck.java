package de.ulrichcech.mbcc.server.platform.health;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {

    @Resource(lookup = "java:app/jdbc/mbcc")
    private DataSource dataSource;

    @Override
    public HealthCheckResponse call() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(0)) {
                final var resultSet = connection.prepareStatement("select count(1) from information_schema.tables WHERE table_schema = 'public'").executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return HealthCheckResponse.named("database-connection").up().withData("number_of_tables:", count).build();
                } else {
                    return HealthCheckResponse.named("database-connection").up().build();
                }
            } else {
                return HealthCheckResponse.named("database-connection").down().build();
            }
        } catch (SQLException ex) {
            return HealthCheckResponse.named("database-connection").down().withData("error", ex.getMessage()).build();
        }
    }
}