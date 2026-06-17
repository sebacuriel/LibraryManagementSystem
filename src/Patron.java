/**
 * Sebastian Yael Curiel Franco
 * CEN 3024C - Software Development I - 31032
 * June 16, 2026
 * Patron.java
 * This class defines a Patron object with an id, name, address,
 * and fine, and handles how the information is output.
 */

public class Patron
{
    private String id;
    private String name;
    private String address;
    private double fine;

    public Patron(String id, String name, String address, double fine)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fine = fine;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getFine()
    {
        return fine;
    }

    public void setFine(double fine)
    {
        this.fine = fine;
    }

    public String toString()
    {
        return id + "-" + name + "-" + address + "-" + fine;
    }
}
