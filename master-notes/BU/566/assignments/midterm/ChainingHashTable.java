import java.util.*;

public class ChainingHashTable {
    private LinkedList<Integer>[] table;

    private int numberOfCollisions;

    private int numberOfInsert;

    private int hash(int input) {
        return input % this.table.length;
    }

    @SuppressWarnings({ "unchecked" })
    public ChainingHashTable(int numberOfSlot) {
        this.table = new LinkedList[numberOfSlot];
        for (int index = 0; index < this.table.length; index++)
            this.table[index] = new LinkedList<>();
    }

    public void insert(int input) {
        this.numberOfInsert++;
        int key = this.hash(input);
        String suffix = this.table[key].size() >= 1 ? ". Collision happened" : "";
        if (this.table[key].size() >= 1)
            this.numberOfCollisions++;
        this.table[key].add(input);
        System.out.println(this.numberOfInsert + ". " + "Insert `" + input + "` at position " + input + " % "
                + this.table.length + " = " + key + suffix);
    }

    public int getNumberOfCollisions() {
        return this.numberOfCollisions;
    }

    public String toString() {
        String result = "\nHash Table:\n";
        for (int index = 0; index < this.table.length; index++) {
            result += index + ": ";
            for (int i = 0; i < this.table[index].size(); i++)
                result += this.table[index].get(i) + " ";

            result += "\n";
        }
        return result;
    }

    public static void main(String[] arrayOfArgument) {
        ChainingHashTable main = new ChainingHashTable(9);
        int[] array = new int[] { 6, 8, 10, 9, 7, 5, 4, 13, 23, 1, 5, 7, 12, 4 };
        for (int index = 0; index < array.length; index++)
            main.insert(array[index]);

        System.out.print(main);
        System.out.println("\nThere are " + main.getNumberOfCollisions() + " collisions.");
    }
}