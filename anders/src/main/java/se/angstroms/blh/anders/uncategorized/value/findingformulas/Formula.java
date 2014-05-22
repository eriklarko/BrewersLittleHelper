package se.angstroms.blh.anders.uncategorized.value.findingformulas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import se.angstroms.blh.anders.uncategorized.value.ValueId;

/**
 *
 * @author eriklark
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Formula {

    public ValueId calculates() default ValueId.NOTHING;

    public ValueId[] calculatesMany() default ValueId.NOTHING;
}
