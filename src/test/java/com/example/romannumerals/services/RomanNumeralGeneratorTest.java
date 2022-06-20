package com.example.romannumerals.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RomanNumeralGenerator.class)
public class RomanNumeralGeneratorTest {
    private final RomanNumeralGenerator generator;

    public RomanNumeralGeneratorTest(){
        generator = new RomanNumeralGenerator();
    }

    @Test
    public void parseInvalidInputTests(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> generator.parse("Not roman"));
        Assertions.assertThrows(IllegalArgumentException.class,() -> generator.parse("MMIXC"));
        Assertions.assertThrows(IllegalArgumentException.class,() -> generator.parse("MMMM"));
    }

    @Test
    public void parseRomanNumberTests(){
        Assertions.assertEquals(3997,generator.parse("MMMCMXCVII"));
        Assertions.assertEquals(2345,generator.parse("MMCCCXLV"));
        Assertions.assertEquals(1699,generator.parse("MDCXCIX"));
        Assertions.assertEquals(723,generator.parse("DCCXXIII"));
        Assertions.assertEquals(102,generator.parse("CII"));
        Assertions.assertEquals(43,generator.parse("XLIII"));
    }

    @Test
    public void generateInvalidInputTests(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> generator.generate(4000));
        Assertions.assertThrows(IllegalArgumentException.class,() -> generator.generate(-900));
    }

    @Test
    public void generateRomanNumberTests(){
        Assertions.assertEquals("MMCCCIX",generator.generate(2309));
        Assertions.assertEquals("MMMCMXCIX",generator.generate(3999));
        Assertions.assertEquals("I",generator.generate(1));
        Assertions.assertEquals("CXLVII",generator.generate(147));
    }
}
