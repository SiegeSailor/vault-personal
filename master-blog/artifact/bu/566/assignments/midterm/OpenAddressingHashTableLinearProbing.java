public class OpenAddressingHashTableLinearProbing {
    private static final int NEVER_USED = -1;

    private static final int PREVIOUS_USED = -2;

    private int[] table;

    private int numberOfCollisions;

    private int numberOfInsert;

    private int hash(int input, int number) {
        return ((input % 7) + number) % this.table.length;
    }

    public OpenAddressingHashTableLinearProbing(int numberOfSlot) {
        this.table = new int[numberOfSlot];
        for (int index = 0; index < this.table.length; index++)
            this.table[index] = NEVER_USED;
    }

    public void insert(int input) throws IllegalAccessException {
        for (int count = 0; count < this.table.length; count++) {
            this.numberOfInsert++;
            int key = this.hash(input, count);
            System.out.println(this.numberOfInsert + ". " + "Insert `" + input + "` at position (" + "((" + input
                    + " % 7) + " + count + ") % " + this.table.length + ")" + " = " + key);
            if (this.table[key] == NEVER_USED || this.table[key] == PREVIOUS_USED) {
                this.table[key] = input;
                this.numberOfCollisions += count;
                return;
            }
        }

        throw new IllegalAccessException("There is no empty slot remaining.");
    }

    public int getNumberOfCollisions() {
        return this.numberOfCollisions;
    }

    public String toString() {
        String result = "Hash Table:\n";
        for (int index = 0; index < this.table.length; index++) {
            result += index + ": " + this.table[index] + "\n";
        }
        return result;
    }

    public static void main(String[] arrayOfArgument) {
        OpenAddressingHashTableLinearProbing main = new OpenAddressingHashTableLinearProbing(7);

        int[] array = new int[] { 31, 11, 5, 17, 25 };

        for (int index = 0; index < array.length; index++) {
            try {
                main.insert(array[index]);
            } catch (Exception event) {
                System.out.println(index + ": " + array[index] + " " + event.getMessage());
            }
        }

        System.out.println("\n" + main);

        System.out.println("There are " + main.getNumberOfCollisions() + " collisions.");
    }
}