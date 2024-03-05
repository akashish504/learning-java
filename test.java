import java.io.*;
 
// A simple interface
interface In1 {
   
    // public, static and final
    final int a = 10;
    
    // public and abstract
    void display(int k);
}


 
// A class that implements the interface.
class TestClass extends ThreadExamples implements In1 {
   
    // Implementing the capabilities of
    // interface.

    static int array[] = {1,23,4};
    public void display(int k){ 
      System.out.println("Geek"); 
    }
 
    // Driver Code
    public static void main(String[] args)
    {
        TestClass t = new TestClass();
        int k = 19;
        t.display(k);
        System.out.println(t.getText(a));
        System.out.println(a);
        System.out.println(array[0]);
    }
}

class ExtendedTestClass extends TestClass {

    // method overload
    public void display(String m) {
        System.out.println("this is an overloaded function");
    }

    public static void main(String[] args) {
        ExtendedTestClass ex = new ExtendedTestClass();
        String l = "this is and example";
        int x = 5;
        ex.display(x);
        ex.display(l);
    }
}