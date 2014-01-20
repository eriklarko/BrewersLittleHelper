package org.blh.core.units.color;

import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.color.MaltColorUnit;
import org.blh.core.unit.volume.USGallons;
import org.blh.core.unit.weight.Lbs;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class MaltColorUnitTest {

	@Test
	public void testSomeMethod() {
		ColorPotential potential = new ColorPotential(new Lovibond(3), new Lbs(1));
		USGallons finalVolume = new USGallons(5);

		MaltColorUnit actual = new MaltColorUnit(potential, finalVolume);
		double expected = 3d / 5;

		Assert.assertEquals(expected, actual.value(), 0.0001);
	}
}
