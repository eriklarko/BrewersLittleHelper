package se.angstroms.blh.anders.context.value.findingformulas;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import se.angstroms.blh.anders.context.value.Value;

/**
 *
 * @author eriklark
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Formula {

    public Value.Id[] calculates() default Value.Id.NOTHING;
}
