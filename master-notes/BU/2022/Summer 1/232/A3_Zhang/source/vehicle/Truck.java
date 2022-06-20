package source.vehicle;

import source.exception.*;
import source.service.*;

public class Truck extends Vehicle {
    private int capacity;

    private int load;

    public Truck(MakeName initialMake, String initialModel, double initialPrice, int initialProductionYear,
            int numberOfPassengers, int initialCapacity)
            throws ValidationException.InvalidString, ValidationException.NullObject,
            ValidationException.NegativeDouble, ValidationException.InvalidDate, ValidationException.FullArray,
            ValidationException.NotEnough, ValidationException.NegativeInteger {
        super(initialMake, initialModel, initialPrice, initialProductionYear, numberOfPassengers);
        setCapacity(initialCapacity);
    }

    @Override
    public void displayWindowSticker() {
        super.displayWindowSticker();
        System.out.println("Class: Truck\nCapacity: " + getCapacity() + "\nLoad: " + getLoad());
    };

    public int getCapacity() {
        return this.capacity;
    }

    public int getLoad() {
        return this.load;
    }

    public void setCapacity(int input) throws ValidationException.NegativeInteger {
        Validation.integerNonNegative("Capacity", input);
        this.capacity = input;
    }

    public void add(int input) throws ValidationException.OutScope {
        if ((input + this.load) > capacity)
            throw new ValidationException.OutScope("Total load can't be more than the capacity.");
        this.load += input;
    }

    public void remove(int input) throws ValidationException.OutScope {
        if ((this.load - input) < 0)
            throw new ValidationException.OutScope("Total load can't be negative.");
        this.load -= input;
    }
}
