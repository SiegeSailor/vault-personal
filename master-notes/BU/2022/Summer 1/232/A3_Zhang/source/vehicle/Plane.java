package source.vehicle;

import source.exception.*;
import source.service.*;

/* File: Plane.java */

/**
 * Edit History:
 * 06/19/2022 Jin Yu, Zhang Initial version.
 * 06/20/2022 Jin Yu, Zhang Add public methods.
 */

/**
 * The class for Plane. A sub class from Vehicle.
 * 
 * @author Jin Yu, Zhang
 */
public class Plane extends Vehicle {
    public enum EngineStatus {
        ON, OFF
    }

    final static int ENGINE_CAPACITY = 40;

    final static int WINDOW_GAP = 10;

    private EngineStatus[] arrayOfEngines;

    private int numberOfWindows;

    /**
     * Setter for number of windows. Should be positive.
     * 
     * @param input {@code} Number of windows.
     * @throws ValidationException.NegativeInteger
     */
    private void setNumberOfWindows(int input) throws ValidationException.NegativeInteger {
        Validation.integerNonNegative("Number of Windows", input);
        this.numberOfWindows = input;
    }

    /**
     * Construct a plane with necessary data. Number of engines will be
     * numberOfPassengers divided by ENGINE_CAPACITY, and all engines will be set to
     * OFF. Number of windows will be numberOfPassengers divided by WINDOW_GAP.
     * 
     * @param initialMake           {@code MakeName} Make.
     * @param initialModel          {@code String} Model.
     * @param initialPrice          {@code double} Price. Should be positive.
     * @param initialProductionYear {@code int} Production year. Should be positive.
     * @param numberOfPassengers    {@code int} Array size for arrayOfPassengers.
     *                              Should be positive.
     * @throws ValidationException.InvalidString
     * @throws ValidationException.NullObject
     * @throws ValidationException.NegativeDouble
     * @throws ValidationException.InvalidDate
     * @throws ValidationException.FullArray
     * @throws ValidationException.NotEnough
     * @throws ValidationException.NegativeInteger
     */
    public Plane(MakeName initialMake, String initialModel, double initialPrice, int initialProductionYear,
            int numberOfPassengers) throws ValidationException.InvalidString, ValidationException.NullObject,
            ValidationException.NegativeDouble, ValidationException.InvalidDate, ValidationException.FullArray,
            ValidationException.NotEnough, ValidationException.NegativeInteger {
        super(initialMake, initialModel, initialPrice, initialProductionYear, numberOfPassengers);
        this.arrayOfEngines = new EngineStatus[numberOfPassengers / ENGINE_CAPACITY];
        for (int index = 0; index < this.arrayOfEngines.length; index++) {
            this.arrayOfEngines[index] = EngineStatus.OFF;
        }
        setNumberOfWindows(numberOfPassengers / WINDOW_GAP);
    }

    /**
     * Print all the data inherited from Vehicle and from the plane self.
     */
    @Override
    public void displayWindowSticker() {
        super.displayWindowSticker();
        String engines = "";
        for (int index = 0; index < this.arrayOfEngines.length; index++) {
            String engine = this.arrayOfEngines[index].toString();
            engines += "[" + index + "] " + engine + (index == this.arrayOfEngines.length - 1 ? "" : ", ");
        }
        System.out
                .println("Class: " + this.getClass().getSimpleName() + "\nEngines: " + engines + "\nNumber of Windows: "
                        + getNumberOfWindows());
    };

    /**
     * Get the number of engines that is at the given status.
     * 
     * @param status {@code EngineStatus} Engine status.
     * @return The number of engines that is at the given status.
     */
    public int getNumberOfStatusEngine(EngineStatus status) {
        int count = 0;
        for (EngineStatus engine : arrayOfEngines) {
            if (engine == status)
                count++;
        }
        return count;
    }

    /**
     * Turn off an engine with index.
     * 
     * @param indexOfEngine {@code int} The engine's index.
     * @throws ValidationException.IllegalStatus
     * @throws ValidationException.OutScope
     */
    public void engineOff(int indexOfEngine) throws ValidationException.IllegalStatus, ValidationException.OutScope {
        if (isRunning() && getNumberOfStatusEngine(EngineStatus.ON) <= 1)
            throw new ValidationException.IllegalStatus("You need at least one engine to fly.");
        if (indexOfEngine > this.getNumberOfEngines() - 1)
            throw new ValidationException.OutScope("The index is out of scope");
        this.arrayOfEngines[indexOfEngine] = EngineStatus.OFF;
    };

    /**
     * Turn on an engine with index.
     * 
     * @param indexOfEngine {@code int} The engine's index.
     * @throws ValidationException.IllegalStatus
     * @throws ValidationException.OutScope
     */
    public void engineOn(int indexOfEngine) throws ValidationException.OutScope {
        if (indexOfEngine > this.getNumberOfEngines() - 1)
            throw new ValidationException.OutScope("The index is out of scope");
        this.arrayOfEngines[indexOfEngine] = EngineStatus.ON;
    };

    /**
     * Get the size of arrayOfEngines.
     * 
     * @return The size of arrayOfEngines.
     */
    public int getNumberOfEngines() {
        return this.arrayOfEngines.length;
    }

    /**
     * Getter for numberOfWindows.
     * 
     * @return Number of windows.
     */
    public int getNumberOfWindows() {
        return this.numberOfWindows;
    }
}
