package de.ulrichcech.mbcc;

import de.ulrichcech.mbcc.server.platform.persistence.PersistenceUnits;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


/**
 * Helper class to create the schema-generation file.
 *
 * @author Ulrich Cech
 */
public class DatabaseSchemaGenerator {

    public static void main(String[] args) {
        try(EntityManagerFactory factory = Persistence.createEntityManagerFactory(PersistenceUnits.PERSISTENCE_UNIT_GENERATE_SCHEMA)) {
            factory.createEntityManager(); // this triggers the schema-generation of EclipseLink
        }
    }
}
