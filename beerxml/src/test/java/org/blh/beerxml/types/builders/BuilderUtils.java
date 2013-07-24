package org.blh.beerxml.types.builders;

import java.util.Map;
import org.blh.core.units.Unit;

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
