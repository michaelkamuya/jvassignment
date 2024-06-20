package DivisibilityTest.java;

import javax.swing.*;
public class UserInfo {
    public static void main(String[] args){
        //user enters surname
        String surname = show_input_value_dialog("Enter surname");
        int number_of_char_surname = 0;
        //if no surname is entered create a warning message
        if(surname.isEmpty())
            JOptionPane.showMessageDialog(null, "Note# No surname entered", "Warning", JOptionPane.INFORMATION_MESSAGE);
        else{
            number_of_char_surname = surname.length();
        }
        int age = input_validate_age();
        boolean is_age_even = false;
        try{
            //check if the number is even
            if(age % 2 == 0){
                is_age_even = true;
            }
            // Print the number of characters in the surname if it's greater than 0
            System.out.println(number_of_char_surname > 0 ? "The number of characters is: "
                    .concat(String.valueOf(number_of_char_surname)) :
                    "No surname entered.");
            // Print whether the age is even or odd
            System.out.println(is_age_even ? "Your current age is an even number.": "Your current age is an odd number.");
        }catch (Exception e){
             // Show an error message if there's an issue with the age
            JOptionPane.showMessageDialog(null, "Did you enter a valid age?", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // show an input dialog and return the user's input.
    private static String show_input_value_dialog(String input_message){
        return JOptionPane.showInputDialog(null, input_message);
    }
    // validate  the users age
    private static int input_validate_age(){
        String age_string = show_input_value_dialog("Enter your age (number)");
        int age;
        try {
            age = Integer.parseInt(age_string);
        }catch (Exception e){
            // if no age entered create an error message 
            JOptionPane.showMessageDialog(null, "Invalid or no age entered!!!","Error", JOptionPane.ERROR_MESSAGE);
            return input_validate_age();
        }
        return age;
    }
}