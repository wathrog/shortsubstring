package org.francesco.shortsubstring;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

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
		//Pre-calculate string lengths
		int inputLength = s.length();
		
		//Define the window pointers
		int begin = 0;
		int end = 0;
		//Initialize counter for constraint
		int targetCount = 0;
		//Data structures to hold the target and current state
		Multiset<Character> target = HashMultiset.create();
		Multiset<Character> state = HashMultiset.create();
		//Initialization of the target multiset
		for (Character c : characters) {
			target.add(c);
		}
		//Initialization of the result variables
		int substringLength = Integer.MAX_VALUE;
		int substringStart = 0;
		int substringEnd = 0;
		//Execution of the algorithm
		Character currentChar = null;
		Character beginChar = null;
		for (; end < inputLength; end++) {
			currentChar = s.charAt(end);
			if (target.contains(currentChar)) {
				state.add(currentChar);
				if (state.count(currentChar) <= target.count(currentChar)) {
					targetCount++;
				}
				if (targetCount == numCharacters) {
					beginChar = s.charAt(begin);
					while (!target.contains(beginChar) || state.count(beginChar) > target.count(beginChar)) {
						if (state.count(beginChar) > target.count(beginChar)) {
							state.remove(beginChar);
						}
						beginChar = s.charAt(++begin);
					}
					
					int newLength = end - begin + 1;
					if (newLength < substringLength) {
						substringStart = begin;
						substringEnd = end;
						substringLength = newLength;
					}
				}
			}
		}
		
		
		if (targetCount == numCharacters) {
			return s.substring(substringStart, substringEnd+1);
		} else {
			return "";
		}
	}

}
