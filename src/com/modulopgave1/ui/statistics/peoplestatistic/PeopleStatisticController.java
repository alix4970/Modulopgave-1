package com.modulopgave1.ui.statistics.peoplestatistic;

import com.modulopgave1.dal.PeopleStatisticRepository;
import com.modulopgave1.model.*;
import com.modulopgave1.util.Console;
import com.modulopgave1.util.StringHelper;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

public class PeopleStatisticController {
    private final Scanner scanner = new Scanner(System.in);
    private PeopleStatisticRepository movementRepository;
    private PeopleStatisticModel model;

    public PeopleStatisticController(PeopleStatisticRepository movementRepository) {
        this.movementRepository = movementRepository;
        this.model = new PeopleStatisticModel();
    }

    public void displayMovementView()
    {
        while(true) {
            Console.clearConsole();
            System.out.println("MOVEMENT MENU\n");
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
                    model.setMovements(movementRepository.read(model.getSearchCriteria()));
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
        System.out.println("MOVEMENTS\n");

        if(model.getMovements().size() == 0)
            System.out.println("No entries found..");

        Iterator<PeopleStatistic> movementIterator = model.getMovements().iterator();
        while (movementIterator.hasNext()) {
            PeopleStatistic movement = movementIterator.next();
            sum += movement.getCount();

            System.out.println(movement.toString());
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
            System.out.println("4) Municipality");
            System.out.println("5) Gender");
            System.out.println("6) Clear Filter");
            System.out.println("\n0) Back");

            System.out.println("\nCriteria: "+ model.getSearchCriteria().toString());

            int input = -1;
            while (!((input == 0) || (input >= 3 && input <= 6))) {
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
                    displayMunicipalityDialog();
                    break;
                case 5:
                    displayGenderCriterionDialog();
                    break;
                case 6:
                    clearFilters();
                    break;
            }
        }
    }

    private void clearFilters() {
        model.setSearchCriteria(new PeopleStatistic());
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

    private void displayMunicipalityDialog() {
        Console.clearConsole();

        String input = "";
        while (input.isEmpty()) {
            System.out.print("Municipality\t>>\t");
            input = scanner.nextLine();
        }
        model.getSearchCriteria().setMunicipality(new Municipality(0, input));
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
