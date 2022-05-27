public class Simple
{
    public static void main(String args[])
    {
      System.out.println("Hello World!");  
    };
};

// javac Simple.java generates Simple.class (compile to byte-code, this is called portability, this is not platform-specified, so we can send/use it through internet)
// Use java Simple to execute it (just-in-time, translate to machine language only as needed and stored for later use)
// Simple.class is a bytecode file, which is not limited with platforms as c++ (Slower compile time)

// javadoc -d doc Simple.java (Only /* */ ones, has to be in the format)