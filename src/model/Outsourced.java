package model;

/**
 * Outsourced class extends the main Part Class
 */
public class Outsourced extends Part {

    /**
     * Each Outsourced part has a companyName of type String
     */
    private String companyName;

    /**
     * Constructor for the Outsourced that calls the super class Part and assigns it values while also assigning its companyName a value
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Returns companyName value for the Outsourced object
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets value companyName for the Outsourced object
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
