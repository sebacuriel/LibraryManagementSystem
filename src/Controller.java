/**
 * Sebastian Yael Curiel Franco
 * CEN 3024C - Software Development I - 31032
 * June 16, 2026
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
        if (addOption == 1)
        {
            while (true)
            {
                System.out.print("\nEnter a Patron ID: ");
                String id = scanner.nextLine();

                System.out.print("Enter the Patron's Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter the Patron's Address: ");
                String address = scanner.nextLine();

                System.out.print("Enter the Patron's Overdue Fine Amount: ");
                while (!scanner.hasNextDouble())
                {
                    System.out.print("Fine must be a number: ");
                    scanner.nextLine();
                }
                double fine = scanner.nextDouble();
                scanner.nextLine();

                if (validateID(id) && validateName(name) && validateAddress(address) && validateFine(fine))
                {
                    Patron patron = new Patron(id, name, address, fine);
                    patrons.add(patron);

                    System.out.println("");
                    showPatrons();
                    break;
                }
            }
        }
        else if (addOption == 2)
        {
            System.out.print("\nEnter the file's directory: ");
            String directory = scanner.nextLine();

            try
            {
                File file = new File(directory);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine())
                {
                    String data = scanner.nextLine();
                    String[] format = data.split("-");

                    if (format.length != 4)
                    {
                        System.out.println("\nInvalid Format (Must be ID-Name-Address-Fine)");
                        continue;
                    }

                    String id = format[0];
                    String name = format[1];
                    String address = format[2];
                    double fine = Double.parseDouble(format[3]);

                    if (validateID(id) && validateName(name) && validateAddress(address) && validateFine(fine))
                    {
                        Patron patron = new Patron(id, name, address, fine);
                        patrons.add(patron);
                    }
                }

                showPatrons();
            }
            catch (NumberFormatException e)
            {
                System.out.println("\nInvalid File, check the file's contents.");
                System.out.println("Make sure each Patron is in a valid format.");
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
                System.out.println("Please re-assign this Patron ID to a valid seven digit number.");
                return false;
            }
        }

        for (int i = 0; i < id.length(); i++)
        {
            if (!Character.isDigit(id.charAt(i)))
            {
                System.out.println("\nA Patron ID must only use numbers.");
                return false;
            }
        }

        if (id.length() != 7)
        {
            if (id.isBlank())
            {
                System.out.println("\nThe Patron must have an ID number.");
                return false;
            }
            else
            {
                System.out.println("\nThe Patron ID must be seven digits long.");
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
            System.out.println("\nThe Patron must have a namme.");
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
            System.out.println("\nThe Patron must have an address.");
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

        System.out.println("\nThis Patron ID does not exist.");
        return false;
    }
}
