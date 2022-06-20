package source.vehicle;

import source.exception.*;
import source.service.*;

public class Car extends Vehicle {
    public enum DriveMode {
        Manual, Automatic, Initial
    }

    private DriveMode mode;

    private int numberOfDoors;

    public Car(MakeName initialMake, String initialModel, double initialPrice, int initialProductionYear,
            int numberOfPassengers) throws ValidationException.InvalidString, ValidationException.NullObject,
            ValidationException.NegativeDouble, ValidationException.InvalidDate, ValidationException.FullArray,
            ValidationException.NotEnough, ValidationException.NegativeInteger {
        super(initialMake, initialModel, initialPrice, initialProductionYear, numberOfPassengers);
        setMode(DriveMode.Initial);
        setNumberOfDoors(numberOfPassengers);
    }

    @Override
    public void displayWindowSticker() {
        super.displayWindowSticker();
        System.out.println(
                "Class: Car\nMode: " + getMode().toString() + "\nNumber of Doors: " + getNumberOfDoors());
    };

    public DriveMode getMode() {
        return this.mode;
    }

    public void setMode(DriveMode input) throws ValidationException.NullObject {
        Validation.objectMeaningful("Mode", input);
        this.mode = input;
    }

    public int getNumberOfDoors() {
        return this.numberOfDoors;
    }

    public void setNumberOfDoors(int input) throws ValidationException.NegativeInteger {
        Validation.integerNonNegative("Number of Doors", input);
        this.numberOfDoors = input;
    }
}
