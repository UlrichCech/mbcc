/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

/**
 * This is the initial database synchronization bean. All classes annotated with @Startup
 * should also have the <code>@DependsOn("FlywayMigrator")</code> annotation in order to
 * start not before the database is in migrated schema.
 *
 * @author Ulrich Cech
 */
@Singleton
@Startup
public class FlywayMigrator {

    @Resource(lookup = "java:app/jdbc/mbcc")    // this name is configured in the glassfish-resources.xml
    private DataSource dataSource;

    protected Flyway migrator;


    @PostConstruct
    public void migrate() {
        this.migrator = Flyway.configure()
                .dataSource(dataSource)
                .schemas("public")
                .locations("database/migration")
                .baselineOnMigrate(true)
                .baselineVersion("0000")
                .load();
        migrator.repair();
        migrator.migrate();
    }

}
