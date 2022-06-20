package source.vehicle;

import source.exception.*;
import source.service.*;

public class Plane extends Vehicle {
    public enum EngineStatus {
        ON, OFF
    }

    final static int ENGINE_CAPACITY = 40;

    final static int WINDOW_GAP = 10;

    private EngineStatus[] arrayOfEngines;

    private int numberOfWindows;

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

    @Override
    public void displayWindowSticker() {
        super.displayWindowSticker();
        String engines = "";
        for (int index = 0; index < this.arrayOfEngines.length; index++) {
            String engine = this.arrayOfEngines[index].toString();
            engines += "[" + index + "] " + engine + (index == this.arrayOfEngines.length - 1 ? "" : ", ");
        }
        System.out
                .println("Class: Plane\nEngines: " + engines + "\nNumber of Windows: " + getNumberOfWindows());
    };

    @Override
    public void start() throws ValidationException.NotExist {
        for (int index = 0; index < this.arrayOfEngines.length; index++) {
            this.arrayOfEngines[index] = EngineStatus.OFF;
        }
        super.start();
    };

    @Override
    public void stop() throws ValidationException.NotExist {
        for (int index = 0; index < this.arrayOfEngines.length; index++) {
            this.arrayOfEngines[index] = EngineStatus.ON;
        }
        super.stop();
    };

    public int getNumberOfStatusEngine(EngineStatus status) {
        int count = 0;
        for (EngineStatus engine : arrayOfEngines) {
            if (engine == status)
                count++;
        }
        return count;
    }

    public void engineOff(int indexOfEngine) throws ValidationException.IllegalStatus, ValidationException.OutScope {
        if (isRunning() && getNumberOfStatusEngine(EngineStatus.ON) <= 1)
            throw new ValidationException.IllegalStatus("You need at least one engine to fly.");
        if (indexOfEngine > this.getNumberOfEngines() - 1)
            throw new ValidationException.OutScope("The index is out of scope");
        this.arrayOfEngines[indexOfEngine] = EngineStatus.OFF;
    };

    public void engineOn(int indexOfEngine) throws ValidationException.IllegalStatus, ValidationException.OutScope {
        if (indexOfEngine > this.getNumberOfEngines() - 1)
            throw new ValidationException.OutScope("The index is out of scope");
        this.arrayOfEngines[indexOfEngine] = EngineStatus.ON;
    };

    public int getNumberOfEngines() {
        return this.arrayOfEngines.length;
    }

    public int getNumberOfWindows() {
        return this.numberOfWindows;
    }

    public void setNumberOfWindows(int input) throws ValidationException.NegativeInteger {
        Validation.integerNonNegative("Number of Windows", input);
        this.numberOfWindows = input;
    }
}
