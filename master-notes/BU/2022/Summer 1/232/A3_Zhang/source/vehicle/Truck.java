package source.vehicle;

import source.exception.*;
import source.service.*;

/* File: Truck.java */

/**
 * Edit History:
 * 06/19/2022 Jin Yu, Zhang Initial version.
 * 06/20/2022 Jin Yu, Zhang Add public methods.
 */

/**
 * The class for Truck. A sub class from Vehicle.
 * 
 * @author Jin Yu, Zhang
 */
public class Truck extends Vehicle {
    private int capacity;

    private int load;

    /**
     * Setter for capacity. Should be positive.
     * 
     * @param input {@code int} Desired capacity.
     * @throws ValidationException.NegativeInteger
     */
    private void setCapacity(int input) throws ValidationException.NegativeInteger {
        Validation.integerNonNegative("Capacity", input);
        this.capacity = input;
    }

    /**
     * Construct a truck with necessary data.
     * 
     * @param initialMake           {@code MakeName} Make.
     * @param initialModel          {@code String} Model.
     * @param initialPrice          {@code double} Price. Should be positive.
     * @param initialProductionYear {@code int} Production year. Should be positive.
     * @param numberOfPassengers    {@code int} Array size for arrayOfPassengers.
     *                              Should be positive.
     * @param initialCapacity       {@code int} Capacity. Should be positive.
     * @throws ValidationException.InvalidString
     * @throws ValidationException.NullObject
     * @throws ValidationException.NegativeDouble
     * @throws ValidationException.InvalidDate
     * @throws ValidationException.FullArray
     * @throws ValidationException.NotEnough
     * @throws ValidationException.NegativeInteger
     */
    public Truck(MakeName initialMake, String initialModel, double initialPrice, int initialProductionYear,
            int numberOfPassengers, int initialCapacity)
            throws ValidationException.InvalidString, ValidationException.NullObject,
            ValidationException.NegativeDouble, ValidationException.InvalidDate, ValidationException.FullArray,
            ValidationException.NotEnough, ValidationException.NegativeInteger {
        super(initialMake, initialModel, initialPrice, initialProductionYear, numberOfPassengers);
        setCapacity(initialCapacity);
    }

    /**
     * Print all the data inherited from Vehicle and from the truck self.
     */
    @Override
    public void displayWindowSticker() {
        super.displayWindowSticker();
        System.out.println(
                "Class: " + this.getClass().getSimpleName() + "\nCapacity: " + getCapacity() + "\nLoad: " + getLoad());
    };

    /**
     * Getter for capacity.
     * 
     * @return Capacity.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Getter for load.
     * 
     * @return Load.
     */
    public int getLoad() {
        return this.load;
    }

    /**
     * Add stuff. The total for input and load can't be more that capacity.
     * 
     * @param input {@code} Desired adding number to load.
     * @throws ValidationException.OutScope
     */
    public void add(int input) throws ValidationException.OutScope {
        if ((input + this.load) > capacity)
            throw new ValidationException.OutScope("Total load can't be more than the capacity.");
        this.load += input;
    }

    /**
     * Remove stuff. The total for load after deducting input can't be negative.
     * 
     * @param input {@code} Desired removing number to load.
     * @throws ValidationException.OutScope
     */
    public void remove(int input) throws ValidationException.OutScope {
        if ((this.load - input) < 0)
            throw new ValidationException.OutScope("Total load can't be negative.");
        this.load -= input;
    }
}
