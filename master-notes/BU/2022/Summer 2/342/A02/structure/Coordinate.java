package structure;

/**
 * Data type represents a coordinate.
 * 
 * @author Zhang, Jin Yu
 */
public class Coordinate {
    /** Current row. */
    private int row;

    /** Current column. */
    private int column;

    /**
     * Construct a coordinate with initial row and column. A coordinate must have a
     * valid pair of row and column all the time.
     * 
     * @param initialRow    {@code int} Initial row.
     * @param initialColumn {@code int} Initial column
     */
    public Coordinate(int initialRow, int initialColumn) {
        this.row = initialRow;
        this.column = initialColumn;
    }

    /**
     * Get row.
     * 
     * @return {@code int} Row number.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get column.
     * 
     * @return {@code int} Column number.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Return the string representation for each node.
     * 
     * @return {@code String} A string representation for each node.
     */
    @Override
    public String toString() {
        return "(" + this.row + ", " + this.column + ")";
    }

    /**
     * Compare if two coordinates are at the same place. Always return {@code true}
     * if compare with self. Return {@code false} if the other object is not a
     * {@code Coordinate} object.
     * 
     * @return {@code boolean} True if both are at the same place.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof Coordinate))
            return false;

        Coordinate that = (Coordinate) object;
        return (this.row == that.row) && (this.column == that.column);
    }
}
