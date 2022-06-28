package vehicle;

import java.io.Serializable;
import java.time.LocalDate;

import exception.*;
import service.*;

/* File: Vehicle.java */

/**
 * Edit History:
 * 06/19/2022 Jin Yu, Zhang Initial version.
 * 06/20/2022 Jin Yu, Zhang Add public methods and documentations.
 */

/**
 * Interface for a drivable stuff. The basic requirement for a vehicle is
 * defined here.
 * 
 * @author Jin Yu, Zhang
 */
interface Drivable {
    public enum MotionStatus {
        RUNNING,
        STOPPED
    }

    /**
     * Print all the data essential data.
     */
    void displayWindowSticker();

    /**
     * Start a vehicle. Change motion status to RUNNING.
     * 
     * @throws ValidationException.NotExist When there is no driver.
     */
    void start() throws ValidationException.NotExist;

    /**
     * Stop a vehicle. Change motion status to STOPPED.
     * 
     * @throws ValidationException.NotExist When there is no driver.
     */
    void stop() throws ValidationException.NotExist;

    /**
     * Add a passenger. Failed when there is no empty slot or when motion status is
     * RUNNING.
     * 
     * @param incomingPassenger {@code String} Passenger name.
     * @throws ValidationException.InvalidString When incomingPassenger's length is
     *                                           0.
     * @throws ValidationException.NullObject    When incomingPassenger is null.
     * @throws ValidationException.FullArray     When arrayOfPassenger is already
     *                                           full.
     * @throws ValidationException.IllegalStatus When the vehicle is at RUNNING.
     */
    void hop(String incomingPassenger)
            throws ValidationException.InvalidString, ValidationException.NullObject, ValidationException.FullArray,
            ValidationException.IllegalStatus;

    /**
     * Drop off a passenger. Failed when there is no passenger matched or when
     * motion status is RUNNING.
     * 
     * @param leavingPassenger {@code String} Passenger name.
     * @throws ValidationException.InvalidString When incomingPassenger's length is
     *                                           0.
     * @throws ValidationException.NullObject    When incomingPassenger is null.
     * @throws ValidationException.FullArray     When arrayOfPassenger is already
     *                                           full.
     * @throws ValidationException.IllegalStatus When the vehicle is at RUNNING.
     * @throws ValidationException.NotExist      When there is no such passenger.
     */
    void drop(String leavingPassenger)
            throws ValidationException.InvalidString, ValidationException.NullObject, ValidationException.NoMatched,
            ValidationException.IllegalStatus, ValidationException.NotExist;

    /**
     * Getter for arrayOfPassengers.
     * 
     * @return ArrayOfPassengers.
     */
    String[] getArrayOfPassengers();

    /**
     * Setter for arrayOfPassengers.
     * 
     * @param numberOfPassengers {@code int} Size of arrayOfPassengers.
     * @throws ValidationException.FullArray When there is already an
     *                                       arrayOfPassengers.
     * @throws ValidationException.NotEnough The number of passengers should be at
     *                                       least one.
     */
    void setArrayOfPassengers(int numberOfPassengers)
            throws ValidationException.FullArray, ValidationException.NotEnough;

    /**
     * Getter for driver. Could be null.
     * 
     * @return Driver.
     */
    String getDriver();

    /**
     * Setter for driver. Use index to assign a existing passenger. Failed when
     * there is no passengers or matched passenger.
     * 
     * @param indexOfPassenger {@code} Index for the passenger.
     * @throws ValidationException.OutScope When the index is larger than the length
     *                                      of arrayOfPassenger.
     * @throws ValidationException.NotExist Where there is no such passenger to
     *                                      assign.
     */
    void setDriver(int indexOfPassenger) throws ValidationException.OutScope, ValidationException.NotExist;

    /**
     * Set driver to null. Failed when there is no driver.
     * 
     * @throws ValidationException.NotExist Where there is no driver.
     */
    void resignDriver() throws ValidationException.NotExist;

    /**
     * Getter for motion status with boolean value for RUNNING.
     * 
     * @return True if the vehicle is at RUNNING motion status.
     */
    boolean isRunning();

    /**
     * Getter for status.
     * 
     * @return status.
     */
    MotionStatus getStatus();
}

/**
 * Interface for methods to override.
 * 
 * @author Jin Yu, Zhang
 */
interface Overridden extends Comparable<Vehicle> {
    String toString();
}

/**
 * The class for vehicle.
 * 
 * @author Jin Yu, Zhang
 */
public abstract class Vehicle implements Drivable, Overridden, Serializable {
    /**
     * Make names.
     */
    public enum MakeName {
        /** A make. */
        AC,
        /** A make. */
        BAIC,
        /** A make. */
        BMW,
        /** A make. */
        DAF,
        /** A make. */
        DS,
        /** A make. */
        GAZ,
        /** A make. */
        GMC,
        /** A make. */
        JAC,
        /** A make. */
        JMC,
        /** A make. */
        MG,
        /** A make. */
        NIO,
        /** A make. */
        RUF,
        /** A make. */
        SEAT,
        /** A make. */
        TVR
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

    /**
     * Construct a vehicle with necessary data.
     * 
     * @param initialMake           {@code MakeName} Make.
     * @param initialModel          {@code String} Model.
     * @param initialPrice          {@code double} Price. Should be positive.
     * @param initialProductionYear {@code int} Production year. Should be positive.
     * @param numberOfPassengers    {@code int} Array size for arrayOfPassengers.
     *                              Should be positive.
     * @throws ValidationException.InvalidString  When initialModel's length is 0.
     * @throws ValidationException.NullObject     When make is null.
     * @throws ValidationException.NegativeDouble When price is negative.
     * @throws ValidationException.InvalidDate    When production year is in the
     *                                            future or earlier that
     *                                            FIRST_VEHICLE_PATENT_YEAR due to
     *                                            obvious reason.
     * @throws ValidationException.FullArray      When there is already an
     *                                            arrayOfPassenger.
     * @throws ValidationException.NotEnough      When there are passengers inside
     *                                            the vehicle.
     */
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

    /**
     * Getter for make.
     * 
     * @return Make. Could be null.
     */
    public MakeName getMake() {
        return this.make;
    };

    /**
     * Getter for model.
     * 
     * @return Model. Could be null.
     */
    public String getModel() {
        return this.model;
    };

    /**
     * Getter for price.
     * 
     * @return Price.
     */
    public double getPrice() {
        return this.price;
    };

    /**
     * Getter for production year.
     * 
     * @return Production year.
     */
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

    /**
     * Setter for make. Can't set null.
     * 
     * @param input {@code MakeName} Make.
     * @throws ValidationException.NullObject When input is null.
     */
    public void setMake(MakeName input) throws ValidationException.NullObject {
        Validation.objectMeaningful("MakeName", input);
        this.make = input;
    };

    /**
     * Setter for model. The input should not be empty or null.
     * 
     * @param input {@code String} Model.
     * @throws ValidationException.InvalidString When input's length is 0.
     * @throws ValidationException.NullObject    When input is null.
     */
    public void setModel(String input) throws ValidationException.InvalidString, ValidationException.NullObject {
        Validation.stringMeaningful("Input", input);
        this.model = input;
    };

    /**
     * Setter for price. Can't be negative.
     * 
     * @param input {@code double} Price.
     * @throws ValidationException.NegativeDouble When input is negative.
     */
    public void setPrice(double input) throws ValidationException.NegativeDouble {
        Validation.doubleNonNegative("Price", input);
        this.price = input;
    };

    /**
     * Setter for production year. Can't be in the future or earlier that
     * FIRST_VEHICLE_PATENT_YEAR due to obvious reason.
     * 
     * @param input {@code int} Production year.
     * @throws ValidationException.InvalidDate When input is a invalid date.
     */
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

    /**
     * Get length of arrayOfPassengers.
     * 
     * @return Length of arrayOfPassengers.
     */
    public int getLengthOfArrayOfPassengers() {
        return this.arrayOfPassengers.length;
    }

    /**
     * Print data with toString method.
     */
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
        String[] arrayOfPassengers = getArrayOfPassengers();
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

    /**
     * Overridden method for compareTo. Return 1 if that is null. The comparing
     * process follows the following order:
     * 1. Make
     * 2. Model
     * 3. Price
     * 4. Production Year
     * Return 0 only if the above 4 are 0.
     * 
     * @param that {@code Vehicle} Compared object.
     * @return 0 if equal. 1 if higher. -1 if lower.
     */
    @Override
    public int compareTo(Vehicle that) {
        if (that == null)
            return 1;

        int resultMake = this.getMake().compareTo(that.getMake());
        if (resultMake != 0)
            return resultMake;

        int resultModel = this.getModel().compareTo(that.getModel());
        if (resultModel != 0)
            return resultModel;

        int resultPrice = Double.compare(this.getPrice(), that.getPrice());
        if (resultPrice != 0)
            return resultPrice;

        int resultProductionYear = Integer.compare(this.getProductionYear(), that.getProductionYear());
        return resultProductionYear;
    }
}
