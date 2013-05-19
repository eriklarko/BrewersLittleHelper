package test.blh.core.beerxml.types.builders;

import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.MashStep.MASH_STEP_TYPE;
import blh.core.beerxml.types.builders.*;
import blh.core.units.temperature.Celcius;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;

public class MashStepBuilderImplTest {

	@Test
	public void testSet() {
		Map<String, String> tags = new HashMap<>();

		String name = "1";
		MASH_STEP_TYPE type = MASH_STEP_TYPE.DECOCTION;
		Liters infuseAmount = new Liters(3);
		Celcius stepTemp = new Celcius(4);
		Minutes stepTime = new Minutes(5);
		Minutes rampTime = new Minutes(6);
		Celcius endTemp = new Celcius(7);

		BuilderUtils.addTag(tags, MashStep.NAME, name);
		BuilderUtils.addTag(tags, MashStep.TYPE, type.toString());
		BuilderUtils.addTag(tags, MashStep.INFUSE_AMOUNT, infuseAmount);
		BuilderUtils.addTag(tags, MashStep.STEP_TEMP, stepTemp);
		BuilderUtils.addTag(tags, MashStep.STEP_TIME, stepTime);
		BuilderUtils.addTag(tags, MashStep.RAMP_TIME, rampTime);
		BuilderUtils.addTag(tags, MashStep.END_TEMP, endTemp);
		MashStepBuilderImpl builder = new MashStepBuilderImpl();
		for (Map.Entry<String, String> tag : tags.entrySet()) {
		    builder.set(tag.getKey(), tag.getValue());
		}
		MashStep actual = builder.create();
		MashStep expected = new MashStep(name, type, infuseAmount, stepTemp, stepTime, rampTime, endTemp);
		assertEquality(expected, actual);
	}

	public void assertEquality(MashStep expected, MashStep actual) {
		Assert.assertEquals(expected.name, actual.name);
		Assert.assertEquals(expected.type, actual.type);
		Assert.assertEquals(expected.infuseAmount, actual.infuseAmount);
		Assert.assertEquals(expected.stepTemp, actual.stepTemp);
		Assert.assertEquals(expected.stepTime, actual.stepTime);
		Assert.assertEquals(expected.rampTime, actual.rampTime);
		Assert.assertEquals(expected.endTemp, actual.endTemp);
	}
}