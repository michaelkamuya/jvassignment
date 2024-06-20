import javax.swing.*;
public class StudentsaverageMarks {
    public static void main(String[]args){
        // create a variable to store the average mark
    float avg;
      // create a variable to store the sum of the marks,
    int sum=0; 
     for(int i=1;i<=5;i++){
         // verify and add  the input mark 
            sum+=input_verify("input the "+i+" mark");
        }
         // divide the sum by5 to calculate the average
      avg= (float) sum/5;
      // print the Average with two decimal places
        System.out.printf("%.2f%n",avg);
    }
    //verify the input marks
    private static int input_verify(String message){
        //Get the input as a string and  an input dialog to the use
        String mark_string= JOptionPane.showInputDialog(null,message);
        int mark;
        try {
            //change the input string to an integer
            mark=Integer.parseInt(mark_string);
            if(mark<0 || mark >100)
            //if the numbers are not in range change the method to a valid mark 
                return input_verify("invalid.enter new mark");
        }catch (Exception e){
            //if no number is entered output invalid choice
            return input_verify("invalid.enter new mark");
        }
        return mark;
    }
}