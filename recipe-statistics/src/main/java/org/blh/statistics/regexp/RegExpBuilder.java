package org.blh.statistics.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpBuilder {

    public static void dumpMatcher(Matcher m) {
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

    public static RegExpBuilder match(String s) {
        return new RegExpBuilder().sequence(s);
    }

    public static RegExpBuilder ArbitrarilyMany(String s) {
        return new RegExpBuilder().arbitrarilyMany(s);
    }

    public static RegExpBuilder AtLeastOne(String s) {
        return new RegExpBuilder().atLeastOne(s);
    }

    private final StringBuilder regexp;

    public RegExpBuilder() {
        regexp = new StringBuilder();
    }

    public RegExpBuilder thenSave(RegExpBuilder s) {
        return thenSave(s.toString());
    }

    public RegExpBuilder thenSave(String s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        return this;
    }

    public RegExpBuilder atLeastOne(String s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        regexp.append("+?");
        return this;
    }

    public RegExpBuilder atMostOne(String s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        regexp.append("?");
        return this;
    }

    public RegExpBuilder arbitrarilyMany(String s) {
        regexp.append("(");
        regexp.append(s);
        regexp.append(")");
        regexp.append("*?");
        return this;
    }

    public RegExpBuilder sequence(String s) {
        regexp.append(s);
        return this;
    }

    public RegExpBuilder followedBy(RegExpBuilder s) {
        return followedBy(s.toString());
    }

    public RegExpBuilder followedBy(String s) {
        return sequence(s);
    }

    public RegExpBuilder until(String s) {
        return sequence(s);
    }

    public Matcher in(String s) {
        return Pattern.compile(regexp.toString(), Pattern.CASE_INSENSITIVE).matcher(s);
    }

    public Matcher inMultiline(String s) {
        return Pattern.compile(regexp.toString(), Pattern.CASE_INSENSITIVE | Pattern.MULTILINE).matcher(s);
    }

    @Override
    public String toString() {
        return regexp.toString();
    }
}
