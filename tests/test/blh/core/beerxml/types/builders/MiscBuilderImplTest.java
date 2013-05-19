package test.blh.core.beerxml.types.builders;

import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.Misc.MISC_TYPE;
import blh.core.beerxml.types.Misc.MISC_USE;
import blh.core.beerxml.types.builders.*;
import blh.core.units.time.Minutes;

public class MiscBuilderImplTest {

	@Test
	public void testSet() {
		Map<String, String> tags = new HashMap<>();

		String name = "1";
		MISC_TYPE type = MISC_TYPE.AGENT;
		MISC_USE use = MISC_USE.BOIL;
		Minutes time = new Minutes(4);
		double amount = 5;
		boolean amountIsWeight = true;
		String useFor = "7";
		String notes = "8";

		BuilderUtils.addTag(tags, Misc.NAME, name);
		BuilderUtils.addTag(tags, Misc.TYPE, type.toString());
		BuilderUtils.addTag(tags, Misc.USE, use.toString());
		BuilderUtils.addTag(tags, Misc.TIME, time);
		BuilderUtils.addTag(tags, Misc.AMOUNT, amount);
		BuilderUtils.addTag(tags, Misc.AMOUNT_IS_WEIGHT, amountIsWeight);
		BuilderUtils.addTag(tags, Misc.USE_FOR, useFor);
		BuilderUtils.addTag(tags, Misc.NOTES, notes);
		MiscBuilderImpl builder = new MiscBuilderImpl();
		for (Map.Entry<String, String> tag : tags.entrySet()) {
		    builder.set(tag.getKey(), tag.getValue());
		}
		Misc actual = builder.create();
		Misc expected = new Misc(name, type, use, time, amount, amountIsWeight, useFor, notes);
		assertEquality(expected, actual);
	}

	public void assertEquality(Misc expected, Misc actual) {
		Assert.assertEquals(expected.name, actual.name);
		Assert.assertEquals(expected.type, actual.type);
		Assert.assertEquals(expected.use, actual.use);
		Assert.assertEquals(expected.time, actual.time);
		Assert.assertEquals(expected.amount, actual.amount, 0.00001);
		Assert.assertEquals(expected.amountIsWeight, actual.amountIsWeight);
		Assert.assertEquals(expected.useFor, actual.useFor);
		Assert.assertEquals(expected.notes, actual.notes);
	}
}