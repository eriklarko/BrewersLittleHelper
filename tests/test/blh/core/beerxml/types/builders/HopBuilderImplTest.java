package test.blh.core.beerxml.types.builders;

import org.junit.*;
import java.util.Map;
import java.util.HashMap;
import blh.core.units.*;
import blh.core.beerxml.types.*;
import blh.core.beerxml.types.Hop.HOP_FORM;
import blh.core.beerxml.types.Hop.HOP_TYPE;
import blh.core.beerxml.types.Hop.HOP_USE;
import blh.core.beerxml.types.builders.*;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;

public class HopBuilderImplTest {

	@Test
	public void testSet() {
		Map<String, String> tags = new HashMap<>();

		String name = "1";
		Percentage alpha = new Percentage(2);
		Kilograms amount = new Kilograms(3);
		HOP_USE use = HOP_USE.AROMA;
		Minutes time = new Minutes(5);
		String notes = "6";
		HOP_TYPE type = HOP_TYPE.BITTERING;
		HOP_FORM form = HOP_FORM.LEAF;
		Percentage beta = new Percentage(9);
		Percentage hopStabilityIndex = new Percentage(10);
		String origin = "11";
		String substitutes = "12";
		Percentage humulene = new Percentage(13);
		Percentage caryophyllene = new Percentage(14);
		Percentage cohumulone = new Percentage(15);
		Percentage myrcene = new Percentage(16);

		BuilderUtils.addTag(tags, Hop.NAME, name);
		BuilderUtils.addTag(tags, Hop.ALPHA, alpha);
		BuilderUtils.addTag(tags, Hop.AMOUNT, amount);
		BuilderUtils.addTag(tags, Hop.USE, use.toString());
		BuilderUtils.addTag(tags, Hop.TIME, time);
		BuilderUtils.addTag(tags, Hop.NOTES, notes);
		BuilderUtils.addTag(tags, Hop.TYPE, type.toString());
		BuilderUtils.addTag(tags, Hop.FORM, form.toString());
		BuilderUtils.addTag(tags, Hop.BETA, beta);
		BuilderUtils.addTag(tags, Hop.HOP_STABILITY_INDEX, hopStabilityIndex);
		BuilderUtils.addTag(tags, Hop.ORIGIN, origin);
		BuilderUtils.addTag(tags, Hop.SUBSTITUTES, substitutes);
		BuilderUtils.addTag(tags, Hop.HUMULENE, humulene);
		BuilderUtils.addTag(tags, Hop.CARYOPHYLLENE, caryophyllene);
		BuilderUtils.addTag(tags, Hop.COHUMULONE, cohumulone);
		BuilderUtils.addTag(tags, Hop.MYRCENE, myrcene);
		HopBuilderImpl builder = new HopBuilderImpl();
		for (Map.Entry<String, String> tag : tags.entrySet()) {
		    builder.set(tag.getKey(), tag.getValue());
		}
		Hop actual = builder.create();
		Hop expected = new Hop(name, alpha, amount, use, time, notes, type, form, beta, hopStabilityIndex, origin, substitutes, humulene, caryophyllene, cohumulone, myrcene);
		assertEquality(expected, actual);
	}

	public void assertEquality(Hop expected, Hop actual) {
		Assert.assertEquals(expected.name, actual.name);
		Assert.assertEquals(expected.alpha, actual.alpha);
		Assert.assertEquals(expected.amount, actual.amount);
		Assert.assertEquals(expected.use, actual.use);
		Assert.assertEquals(expected.time, actual.time);
		Assert.assertEquals(expected.notes, actual.notes);
		Assert.assertEquals(expected.type, actual.type);
		Assert.assertEquals(expected.form, actual.form);
		Assert.assertEquals(expected.beta, actual.beta);
		Assert.assertEquals(expected.hopStabilityIndex, actual.hopStabilityIndex);
		Assert.assertEquals(expected.origin, actual.origin);
		Assert.assertEquals(expected.substitutes, actual.substitutes);
		Assert.assertEquals(expected.humulene, actual.humulene);
		Assert.assertEquals(expected.caryophyllene, actual.caryophyllene);
		Assert.assertEquals(expected.cohumulone, actual.cohumulone);
		Assert.assertEquals(expected.myrcene, actual.myrcene);
	}
}