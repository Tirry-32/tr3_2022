package week02;
import textio.TextIO;

public class TestTextIO {
  public static void main(String[]args){
      //start new input
      TextIO.put("Enter name :");
      //string input
      String name = TextIO.getln();
      //numerical input
      TextIO.put("Enter age:");
      int age = TextIO.getlnInt();
      TextIO.put("Enter Salary :");
      double salary = TextIO.getlnDouble();

      // output to standard
      TextIO.putln("Name"+name);
      TextIO.putln("Age"+age);
      TextIO.putln("Salary"+salary);

  }
}
