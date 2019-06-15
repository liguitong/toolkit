package codecheck;

import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;

import static org.junit.Assert.*;

public class LineParserTest {
    private static LineParser parser = null;
    @BeforeClass
    public static void init() throws Exception {
        File fileLibrary = new File("src/main/java/codecheck/Library.java");
        parser = new LineParser();
        parser.parseFile(fileLibrary);
        System.out.println("Set up executed");
    }

    @Test
    public void testParseFile() {
        parser.parseResult();
    }
    @Test
    public void testIsSeparateLine(){
        assertFalse(parser.isSeparateLine(10));
        assertFalse(parser.isSeparateLine(9));
        assertTrue(parser.isSeparateLine(8));
    }
    @Test
    public void TestJoinLines(){
        parser.parseResult();
        parser.joinLines();
        parser.parseResult();
    }
}