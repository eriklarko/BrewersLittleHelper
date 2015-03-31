package org.blh.statistics.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpBuilder implements CharSequence {

    public static void dumpMatcher(Matcher m) {
		System.out.println("Showing matches for " + m.pattern());
        while (m.find()) {
            System.out.println("NEW MATCH");
            for (int i = 0; i < m.groupCount(); i++) {
                System.out.println("Group " + i + ": " + m.group(i));
            }
        }

        m.reset();
    }

    public static final String whitespace = "\\s";
    public static final String alphaNum = "\\w";
    public static final String anything = ".*?";
    public static final String aCharacter = ".";
    public static final String nonDigit = "\\D";

    public static RegExpBuilder match(CharSequence s) {
        return new RegExpBuilder().sequence(s);
    }

    public static RegExpBuilder ArbitrarilyMany(CharSequence s) {
        return new RegExpBuilder().arbitrarilyMany(s);
    }

    public static RegExpBuilder AtLeastOne(CharSequence s) {
        return new RegExpBuilder().atLeastOne(s);
    }

	public static CharSequence ExactlyOne(String s) {
		return new RegExpBuilder().exactlyOne(s);
	}

    private final StringBuilder regexp;

    public RegExpBuilder() {
        regexp = new StringBuilder();
    }

    public RegExpBuilder thenSave(CharSequence s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        return this;
    }

    public RegExpBuilder atLeastOne(CharSequence s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        regexp.append("+?");
        return this;
    }

    public RegExpBuilder atMostOne(CharSequence s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        regexp.append("?");
        return this;
    }

    public RegExpBuilder arbitrarilyMany(CharSequence s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        regexp.append("*?");
        return this;
    }

	public RegExpBuilder exactlyOne(CharSequence s) {
		return this.followedBy(s);
	}

    public RegExpBuilder sequence(CharSequence s) {
        regexp.append(s);
        return this;
    }

    public RegExpBuilder followedBy(CharSequence s) {
        return sequence(s);
    }

    public RegExpBuilder until(CharSequence s) {
        return sequence(s);
    }

    public Matcher in(CharSequence s) {
        return Pattern.compile(regexp.toString(), Pattern.CASE_INSENSITIVE).matcher(s);
    }

    public Matcher inMultiline(CharSequence s) {
        return Pattern.compile(regexp.toString(), Pattern.CASE_INSENSITIVE | Pattern.MULTILINE).matcher(s);
    }

    @Override
    public String toString() {
        return regexp.toString();
    }

	@Override
	public int length() {
		return regexp.length();
	}

	@Override
	public char charAt(int index) {
		return regexp.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return regexp.subSequence(start, end);
	}
}
