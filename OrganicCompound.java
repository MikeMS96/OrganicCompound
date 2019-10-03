package com.metacube.question3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class for Organic Compound consists of method to find molar mass
 * 
 * @author Mohit Sahu
 *
 */
public class OrganicCompound {
	private int CARBON_MASS = 12;
	private int HYDROGEN_MASS = 1;
	private int OXYGEN_MASS = 16;
	// Local Cache created to get molar mass of compound if already found
	// previously
	Map<String, Integer> localCache = new HashMap<String, Integer>();

	/**
	 * Method to find the molecular mass of compound having C,H,O as only
	 * molecules
	 * 
	 * @param orgCompound
	 *            String consisting of compound formula
	 * @return Molecular mass of the compound as Integer type
	 */
	public int getMolecularMass(String orgCompound) {
		if (orgCompound == null) {
			throw new NullPointerException("Invalid: Null String Encountered");
		} else if (orgCompound == "") {
			throw new AssertionError("Invalid: Empty String Encountered");
		} else {
			orgCompound = orgCompound.toUpperCase();
			if (!localCache.containsKey(orgCompound)) {
				return getMolecularMassPart(orgCompound);
			}
			return localCache.get(orgCompound);
		}
	}

	/**
	 * This method is part of getMolecularMass separated for modularity purpose
	 * 
	 * @param orgCompound
	 * @return Molecular Mass to getMolecularMass which return it to caller
	 */
	private int getMolecularMassPart(String orgCompound) {
		Stack<Integer> compoundStack = new Stack<Integer>();
		int molarMass = 0;
		for (int loopVar = 0; loopVar < orgCompound.length(); loopVar++) {
			// Is it C, H or O
			if (orgCompound.charAt(loopVar) == 'C'
					|| orgCompound.charAt(loopVar) == 'H'
					|| orgCompound.charAt(loopVar) == 'O') {
				compoundStack
						.push(getCompoundMass(orgCompound.charAt(loopVar)));
			}
			// Is it an ( bracket
			else if (orgCompound.charAt(loopVar) == '(') {
				compoundStack.push(-1);
			}
			// Is it an digit
			else if (Character.isDigit(orgCompound.charAt(loopVar))) {
				StringBuilder stringBuilder = new StringBuilder();
				while (loopVar != orgCompound.length()
						&& Character.isDigit(orgCompound.charAt(loopVar))) {
					stringBuilder.append(orgCompound.charAt(loopVar));
					loopVar++;
				}
				int digit = compoundStack.pop();
				compoundStack
						.push((Integer.parseInt(stringBuilder.toString()) * digit));
				loopVar--;
			}
			// Is it an ) bracket
			else if (orgCompound.charAt(loopVar) == ')') {
				int value = 0;
				while (!compoundStack.isEmpty() && compoundStack.peek() != -1) {
					value += compoundStack.pop();
				}
				if (!compoundStack.isEmpty()) {
					// remove the -1 from stack
					compoundStack.pop();
					// put back the result calculated to stack
					compoundStack.push(value);
				} else {
					throw new AssertionError("Invalid Compound Formula");
				}
			} else {
				throw new AssertionError(
						"Invalid Compound : Compound molecule need to be C,H or O only");
			}
		}
		// Final calculation
		while (!compoundStack.isEmpty() && compoundStack.peek() != -1) {
			molarMass += compoundStack.pop();
		}
		if (!compoundStack.isEmpty() && compoundStack.peek() == -1) {
			throw new AssertionError("Invalid Compound Formula");
		}
		localCache.put(orgCompound, molarMass);
		return molarMass;
	}

	/**
	 * This method is used to return the molecular mass of an Molecule
	 * 
	 * @param compound
	 * @return molecule mass as Integer type
	 */
	private int getCompoundMass(char molecule) {
		switch (molecule) {
		case 'C':
			return CARBON_MASS;
		case 'H':
			return HYDROGEN_MASS;
		default:
			return OXYGEN_MASS;
		}
	}
}
