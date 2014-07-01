package se.angstroms.blh.anders.context.value.annot;

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
@Target(ElementType.FIELD)
public @interface ValueAnnot {

    public Value.Id id();
}
