## RomanNumeralGenerator

The RomanNumeralGenerator class contains the generate and parse functions, for converting arabic numbers to roman numbers and the reverse. For the roman numbers, standard form with subtractive notation is used. Only capital characters are accepted. There is only one class instead of an interface and class implementing it, as there is just one implementation at the moment.

**Integer parse(String roman)**: In a roman numeral string, a symbol placed before one of greater value subtracts its value. If placed before one of equal or lesser value it adds its value instead. This makes recursion a good tool for converting the string to a number. The private method **parseRecursion** takes the string, removes the last character, adds or subtracts its value after comparing it to that of the character before it if one exists, then is called again until there are no characters left in the string. The valid roman number characters and their values are stored in a map for easier comparison. Before conversion starts, the parse method checks the string is a valid roman number using a regular expression.

**String generate(Integer number)**: This one simply checks the number is between 1 and 3999, then it uses a while loop to subtract from the number the greatest value that corresponds to a roman symbol and add it to a string, until the number is 0.
