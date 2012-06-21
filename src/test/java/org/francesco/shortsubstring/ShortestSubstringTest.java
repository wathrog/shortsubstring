package org.francesco.shortsubstring;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ShortestSubstringTest {

    public static final String EMPTY_STRING = "";
    public static final String NULL_STRING = null;
    public static final String EXAMPLE_STRING = "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG";
    public static final Character[] EMPTY_ARRAY = new Character[0];
    public static final Character[] NULL_ARRAY = null;
    public static final Character[] EXAMPLE_ARRAY_NO_REPETITION = new Character[] { 'E', 'O', 'T' };
    public static final Character[] EXAMPLE_ARRAY_REPETITION = new Character[] { 'E', 'O', 'O', 'T' };
    public static final String EXPECTED_NO_REPETITION = "OVERT";
    public static final String EXPECTED_REPETITION = "OXJUMPSOVERT";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // Preconditions and parameter testing
    @Test
    public void testPreconditionsNullString() {
        thrown.expect(NullPointerException.class);
        ShortestSubstring.getShortestSubstring(NULL_STRING, EMPTY_ARRAY);
    }

    @Test
    public void testPreconditionsNullCharacters() {
        thrown.expect(NullPointerException.class);
        ShortestSubstring.getShortestSubstring(EMPTY_STRING, NULL_ARRAY);
    }

    @Test
    public void testEmptyString() {
        String result = ShortestSubstring.getShortestSubstring(EMPTY_STRING, EXAMPLE_ARRAY_NO_REPETITION);
        assertNotNull(result);
        assertEquals(result, EMPTY_STRING);
        int length = ShortestSubstring.getShortestSubstringLenght(EMPTY_STRING, EXAMPLE_ARRAY_NO_REPETITION);
        assertEquals(result.length(), length);
    }

    @Test
    public void testEmptyCharacters() {
        String result = ShortestSubstring.getShortestSubstring(EXAMPLE_STRING, EMPTY_ARRAY);
        assertNotNull(result);
        assertEquals(result, EMPTY_STRING);
        int length = ShortestSubstring.getShortestSubstringLenght(EXAMPLE_STRING, EMPTY_ARRAY);
        assertEquals(result.length(), length);
    }

    @Test
    public void testEmptyParameters() {
        String result = ShortestSubstring.getShortestSubstring(EMPTY_STRING, EMPTY_ARRAY);
        assertNotNull(result);
        assertEquals(result, EMPTY_STRING);
        int length = ShortestSubstring.getShortestSubstringLenght(EMPTY_STRING, EMPTY_ARRAY);
        assertEquals(result.length(), length);
    }

    // Functionality testing
    @Test
    public void testExampleSet() {
        String result = ShortestSubstring.getShortestSubstring(EXAMPLE_STRING, EXAMPLE_ARRAY_NO_REPETITION);
        assertNotNull(result);
        assertEquals(result, EXPECTED_NO_REPETITION);
        int length = ShortestSubstring.getShortestSubstringLenght(EXAMPLE_STRING, EXAMPLE_ARRAY_NO_REPETITION);
        assertEquals(result.length(), length);
    }

    @Test
    public void testExampleSetRepetitions() {
        String result = ShortestSubstring.getShortestSubstring(EXAMPLE_STRING, EXAMPLE_ARRAY_REPETITION);
        assertNotNull(result);
        assertEquals(result, EXPECTED_REPETITION);
        int length = ShortestSubstring.getShortestSubstringLenght(EXAMPLE_STRING, EXAMPLE_ARRAY_REPETITION);
        assertEquals(result.length(), length);
    }

}
