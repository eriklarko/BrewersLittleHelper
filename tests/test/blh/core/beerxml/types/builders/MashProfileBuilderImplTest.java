package test.blh.core.beerxml.types.builders;

import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.units.*;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.builders.*;
import blh.core.units.temperature.Celcius;
import blh.core.units.weight.Kilograms;
import java.util.List;

public class MashProfileBuilderImplTest {

	@Test
	public void testSet() {
		Map<String, String> tags = new HashMap<>();

		String name = "1";
		Celcius grainTemperature = new Celcius(2);
		String notes = "4";
		Celcius tunTemperature = new Celcius(5);
		Celcius spargeTemperature = new Celcius(6);
		PH spargePH = new PH(7);
		Kilograms tunWeight = new Kilograms(8);
		double tunSpecificHeat = 9;
		boolean adjustForEquipmentTemperature = true;

		BuilderUtils.addTag(tags, MashProfile.NAME, name);
		BuilderUtils.addTag(tags, MashProfile.GRAIN_TEMPERATURE, grainTemperature);
		BuilderUtils.addTag(tags, MashProfile.NOTES, notes);
		BuilderUtils.addTag(tags, MashProfile.TUN_TEMPERATURE, tunTemperature);
		BuilderUtils.addTag(tags, MashProfile.SPARGE_TEMPERATURE, spargeTemperature);
		BuilderUtils.addTag(tags, MashProfile.SPARGE_PH, spargePH);
		BuilderUtils.addTag(tags, MashProfile.TUN_WEIGHT, tunWeight);
		BuilderUtils.addTag(tags, MashProfile.TUN_SPECIFIC_HEAT, tunSpecificHeat);
		BuilderUtils.addTag(tags, MashProfile.ADJUST_FOR_EQUIPMENT_TEMPERATURE, adjustForEquipmentTemperature);
		MashProfileBuilderImpl builder = new MashProfileBuilderImpl();
		for (Map.Entry<String, String> tag : tags.entrySet()) {
		    builder.set(tag.getKey(), tag.getValue());
		}
		MashProfile actual = builder.create();
		MashProfile expected = new MashProfile(name, grainTemperature, null, notes, tunTemperature, spargeTemperature, spargePH, tunWeight, tunSpecificHeat, adjustForEquipmentTemperature);
		assertEquality(expected, actual);
	}

	public void assertEquality(MashProfile expected, MashProfile actual) {
		Assert.assertEquals(expected.name, actual.name);
		Assert.assertEquals(expected.grainTemperature, actual.grainTemperature);
		Assert.assertEquals(expected.mashSteps, actual.mashSteps);
		Assert.assertEquals(expected.notes, actual.notes);
		Assert.assertEquals(expected.tunTemperature, actual.tunTemperature);
		Assert.assertEquals(expected.spargeTemperature, actual.spargeTemperature);
		Assert.assertEquals(expected.spargePH, actual.spargePH);
		Assert.assertEquals(expected.tunWeight, actual.tunWeight);
		Assert.assertEquals(expected.tunSpecificHeat, actual.tunSpecificHeat, 0.00001);
		Assert.assertEquals(expected.adjustForEquipmentTemperature, actual.adjustForEquipmentTemperature);
	}
}