/**
 * Array-based hash table class.
 * 
 * @author Zhang, Jin Yu
 */
public class HashTable {
    /** A value represents a slot never been used. */
    private static final int NEVER_USED = -1;

    /** A value represents a slot was previously used. */
    private static final int PREVIOUS_USED = -2;

    /** Table. */
    private int[] arrayOfSlot;

    /**
     * Generate an index from the input value.
     * 
     * @param input {@code int} Input value.
     * @return {@code int} Hashed index.
     */
    private int hash(int input) {
        return input % this.arrayOfSlot.length;
    }

    /**
     * Search the next index from the input index.
     * 
     * @param index {@code int} Table index that has been occupied.
     * @return {@code int} Table index.
     */
    private int probe(int index) {
        return (index + 1) % this.arrayOfSlot.length;
    }

    /**
     * Initialize an array-based hash table.
     * 
     * @param size {@code int} The size of table.
     */
    public HashTable(int size) {
        this.arrayOfSlot = new int[size];
        for (int index = 0; index < size; index++) {
            this.arrayOfSlot[index] = NEVER_USED;
        }
    }

    /**
     * Get the value where the hashed value index point.
     * 
     * @param value {@code int} Input value. Will be hashed to get the index.
     * @return {@code int} Value.
     */
    public int get(int value) {
        return this.arrayOfSlot[this.hash(value)];
    }

    /**
     * Add a value into the table.
     * 
     * @param value {@code int} Input value.
     * @throws IllegalAccessException If there is no empty slot remaining.
     */
    public void put(int value) throws IllegalAccessException {
        int indexOfValue = this.hash(value);

        for (int count = 0; count < this.arrayOfSlot.length; count++) {
            if (this.arrayOfSlot[indexOfValue] == NEVER_USED ||
                    this.arrayOfSlot[indexOfValue] == PREVIOUS_USED) {
                this.arrayOfSlot[indexOfValue] = value;
                return;
            } else
                indexOfValue = this.probe(indexOfValue);
        }

        throw new IllegalAccessException("There is no empty slot remaining.");
    }

    /**
     * See if the input value is in the table.
     * 
     * @param value {@code int} Input value.
     * @return {@code boolean} True if the value is found in the table.
     */
    public boolean contains(int value) {
        int indexOfValue = this.hash(value);

        for (int count = 0; count < this.arrayOfSlot.length; count++) {
            if (this.arrayOfSlot[indexOfValue] == value)
                return true;
            if (this.arrayOfSlot[indexOfValue] == NEVER_USED)
                return false;

            indexOfValue = this.probe(indexOfValue);
        }

        return false;
    }

    /**
     * Delete a value from the table. The slot will be {@code PREVIOUS_USED} after
     * deleting.
     * 
     * @param value {@code int} Value to delete.
     * @throws IllegalArgumentException If there is no such value stored in the
     *                                  table.
     */
    public void delete(int value) throws IllegalArgumentException {
        int indexOfValue = this.hash(value);

        for (int count = 0; count < this.arrayOfSlot.length; count++) {
            if (this.arrayOfSlot[indexOfValue] == value) {
                this.arrayOfSlot[indexOfValue] = PREVIOUS_USED;
                return;
            }
            if (this.arrayOfSlot[indexOfValue] == NEVER_USED)
                return;

            indexOfValue = this.probe(indexOfValue);
        }

        throw new IllegalArgumentException("There is no such value stored in the table.");
    }

    /** Print the table. */
    public void printHash() {
        for (int index = 0; index < this.arrayOfSlot.length; index++) {
            String value;
            if (this.arrayOfSlot[index] == NEVER_USED)
                value = "NEVER_USED";
            else if (this.arrayOfSlot[index] == PREVIOUS_USED)
                value = "PREVIOUS_USED";
            else
                value = String.valueOf(this.arrayOfSlot[index]);
            System.out.println("Index " + index + ": " + value);
        }
    }
}