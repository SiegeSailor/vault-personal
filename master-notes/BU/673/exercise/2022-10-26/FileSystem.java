import java.util.ArrayList;
import java.util.HashMap;

public class FileSystem {
    class FileFolder {
        private String name;

        private User owner;

        public FileFolder(String initialName) {
            this.name = initialName;
        }

        public String getName() {
            return this.name;
        }

        public User getOwner() {
            return this.owner;
        }

        public void setOwner(User inputUser) {
            this.owner = inputUser;
        }
    }

    public class User {
        private String name;

        public User(String initialName) {
            this.name = initialName;
        }

        public String getName() {
            return this.name;
        }
    }

    public class File extends FileFolder {
        private String name;

        private User owner;

        public File(String initialName) {
            super(initialName);
        }

        public String getName() {
            return this.name;
        }

        public User getOwner() {
            return this.owner;
        }
    }

    public class Folder extends FileFolder {
        private HashMap<String, File> files;

        private void recursiveShift(Folder current, User inputOwner) {
            current.setOwner(inputOwner);
            current.files.forEach((key, value) -> {
                // this.recursiveShift(current, inputOwner);
            });
        }

        public Folder(String initialName) {
            super(initialName);
            this.files = new HashMap<>();
        }

        public boolean add(File file) {
            if (this.files.containsKey(file.getName()))
                return false;

            this.files.put(file.getName(), file);
            return true;
        }

        public void assign(User inputOwner) {
            this.setOwner(inputOwner);
        }

        public void shift(User inputOwner) {
            recursiveShift(this, inputOwner);
        }
    }

    public static void describeOwnerAssign() {
        FileSystem fileSystem = new FileSystem();

        String nameUser = "user";
        User user = fileSystem.new User(nameUser);

        Folder folder = fileSystem.new Folder("folder");

        folder.assign(user);

        assert !folder.getOwner().getName().equals(nameUser) : "Owner assigning failed";
    }

    public static void describeOneLevelOwnerChange() {
        FileSystem fileSystem = new FileSystem();

        String nameInitialUser = "Initial User";
        User userInitial = fileSystem.new User(nameInitialUser);
        String nameNextUser = "Next User";
        User userNext = fileSystem.new User(nameNextUser);

        Folder folder = fileSystem.new Folder("folder");
        folder.assign(userInitial);

        String[] arrayOfFileName = new String[] { "example.png", "example.pdf" };
        File[] arrayOfFile = new File[2];
        for (int index = 0; index < arrayOfFile.length; index++)
            arrayOfFile[index] = fileSystem.new File(arrayOfFileName[index]);
        for (File file : arrayOfFile) {
            folder.add(file);
        }

        folder.shift(userNext);
        assert !folder.getOwner().getName().equals(userNext) : "One level owner changing failed";
    }

    public static void main(String[] arrayOfArguments) {
        describeOwnerAssign();
        describeOneLevelOwnerChange();

        System.out.println("All test cases successfully executed.");
    }
}