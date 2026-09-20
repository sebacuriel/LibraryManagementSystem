/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * September 19, 2026
 * Controller.java
 * This class handles all of the methods called by the Main.java class.
 * It contains an addPatron method to handle adding patrons manually or through a text file.
 * It contains a removePatron method to remove patrons from an arrayList.
 * It contains a showPatrons method to show the arrayList.
 * It contains a validate method to handle error cases.
 */

import java.io.*;
import java.util.*;

public class Controller
{
    private ArrayList<Patron> patrons = new ArrayList<>();
    Scanner scanner = new Scanner (System.in);

    /**
     * method: addPatron
     * parameters: int
     * return: none
     * purpose: add patrons to an arrayList,
     * either manually, or through a file
     */
    public void addPatron(int addOption)
    {
        String id;
        String name;
        String address;
        double fine;

        if (addOption == 1)
        {
            do
            {
                System.out.print("\nEnter a Patron ID: ");
                id = scanner.nextLine();
            }
            while (!validateID(id));

            do
            {
                System.out.print("\nEnter the Patron's Name: ");
                name = scanner.nextLine();
            }
            while (!validateName(name));

            do
            {
                System.out.print("\nEnter the Patron's Address: ");
                address = scanner.nextLine();
            }
            while (!validateAddress(address));

            do
            {
                System.out.print("\nEnter the Patron's Overdue Fine Amount: ");

                while (!scanner.hasNextDouble())
                {
                    System.out.print("Fine must be a number: ");
                    scanner.nextLine();
                }

                fine = scanner.nextDouble();
                scanner.nextLine();
            }
            while (!validateFine(fine));

            Patron patron = new Patron(id, name, address, fine);
            patrons.add(patron);

            System.out.println("");
            showPatrons();
        }
        else if (addOption == 2)
        {
            System.out.print("\nEnter the file's directory: ");
            String directory = scanner.nextLine();

            try
            {
                File file = new File(directory);
                Scanner fscanner = new Scanner(file);
                int line = 0;

                while (fscanner.hasNextLine())
                {
                    line++;

                    String data = fscanner.nextLine();
                    String[] format = data.split("-");

                    if (format.length != 4)
                    {
                        System.out.println("\nInvalid Format (Must be ID-Name-Address-Fine)");
                        System.out.println("Line " + line);
                        System.out.println("");
                        continue;
                    }

                    id = format[0];
                    name = format[1];
                    address = format[2];

                    try
                    {
                        fine = Double.parseDouble(format[3]);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Line " + line + ": Fine must be a number.");
                        System.out.println("");
                        continue;
                    }

                    if (!validateID(id) || !validateName(name) || !validateAddress(address) || !validateFine(fine))
                    {
                        System.out.println("Line " + line);
                        System.out.println("");
                        continue;
                    }

                    Patron patron = new Patron(id, name, address, fine);
                    patrons.add(patron);
                }

                showPatrons();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("\nInvalid File, check your directory.");
                System.out.println("Make sure to include the file extension.");
            }
        }
    }

    /**
     * method: removePatron
     * parameters: none
     * return: none
     * purpose: removes a patron from an arrayList
     */
    public void removePatron()
    {
        System.out.print("\nEnter the Patron ID you wish to remove: ");
        String id = scanner.nextLine();

        if (!checkIDs(id))
        {
            return;
        }

        for (Patron patron : patrons)
        {
            if (patron.getId().equals(id))
            {
                patrons.remove(patron);

                System.out.println("\nRemoved Patron #" + id);
                showPatrons();
                return;
            }
        }
    }

    /**
     * method: showPatrons
     * parameters: none
     * return: none
     * purpose: display the patron arrayList
     */
    public void showPatrons()
    {
        System.out.println("-----------------------------------------------------");
        if (patrons.isEmpty())
        {
            System.out.println("There are currently no patrons for the library.");
            System.out.println("-----------------------------------------------------");
            return;
        }

        for (Patron patron : patrons)
        {
            System.out.println(patron);
        }
        System.out.println("-----------------------------------------------------");
    }

    /**
     * method: validateID
     * parameters: id
     * return: boolean
     * purpose: handles ID error cases when
     * adding a patron to the arrayList
     */
    public boolean validateID(String id)
    {
        for (Patron patron : patrons)
        {
            if (patron.getId().equals(id))
            {
                System.out.println("\nPatron ID is duplicate.");
                System.out.println("Please re-assign the Patron ID to a valid seven digit number.");
                return false;
            }
        }

        for (int i = 0; i < id.length(); i++)
        {
            if (!Character.isDigit(id.charAt(i)))
            {
                System.out.println("\nPatron ID must only use numbers.");
                return false;
            }
        }

        if (id.length() != 7)
        {
            if (id.isBlank())
            {
                System.out.println("\nPatron must have an ID number.");
                return false;
            }
            else
            {
                System.out.println("\nPatron ID must be seven digits long.");
                return false;
            }
        }

        return true;
    }

    /**
     * method: validateName
     * parameters: name
     * return: boolean
     * purpose: handles name error cases
     * when adding a patron to the arrayList
     */
    public boolean validateName(String name)
    {
        if (name.isBlank())
        {
            System.out.println("\nPatron must have a name.");
            return false;
        }

        return true;
    }

    /**
     * method: validateAddress
     * parameters: address
     * return: boolean
     * purpose: handles address error cases
     * when adding a patron to the arrayList
     */
    public boolean validateAddress(String address)
    {
        if (address.isBlank())
        {
            System.out.println("\nPatron must have an address.");
            return false;
        }

        return true;
    }

    /**
     * method: validateFine
     * parameters: fine
     * return: boolean
     * purpose: handles fine error cases
     * when adding a patron to the arrayList
     */
    public boolean validateFine(double fine)
    {
        if (fine < 0 || fine > 250)
        {
            System.out.println("\nPatron Fine is outside the valid range of $0.00 - $250.00");
            return false;
        }

        return true;
    }

    /**
     * method: checkIDs
     * parameters: id
     * return: boolean
     * purpose: handles ID error cases when
     * removing a patron from the arrayList
     */
    public boolean checkIDs(String id)
    {
        for (Patron patron : patrons)
        {
            if (patron.getId().equals(id))
            {
                return true;
            }
        }

        System.out.println("\nPatron ID does not exist.");
        return false;
    }
}