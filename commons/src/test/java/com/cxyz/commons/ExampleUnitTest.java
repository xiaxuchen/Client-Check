package com.cxyz.commons;

import com.cxyz.commons.widget.sideview.CharacterParser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void t()
    {
        CharacterParser characterParser = CharacterParser.getInstance();
        String str = "夏旭晨";
        for(char c:str.toCharArray())
        {
            System.out.print(characterParser.getSelling(String.valueOf(c)).charAt(0));
        }
    }
}