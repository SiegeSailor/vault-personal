package source.vehicle;

import java.io.Serializable;
import java.time.LocalDate;

import source.exception.*;
import source.service.*;

interface Drivable {
    public enum MotionStatus {
        RUNNING,
        STOPPED
    }

    void displayWindowSticker();

    void start() throws ValidationException.NotExist;

    void stop() throws ValidationException.NotExist;

    void hop(String incomingPassenger)
            throws ValidationException.InvalidString, ValidationException.NullObject, ValidationException.FullArray,
            ValidationException.IllegalStatus;

    void drop(String leavingPassenger)
            throws ValidationException.InvalidString, ValidationException.NullObject, ValidationException.NoMatched,
            ValidationException.IllegalStatus, ValidationException.NotExist;

    String[] getArrayOfPassengers();

    void setArrayOfPassengers(int numberOfPassengers)
            throws ValidationException.FullArray, ValidationException.NotEnough;

    String getDriver();

    void setDriver(int indexOfPassenger) throws ValidationException.OutScope, ValidationException.NotExist;

    void resignDriver() throws ValidationException.NotExist;

    boolean isRunning();

    MotionStatus getStatus();
}

interface Overridden {
    String toString();

    int compareTo(Object that);
}

public abstract class Vehicle implements Drivable, Overridden, Serializable {
    public enum MakeName {
        AC, BAIC, BMW, DAF, DS, GAZ, GMC, JAC, JMC, MG, NIO, RUF, SEAT, TVR
    }

    final static int FIRST_VEHICLE_PATENT_YEAR = 1886;

    final static int LIMIT_DISPLAY_NUMBER_NAME = 24;

    private MakeName make;

    private String model;

    private double price;

    private int productionYear;

    private String[] arrayOfPassengers;

    private String driver;

    private MotionStatus status;

    public Vehicle(MakeName initialMake, String initialModel, double initialPrice, int initialProductionYear,
            int numberOfPassengers) throws ValidationException.InvalidString, ValidationException.NullObject,
            ValidationException.NegativeDouble, ValidationException.InvalidDate, ValidationException.FullArray,
            ValidationException.NotEnough {
        setMake(initialMake);
        setModel(initialModel);
        setPrice(initialPrice);
        setProductionYear(initialProductionYear);
        setArrayOfPassengers(numberOfPassengers);
        this.status = MotionStatus.STOPPED;
    };

    public MakeName getMake() {
        return this.make;
    };

    public String getModel() {
        return this.model;
    };

    public double getPrice() {
        return this.price;
    };

    public int getProductionYear() {
        return this.productionYear;
    };

    @Override
    public String[] getArrayOfPassengers() {
        return this.arrayOfPassengers.clone();
    };

    @Override
    public String getDriver() {
        return this.driver;
    }

    @Override
    public boolean isRunning() {
        return this.status == MotionStatus.RUNNING;
    }

    @Override
    public MotionStatus getStatus() {
        return this.status;
    };

    public void setMake(MakeName input) throws ValidationException.NullObject {
        Validation.objectMeaningful("MakeName", input);
        this.make = input;
    };

    public void setModel(String input) throws ValidationException.InvalidString, ValidationException.NullObject {
        Validation.stringMeaningful("Input", input);
        this.model = input;
    };

    public void setPrice(double input) throws ValidationException.NegativeDouble {
        Validation.doubleNonNegative("Price", input);
        this.price = input;
    };

    public void setProductionYear(int input) throws ValidationException.InvalidDate {
        int currentYear = LocalDate.now().getYear();
        if (input > currentYear)
            throw new ValidationException.InvalidDate("Production year can't be in the future. (" + currentYear + ")");
        if (input < 1886)
            throw new ValidationException.InvalidDate(
                    "The patent for vehicle powered by a gas engine was applied in " + FIRST_VEHICLE_PATENT_YEAR
                            + ". The production year is not possible to be early that it.");
        this.productionYear = input;
    };

    @Override
    public void setDriver(int indexOfPassenger) throws ValidationException.OutScope, ValidationException.NotExist {
        if (indexOfPassenger > arrayOfPassengers.length - 1)
            throw new ValidationException.OutScope("The index is out of scope");
        String passenger = arrayOfPassengers[indexOfPassenger];
        if (passenger == null)
            throw new ValidationException.NotExist("There is no passenger.");
        this.driver = passenger;
    }

    @Override
    public void resignDriver() throws ValidationException.NotExist {
        if (this.driver == null)
            throw new ValidationException.NotExist("There is no driver to resign.");
        this.driver = null;
    }

    @Override
    public void setArrayOfPassengers(int numberOfPassengers)
            throws ValidationException.FullArray, ValidationException.NotEnough {
        if (this.arrayOfPassengers != null && this.arrayOfPassengers.length > 0)
            throw new ValidationException.FullArray(
                    "There are passengers inside the vehicle. Please drop them somewhere first.");
        if (numberOfPassengers <= 0)
            throw new ValidationException.NotEnough(
                    "The number of passengers should be at least one. You need a driver.");
        this.arrayOfPassengers = new String[numberOfPassengers];
    }

    public int getLengthOfArrayOfPassengers() {
        return this.arrayOfPassengers.length;
    }

    public void summarize() {
        System.out.println(this);
    };

    @Override
    public void displayWindowSticker() {
        summarize();
    };

    @Override
    public void start() throws ValidationException.NotExist {
        if (getDriver() == null)
            throw new ValidationException.NotExist("There is no driver.");
        if (!isRunning())
            this.status = MotionStatus.RUNNING;
    };

    @Override
    public void stop() throws ValidationException.NotExist {
        if (getDriver() == null)
            throw new ValidationException.NotExist("There is no driver.");
        if (isRunning())
            this.status = MotionStatus.STOPPED;
    };

    @Override
    public void hop(String incomingPassenger)
            throws ValidationException.InvalidString, ValidationException.NullObject, ValidationException.FullArray,
            ValidationException.IllegalStatus {
        if (isRunning())
            throw new ValidationException.IllegalStatus("The vehicle is running.");
        Validation.stringMeaningful("Incoming Passenger", incomingPassenger);

        boolean isSlotFound = false;
        for (int index = 0; index < this.arrayOfPassengers.length; index++) {
            if (this.arrayOfPassengers[index] == null) {
                this.arrayOfPassengers[index] = incomingPassenger;
                isSlotFound = true;
                break;
            }
        }
        if (!isSlotFound)
            throw new ValidationException.FullArray("There is no empty slot.");
    };

    @Override
    public void drop(String leavingPassenger)
            throws ValidationException.InvalidString, ValidationException.NullObject, ValidationException.NoMatched,
            ValidationException.IllegalStatus, ValidationException.NotExist {
        if (isRunning())
            throw new ValidationException.IllegalStatus("The vehicle is running.");
        Validation.stringMeaningful("Leaving Passenger", leavingPassenger);
        boolean isPassengerFound = false;
        for (int index = 0; index < getLengthOfArrayOfPassengers(); index++) {
            if (this.arrayOfPassengers[index].equals(leavingPassenger)) {
                this.arrayOfPassengers[index] = null;
                isPassengerFound = true;
                if (leavingPassenger.equals(getDriver()))
                    resignDriver();

                break;
            }
        }
        if (!isPassengerFound)
            throw new ValidationException.NoMatched("There is no passenger matched.");
    };

    @Override
    public String toString() {
        String passengers = "";
        int lengthOfArrayOfPassengers = getLengthOfArrayOfPassengers();
        boolean isManyPassenger = lengthOfArrayOfPassengers > LIMIT_DISPLAY_NUMBER_NAME;
        int gap = lengthOfArrayOfPassengers - LIMIT_DISPLAY_NUMBER_NAME;
        for (int index = 0; index < (isManyPassenger ? LIMIT_DISPLAY_NUMBER_NAME
                : lengthOfArrayOfPassengers); index++) {
            String passenger = arrayOfPassengers[index];
            passengers += (index == 0 ? "" : ", ") + passenger;
        }
        if (isManyPassenger)
            passengers += " ,..." + gap + " more (total: " + lengthOfArrayOfPassengers + ")";

        return "# VEHICLE SUMMARY\nMake: " + getMake().toString() + "\nModel: " + getModel() + "\nPrice: " + getPrice()
                + "\nProduction Year: " + getProductionYear() + "\nPassengers: " + passengers + "\nDriver: "
                + getDriver() + "\nMotion Status: " + getStatus().toString();
    }

    @Override
    public int compareTo(Object that) {
        if (that == null || !(that instanceof Vehicle))
            return 1;
        Vehicle thatVehicle = (Vehicle) that;

        int resultMake = this.getMake().compareTo(thatVehicle.getMake());
        if (resultMake != 0)
            return resultMake;

        int resultModel = this.getModel().compareTo(thatVehicle.getModel());
        if (resultModel != 0)
            return resultModel;

        int resultPrice = Double.compare(this.getPrice(), thatVehicle.getPrice());
        if (resultPrice != 0)
            return resultPrice;

        int resultProductionYear = Integer.compare(this.getProductionYear(), thatVehicle.getProductionYear());
        return resultProductionYear;
    }
}
