package com.modulopgave1;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

import com.modulopgave1.util.Console;
import com.modulopgave1.dal.MigrationRepository;
import com.modulopgave1.dal.PeopleStatisticRepository;
import com.modulopgave1.ui.statistics.migrations.MigrationsController;
import com.modulopgave1.ui.statistics.peoplestatistic.PeopleStatisticController;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    static PeopleStatisticController peoplestatistcController;
    static MigrationsController migrationsController;


    public static void main(String[] args)
    {
        PeopleStatisticRepository peoplestatisticRepo = new PeopleStatisticRepository();
        peoplestatistcController = new PeopleStatisticController(peoplestatisticRepo);

        MigrationRepository migrationRepo = new MigrationRepository();
        migrationsController = new MigrationsController(migrationRepo);

        displayMainView();
    }

    public static void displayMainView() {
        while(true) {
            Console.clearConsole();
            System.out.println("MAIN MENU\n");
            System.out.println("1) Migrations");
            System.out.println("2) People Statistics");
            System.out.println("\n0) EXIT");

            int input = -1;
            while (!((input == 0) || (input >= 1 && input <= 2))) {
                System.out.print("input\t>>\t");
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("input must be an integer..");
                }
            }

            switch (input) {
                case 0:
                    return;
                case 1:
                    migrationsController.displayMigrationsView();
                    break;
                case 2:
                    peoplestatistcController.displayMovementView();
                    break;
            }
        }
    }
}