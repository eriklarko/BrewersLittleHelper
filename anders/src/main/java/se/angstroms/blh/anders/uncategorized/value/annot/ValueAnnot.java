package se.angstroms.blh.anders.uncategorized.value.annot;

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
@Target(ElementType.FIELD)
public @interface ValueAnnot {

    public ValueId id();
}
