package test.blh.core.beerxml.types.builders;

import blh.core.units.Unit;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: thinner
 * Date: 5/16/13
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuilderUtils {
    public static void addTag(Map<String, String> map, String name, String value) {
        map.put(name, value);
    }

    public static void addTag(Map<String, String> map, String name, double value) {
        map.put(name, String.valueOf(value));
    }

    public static void addTag(Map<String, String> map, String name, int value) {
        map.put(name, String.valueOf(value));
    }

    public static void addTag(Map<String, String> map, String name, boolean value) {
        map.put(name, String.valueOf(value));
    }

    public static void addTag(Map<String, String> map, String name, Unit unit) {
        map.put(name, String.valueOf(unit.value()));
    }
}
