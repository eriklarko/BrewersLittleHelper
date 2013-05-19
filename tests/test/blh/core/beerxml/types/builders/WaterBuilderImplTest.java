package test.blh.core.beerxml.types.builders;

import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.units.*;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.builders.*;
import blh.core.units.volume.Liters;

public class WaterBuilderImplTest {

	@Test
	public void testSet() {
		Map<String, String> tags = new HashMap<>();

		String name = "1";
		Liters amount = new Liters(2);
		PPM calcium = new PPM(3);
		PPM bicarbonate = new PPM(4);
		PPM sulfate = new PPM(5);
		PPM chloride = new PPM(6);
		PPM sodium = new PPM(7);
		PPM magnesium = new PPM(8);
		PH ph = new PH(9);
		String notes = "10";

		BuilderUtils.addTag(tags, Water.NAME, name);
		BuilderUtils.addTag(tags, Water.AMOUNT, amount);
		BuilderUtils.addTag(tags, Water.CALCIUM, calcium);
		BuilderUtils.addTag(tags, Water.BICARBONATE, bicarbonate);
		BuilderUtils.addTag(tags, Water.SULFATE, sulfate);
		BuilderUtils.addTag(tags, Water.CHLORIDE, chloride);
		BuilderUtils.addTag(tags, Water.SODIUM, sodium);
		BuilderUtils.addTag(tags, Water.MAGNESIUM, magnesium);
		BuilderUtils.addTag(tags, Water.PH, ph);
		BuilderUtils.addTag(tags, Water.NOTES, notes);
		WaterBuilderImpl builder = new WaterBuilderImpl();
		for (Map.Entry<String, String> tag : tags.entrySet()) {
		    builder.set(tag.getKey(), tag.getValue());
		}
		Water actual = builder.create();
		Water expected = new Water(name, amount, calcium, bicarbonate, sulfate, chloride, sodium, magnesium, ph, notes);
		assertEquality(expected, actual);
	}

	public void assertEquality(Water expected, Water actual) {
		Assert.assertEquals(expected.name, actual.name);
		Assert.assertEquals(expected.amount, actual.amount);
		Assert.assertEquals(expected.calcium, actual.calcium);
		Assert.assertEquals(expected.bicarbonate, actual.bicarbonate);
		Assert.assertEquals(expected.sulfate, actual.sulfate);
		Assert.assertEquals(expected.chloride, actual.chloride);
		Assert.assertEquals(expected.sodium, actual.sodium);
		Assert.assertEquals(expected.magnesium, actual.magnesium);
		Assert.assertEquals(expected.ph, actual.ph);
		Assert.assertEquals(expected.notes, actual.notes);
	}
}