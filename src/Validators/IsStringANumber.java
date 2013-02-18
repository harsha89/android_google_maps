package Validators;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  /*A Java class to verify if a String variable is a number*/  
  public class IsStringANumber{  
    public  boolean isNumeric(String number){  
         boolean isValid = false;  
         /* [-+]?: Can have an optional - or + sign at the beginning. 
            [0-9]*: Can have any numbers of digits between 0 and 9 
            \\.? : the digits may have an optional decimal point. 
             [0-9]+$: The string must have a digit at the end. 
           */  
           String expression = "[-+]?[0-9]*\\.?[0-9]+$";  
           CharSequence inputStr = number;  
           Pattern pattern = Pattern.compile(expression);  
           Matcher matcher = pattern.matcher(inputStr);  
           if(matcher.matches()){  
              isValid = true;  
           }  
           return isValid;  
         }  
      }  