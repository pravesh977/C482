package model;

/**
 * InHouse class extends the main Part Class
 */
public class InHouse extends Part {

    /**
     * Each InHouse part has a machineId of type Int
     */
    private int machineId;

    /**
     * Constructor for the InHouse that calls the super class Part and assigns it values while also assigning its machineId a value
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Returns machineId value for the InHouse object
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets value machineId for the InHouse object
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}
