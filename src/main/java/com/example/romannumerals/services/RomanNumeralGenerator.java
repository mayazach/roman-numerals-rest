package com.example.romannumerals.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Pattern;

@Service
public class RomanNumeralGenerator {
    private static final Map<Character,Integer> characterIntegerMap = Map.of(
            'I',1,
            'V',5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );
    private static final String romanRegEx = "M{0,3}(CM|DC{0,3}|CD|C{0,3})(XC|LX{0,3}|XL|X{0,3})(IX|VI{0,3}|IV|I{0,3})";

    private Integer parseRecursion(String romanString, Integer number, Character nextChar){
        if(romanString.isEmpty())
            return number;
        char currentChar = romanString.charAt(romanString.length()-1);
        if(nextChar.equals('-')) {
            number = characterIntegerMap.get(currentChar);
        }
        else if(characterIntegerMap.get(nextChar) <= characterIntegerMap.get(currentChar)){
            number += characterIntegerMap.get(currentChar);
        }
        else {
            number -= characterIntegerMap.get(currentChar);
        }
        nextChar = currentChar;
        return parseRecursion(romanString.substring(0,romanString.length()-1),number,nextChar);
    }

    public String generate(Integer number){
        if(number > 3999 || number < 1)
            throw new IllegalArgumentException("Number must be between 1 and 3999");
        StringBuilder romanNumber = new StringBuilder();
        while(number > 0){
            if(number >= 1000) {
                romanNumber.append("M".repeat(number / 1000));
                number %= 1000;
            }
            else if(number >= 900){
                romanNumber.append("CM");
                number -= 900;
            }
            else if(number >= 500){
                romanNumber.append("D");
                number -= 500;
            }
            else if(number >= 400){
                romanNumber.append("CD");
                number -= 400;
            }
            else if(number >= 100){
                romanNumber.append("C".repeat(number / 100));
                number %= 100;
            }
            else if(number >= 90){
                romanNumber.append("XC");
                number -= 90;
            }
            else if(number >= 50){
                romanNumber.append("L");
                number -= 50;
            }
            else if(number >= 40){
                romanNumber.append("XL");
                number -= 40;
            }
            else if(number >= 10){
                romanNumber.append("X".repeat(number / 10));
                number %= 10;
            }
            else if(number == 9){
                romanNumber.append("IX");
                number = 0;
            }
            else if(number >= 5){
                romanNumber.append("V");
                number -= 5;
            }
            else if(number == 4){
                romanNumber.append("IV");
                number = 0;
            }
            else{
                romanNumber.append("I".repeat(number));
                number = 0;
            }
        }

        return romanNumber.toString();
    }

    public Integer parse(String roman){
        if(!Pattern.matches(romanRegEx,roman))
            throw new IllegalArgumentException("Please enter a valid roman number between 1 and 3999");
        return parseRecursion(roman,0,'-');
    }
}
