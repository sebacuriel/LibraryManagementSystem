/**
 * Sebastian Yael Curiel Francoy
 * CEN 3024C - Software Development I - 31032
 * June 16, 2026
 * Main.java
 * This application asks the user to select an option
 * to either add, remove, or display Patron information.
 * It is to be used in a library setting to track patrons.
 */

import java.io.*;
import java.util.*;

public class Main
{
    /**
     * method: main
     * parameters: args
     * return: none
     * purpose: creates a menu for the user to
     * add, remove, or show patrons, as well
     * as exiting the program
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();

        int userInput;
        int addOption;

        while (true)
        {
            System.out.println("\n- Welcome to Library Management System -");
            System.out.println("- Select what you would like to do -");
            System.out.println("\n1. Add Patron");
            System.out.println("2. Remove Patron");
            System.out.println("3. Display Patrons");
            System.out.println("4. Exit Program");
            System.out.println("");

            try
            {
                userInput = scanner.nextInt();
                scanner.nextLine();

                switch (userInput)
                {
                    case 1:

                        System.out.println("\nHow would you like to add a Patron?");
                        System.out.println("1. Manual");
                        System.out.println("2. Text File");
                        System.out.println("");

                        addOption = scanner.nextInt();
                        scanner.nextLine();

                        switch (addOption)
                        {
                            case 1:
                            case 2:

                                controller.addPatron(addOption);
                                break;

                            default:

                                System.out.println("\nInvalid Option");
                        }

                        break;

                    case 2:

                        controller.removePatron();
                        break;

                    case 3:

                        System.out.println("");
                        controller.showPatrons();
                        break;

                    case 4:

                        System.out.println("\nExiting LMS...");
                        return;

                    default:

                        System.out.println("\nInvalid Option");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nInvalid Option");
                scanner.nextLine();
            }
        }
    }
}