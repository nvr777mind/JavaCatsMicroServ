package com.example.ApiMicroservice.Migrations;

import org.flywaydb.core.Flyway;

public class Init {
    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/CatsLab_db",
                        "postgres", "admin")
                .load();

        flyway.migrate();
    }
}
