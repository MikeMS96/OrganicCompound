package com.metacube.question3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test class for Organic Compound
 * 
 * @author Mohit Sahu
 *
 */
public class OrganicCompoundTest {
	OrganicCompound organicCompound = new OrganicCompound();

	@Test
	public void getMolecularMassTestMethodWithCompoundWithoutBrackets() {
		assertEquals(14, organicCompound.getMolecularMass("CH2"));
	}

	@Test
	public void getMolecularMassTestMethodWithCompoundWithBrackets() {
		assertEquals(206, organicCompound.getMolecularMass("CH2(CH4)12"));
	}

	@Test
	public void getMolecularMassTestMethodWithCompoundInLowerCase() {
		assertEquals(180, organicCompound.getMolecularMass("c6h12o6"));
	}

	@Test
	public void getMolecularMassTestMethodWithCompoundInUpperCase() {
		assertEquals(342, organicCompound.getMolecularMass("C12H22O11"));
	}

	@Test(expected = AssertionError.class)
	public void getMolecularMassTestMethodWithCompoundHavingLessOpenBracket() {
		assertEquals(0, organicCompound.getMolecularMass("CH2OH)12"));
	}

	@Test(expected = AssertionError.class)
	public void getMolecularMassTestMethodWithCompoundHavingLessCloseBracket() {
		assertEquals(0, organicCompound.getMolecularMass("CH2OH(O12"));
	}

	@Test(expected = NullPointerException.class)
	public void getMolecularMassTestMethodWithNullValue() {
		assertEquals(0, organicCompound.getMolecularMass(null));
	}

	@Test(expected = AssertionError.class)
	public void getMolecularMassTestMethodWithEmptyValue() {
		assertEquals(0, organicCompound.getMolecularMass(""));
	}

	@Test(expected = AssertionError.class)
	public void getMolecularMassTestMethodWithInvalidCompound() {
		assertEquals(0, organicCompound.getMolecularMass("CHDTSdsssse"));
	}

}
