package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.Misc;
import org.blh.beerxml.type.Misc.MISC_TYPE;
import org.blh.beerxml.type.Misc.MISC_USE;
import org.blh.core.unit.time.Minutes;

/**
 * Builder for the Misc type.
 *
 * @author thinner
 */
public interface MiscBuilder extends Builder<Misc> {

    MiscBuilder setAmount(double amount);

    MiscBuilder setAmountIsWeight(boolean amountIsWeight);

    MiscBuilder setName(String name);

    MiscBuilder setNotes(String notes);

    MiscBuilder setTime(Minutes time);

    MiscBuilder setType(MISC_TYPE type);

    MiscBuilder setUse(MISC_USE use);

    MiscBuilder setUseFor(String useFor);
}
