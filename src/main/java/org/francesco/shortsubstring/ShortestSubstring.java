package org.francesco.shortsubstring;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Class to solve the following problem:</br> 
 * Given a string s and a set of search characters c1, c2, ... cn, write code to determine the length of the
 * shortest substring of s that contains all search characters.</br>
 * 
 * Specifications used:</br>
 * <ol>
 * <li>Even if the word SET is mentioned, we assume that there can be repetitions in the search characters. 
 *      e.g.: c1=a, c2=b, c3=a </li>
 * <li>The string s can contain multiple instances of the same character </li>
 * <li>The problem do not exclude the use of non standard characters (outside the A..Za..Z ranges) </li>
 * <li>There is no constraint about case sensitivity, I have chosen to implement the solution as case sensitive, 
 * so 'A' != 'a'</li>
 * </ol>
 * 
 * @author francesco
 * 
 */
public class ShortestSubstring {

    /**
     * Static method to solve the given problem and obtain the length of the
     * shortest substring</br>
     * 
     * @param s
     *            the string to analyse
     * @param characters
     *            the sequence of characters
     * @return the length of the shortest substring if exists, zero otherwise
     */
    public static int getShortestSubstringLenght(String s, Character... characters) {
        return getShortestSubstring(s, characters).length();
    }

    /**
     * Static method to solve the given problem and obtain the shortest
     * substring.</br> It is based on a sliding window of variable size and a
     * targetCoverage constraint.</br> Complexity is O(2n) in the worst case.
     * </br>
     * 
     * @param s
     *            the string to analyse
     * @param characters
     *            the sequence of characters
     * @return the shorter substring if exists, an empty string otherwise
     */
    public static String getShortestSubstring(String s, Character... characters) {
        // Verify preconditions
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(characters);
        // Early exit conditions
        // If s is empty, there is no possible window, so we return an empty
        // string.
        if (s.isEmpty()) {
            return "";
        }
        int numCharacters = characters.length;
        // If we are not given any substring characters, there is no possible
        // window hence returning an empty string.
        if (numCharacters == 0) {
            return "";
        }
        // Pre-calculate string lengths
        int inputLength = s.length();

        // Define the window pointers
        int begin = 0;
        int end = 0;
        // Initialize counter for target coverage constraint
        int targetCoverage = 0;
        // Data structures to hold the target and current state
        // The current state represent the number of occurrencies of each target
        // character in the current window
        Multiset<Character> target = HashMultiset.create();
        Multiset<Character> state = HashMultiset.create();
        // Initialization of the target multiset, adding each character to the
        // target multiset
        // this will hold a pair <Character, num_of_occurrencies>
        for (Character c : characters) {
            target.add(c);
        }
        // Initialization of the result variables
        int substringLength = Integer.MAX_VALUE;
        int substringStart = 0;
        int substringEnd = 0;
        // Initialization of temporary variables
        Character currentChar = null;
        Character beginChar = null;
        // Execution of the algorithm, start iterating a pointer through the
        // input string
        for (; end < inputLength; end++) {
            currentChar = s.charAt(end);
            // If the current character is in the target...
            if (target.contains(currentChar)) {
                // ...add it to the current state...
                state.add(currentChar);
                // ...increase the target coverage counter if the char is
                // contributing to cover the target
                if (state.count(currentChar) <= target.count(currentChar)) {
                    targetCoverage++;
                }
                // if a solution is find (full coverage of the target)
                if (targetCoverage == numCharacters) {
                    beginChar = s.charAt(begin);
                    // try to shrink the window from the beginning without
                    // breaking the 100% target coverage constraint
                    while (!target.contains(beginChar) || state.count(beginChar) > target.count(beginChar)) {
                        if (state.count(beginChar) > target.count(beginChar)) {
                            state.remove(beginChar);
                        }
                        beginChar = s.charAt(++begin);
                    }

                    // Analyse the solution and compare it with the current one
                    int newLength = end - begin + 1;
                    // if shorter, this will become our new solution
                    if (newLength < substringLength) {
                        substringStart = begin;
                        substringEnd = end;
                        substringLength = newLength;
                    }
                }
            }
        }

        // If we have found a solution, return it, otherwise return empty
        // string.
        if (targetCoverage == numCharacters) {
            return s.substring(substringStart, substringEnd + 1);
        } else {
            return "";
        }
    }

}
