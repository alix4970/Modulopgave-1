package com.modulopgave1.ui.statistics.migrations;

import com.modulopgave1.dal.MigrationRepository;
import com.modulopgave1.model.Gender;
import com.modulopgave1.model.Migration;
import com.modulopgave1.model.Municipality;
import com.modulopgave1.model.Year;
import com.modulopgave1.util.Console;
import com.modulopgave1.util.StringHelper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

public class MigrationsController {
    private final Scanner scanner = new Scanner(System.in);
    private MigrationRepository migrationRepository;
    private MigrationsModel model;

    public MigrationsController(MigrationRepository migrationRepository) {
        this.migrationRepository = migrationRepository;
        this.model = new MigrationsModel();
    }

    public void displayMigrationsView() {
        while(true) {
            Console.clearConsole();
            System.out.println("MIGRATIONS MENU\n");
            System.out.println("1) Search");
            System.out.println("2) Filter Menu");
            System.out.println("\n0) Back");

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
                    model.setMigrations(migrationRepository.read(model.getSearchCriteria()));
                    displayListView();
                    break;
                case 2:
                    displayCriteriaView();
                    break;
            }
        }
    }

    public void displayListView() {
        int sum = 0;

        Console.clearConsole();
        System.out.println("MIGRATIONS\n");

        if(model.getMigrations().size() == 0)
            System.out.println("No entries found..");

        Iterator<Migration> migrationIterator = model.getMigrations().iterator();
        while (migrationIterator.hasNext()) {
            Migration migration = migrationIterator.next();
            sum += migration.getCount();

            System.out.println(migration.toString());
        }
        System.out.println("SUM: "+ sum);
        System.out.println("\nPress [ENTER] to continue..");
        scanner.nextLine();
        return;
    }

    private void displayCriteriaView() {
        while(true) {
            Console.clearConsole();
            System.out.println("FILTER MENU\n");
            System.out.println("3) Year");
            System.out.println("4) From Municipality");
            System.out.println("5) To Municipality");
            System.out.println("6) Gender");
            System.out.println("7) Clear Filter");
            System.out.println("\n0) Back");

            System.out.println("\nCriteria: "+ model.getSearchCriteria().toString());

            int input = -1;
            while (!((input == 0) || (input >= 3 && input <= 7))) {
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
                case 3:
                    displayYearCriterionDialog();
                    break;
                case 4:
                    displayFromMunicipalityDialog();
                    break;
                case 5:
                    displayToMunicipalityDialog();
                    break;
                case 6:
                    displayGenderCriterionDialog();
                    break;
                case 7:
                    clearFilters();
                    break;
            }
        }
    }

    private void clearFilters() {
        model.setSearchCriteria(new Migration());
    }

    private void displayYearCriterionDialog() {
        Console.clearConsole();

        int input = -1;
        while (!(input >= 0)) {
            System.out.print("Year\t>>\t");
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("input must be an integer..");
            }
        }
        model.getSearchCriteria().setYear(new Year(0, input));
    }

    private void displayFromMunicipalityDialog() {
        Console.clearConsole();

        String input = "";
        while (input.isEmpty()) {
            System.out.print("From Municipality\t>>\t");
            input = scanner.nextLine();
        }
        model.getSearchCriteria().setFromMunicipality(new Municipality(0, input));
    }

    private void displayToMunicipalityDialog() {
        Console.clearConsole();

        String input = "";
        while (input.isEmpty()) {
            System.out.print("To Municipality\t>>\t");
            input = scanner.nextLine();
        }
        model.getSearchCriteria().setToMunicipality(new Municipality(0, input));
    }

    private void displayGenderCriterionDialog() {
        Console.clearConsole();

        Gender gender = new Gender();

        System.out.println("1. Male");
        System.out.println("2. Female");

        int input = -1;
        while (!(input >= 1 && input <= 2)) {
            System.out.print("Gender\t>>\t");
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("input must be an integer..");
            }
        }

        switch (input) {
            case 1:
                gender.setTitle(StringHelper.convert("Mænd", "ISO-8859-1", "UTF-8"));
                break;
            case 2:
                gender.setTitle("Kvinder");
                break;
        }

        model.getSearchCriteria().setGender(gender);
    }
}
