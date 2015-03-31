package org.blh.statistics.regexp;

import java.util.regex.Matcher;

import org.junit.Test;

import junit.framework.Assert;

public class RegExpBuilderTest {

	@Test
	public void testMatchOneCharacter() {
		Matcher m = RegExpBuilder.match(RegExpBuilder.ExactlyOne("a")).in("apa");
		Assert.assertTrue(m.find());
		Assert.assertTrue(m.find());
		Assert.assertFalse(m.find());
	}

	@Test
	public void testSaveOneCharacter() {
		Matcher m = RegExpBuilder.match("hej").thenSave(".").in("tjabbaheje");
		Assert.assertTrue(m.find());
		Assert.assertEquals("e", m.group());
	}

	@Test
	public void testMatchZerOrMore() {
		Matcher m = RegExpBuilder.match("").arbitrarilyMany("a").in("aaa");
		RegExpBuilder.dumpMatcher(m);
		Assert.assertTrue(m.find());
	}
}
