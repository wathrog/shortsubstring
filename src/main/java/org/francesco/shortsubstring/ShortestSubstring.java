package org.francesco.shortsubstring;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

/**
 * Class to solve the following problem:
 * Given a string s and a set of search characters c1, c2, ... cn, write code to determine the length of the shortest substring of s that contains all search characters.
 * 
 * Constraints used:
 * 1) Even if the word SET is mentioned, we assume that there can be repetitions in the search characters. e.g.: c1=a, c2=b, c3=a, ... cn
 * 2) The string s can contain multiple instances of the same character
 * 3) The problem do not exclude the use of non standard characters
 * 
 * @author francesco
 *
 */
public class ShortestSubstring {
	
	public static int getShortestSubstringLenght(String s, Character...characters) {
		return getShortestSubstring(s, characters).length();
	}
	
	public static String getShortestSubstring(String s, Character...characters) {
		//Verify preconditions
		Preconditions.checkNotNull(s);
		Preconditions.checkNotNull(characters);
		//Early exit conditions
		//If s is empty, there is no possible window, so we return an empty string.
		if (s.isEmpty()) {
			return "";
		}
		int numCharacters = characters.length;
		//If we are not given any substring characters, there is no possible window hence returning an empty string.
		if (numCharacters == 0) {
			return "";
		}
		//Pre-calculate string lenght
		int sLength = s.length();
		//Define the window pointers
		int start = 0;
		int end = 0;
		//Initialize counter for constraint
		int targetCount = 0;
		//Data structures to hold the target state and the current state (occurencies of the target characters in the window)
		Map<Character, Integer> target = new HashMap<Character, Integer>();
		Map<Character, Integer> state = new HashMap<Character, Integer>();
		//Initialize the data structures
		for (Character c : s.toCharArray()) {
			Integer charcount = target.get(c);
			if (charcount == null) {
				target.put(c, 0);
				state.put(c, 0);
			} else {
				target.put(c, charcount++);
			}
		}
		//Run the algorithm
		for (; end < sLength; end++) {
			Character currentChar = s.charAt(end);
			int currentCount = target.get(currentChar);
			if (target.containsKey(currentChar)) {
				
			}
			
		}
		
		
		
		return "null";
	}

}
