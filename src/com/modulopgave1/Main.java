package com.modulopgave1;

import com.modulopgave1.dal.MigrationRepository;
import com.modulopgave1.ui.statistics.migrations.MigrationsController;

public class Main {

    public static void main(String[] args)
    {
        MigrationRepository migrationRepo = new MigrationRepository();
        MigrationsController migrationsController = new MigrationsController(migrationRepo);

        migrationsController.displayMigrationsView();
    }
}
