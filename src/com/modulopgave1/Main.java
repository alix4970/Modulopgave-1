package com.modulopgave1;

import com.modulopgave1.dal.MigrationRepository;
import com.modulopgave1.dal.PeopleStatisticRepository;
import com.modulopgave1.ui.statistics.migrations.MigrationsController;
import com.modulopgave1.ui.statistics.peoplestatistic.PeopleStatisticController;

public class Main {

    public static void main(String[] args)
    {
        PeopleStatisticRepository peoplestatisticRepo = new PeopleStatisticRepository();
        PeopleStatisticController peoplestatistcController = new PeopleStatisticController(peoplestatisticRepo);
        peoplestatistcController.displayMovementView();

        MigrationRepository migrationRepo = new MigrationRepository();
        MigrationsController migrationsController = new MigrationsController(migrationRepo);

        migrationsController.displayMigrationsView();
    }
}
