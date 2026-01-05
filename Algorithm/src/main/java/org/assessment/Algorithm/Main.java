package org.assessment.Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) {
        System.out.println(isPalindrome(1212));
    }

    public static boolean isPalindrome(int input){
        String stringValue = String.valueOf( + input);

        if(stringValue.charAt(0) == '-'){
            stringValue = stringValue.substring(1);
        }

        int length = stringValue.length();
        int middlePosition = length / 2;
        for(int i = 0; i <= middlePosition -1; i++){
            if(stringValue.charAt(i) != stringValue.charAt(length - 1 - i)){
                return false;
            }
        }

        return true;
    }
}
