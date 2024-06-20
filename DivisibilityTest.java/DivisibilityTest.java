import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DivisibilityTest {
    private static int number; // Variable to store the input number
    public static void main(String[]args){
        //  verify the input number from the user
        number = input_verify_number("Enter a number");
       // StringBuilder to collect reasons for divisibility
        StringBuilder reasons = new StringBuilder();
        //check divisibility from 1 to 9
        for(int i = 1; i <=9; i++){
            if(i==0){
                 // Check divisibility by 0 not possible
                JOptionPane.showMessageDialog(null, ""+number+" is not divisible by 0 "+get_divisibility_reason(0));
            }else if(number % i==0){
                // give reasons if the number is divisible by i
                reasons.append("\n").append(number).append(" is divisible by ").append(i).append(" ").append(get_divisibility_reason(i));
            }
        }
        JOptionPane.showMessageDialog(null, reasons.toString());

    } private static int input_verify_number(String message){
        String number_string = JOptionPane.showInputDialog(null, message);
        int number;
        try{
            // try to enter the input as an interger
            number = Integer.parseInt(number_string);
        }catch (Exception e){
            // if the code fails to run ask foor input again
            return input_verify_number("Invalid! Enter  number");
        }
        return number;
    }private static String get_divisibility_reason(int value){
         // HashMap to store divisibility rules
        HashMap<Integer, String> divisibility_cases = new HashMap<>();
        divisibility_cases.put(0, "because no number is divisible by 0.");
        divisibility_cases.put(1, "because every number is divisible by 1 or itself.");
        divisibility_cases.put(2, "A number is divisible by 2 if its unit digit is divisible by 2.");
        divisibility_cases.put(3, "A number is divisible by 3 if the sum of its digits is divisible by 3.");
        divisibility_cases.put(4, "A number is divisible by 4 if the number formed by its last two digits in the same order (tens and unit digits) is divisible by 4.");
        divisibility_cases.put(5, "A number is divisible by 5 if its unit digit is either 0 or 5.");
        divisibility_cases.put(6, "A number is divisible by 6 if its divisible by both 2 and 3.");
        divisibility_cases.put(7, "however no rule has been established.");
        divisibility_cases.put(8, "A number is divisible by 8 if the number formed by its last three digits in the same order (hundreds, tens and unit digits) is divisible by 8.");
        divisibility_cases.put(9, "A number is divisible by 9 if the sum of its digit is divisible by 9.");
        //Return the reason for divisibility
        return divisibility_cases.get(value)+" "+ (value == 0 || value == 1 || value == 7 ? "" : divisible__proof(number, value))+" "+number+ "/"+value+"="+number/value ;
    } 
 // Method to provide proof of divisibility
    private static String divisible__proof(int number_to_test, int test_against){
        int original_number = number_to_test;
        if(test_against == 2 || test_against == 5){
            // divisibility by 2 or 5
            int unit_digit = number_to_test % 10;
            return "For instance the unit digit for "+original_number+" is "+unit_digit+". "+unit_digit+" / "+test_against+" = "+unit_digit/test_against+".";
        } else if (test_against == 3 || test_against == 9) {
              //  divisibility by 3 or 9
            int sum = 0;
            ArrayList<Integer> number_digits = new ArrayList<>();
            while(number_to_test>0){
                int last_digit = number_to_test % 10;
                number_digits.add(last_digit);
                number_to_test /= 10;
                sum += last_digit;
            }
            StringBuilder all_digits = new StringBuilder();
            if (number_digits.size()>0){
                for (int digits: number_digits){
                    all_digits.append(" ").append(digits);
                }
            }
            return "The sum of digits("+all_digits+") in "+original_number+" is "+ sum+" which is divisible by "+ test_against+".";
        } else if (test_against == 4 || test_against == 8) {
            //  divisibility by 4 or 8
            int loop_twice = 1;
            int last__units = 0;
            AtomicInteger ones = new AtomicInteger();
            AtomicInteger tens = new AtomicInteger();
            AtomicInteger hundreds = new AtomicInteger();
            while (loop_twice <= (test_against == 4 ? 2 : 3) && number_to_test > 0){
                int last_digit = number_to_test % 10;
                if (loop_twice == 1){
                    ones.set(last_digit);
                }
                else if(loop_twice == 2){
                    tens.set(last_digit*10);
                }
                else if(loop_twice == 3){
                    hundreds.set(last_digit*100);
                }
                last__units = hundreds.get()+tens.get()+ones.get();
                number_to_test /= 10;
                loop_twice++;
            }
            return  "The last "+((test_against == 4) ? 2 : 3)+" ("+(last__units < 100 && test_against == 8 ? "0"+last__units: last__units)+") is divisible by "+test_against+" . ("+last__units+" / "+test_against + "= "+last__units/test_against+").";
        } else if (test_against == 6) {
            //6 must be divisible by 2 and 3
            return divisible__proof(original_number, 2)+" "+divisible__proof(original_number, 3)+".";
        }
        return "";
    }
}
