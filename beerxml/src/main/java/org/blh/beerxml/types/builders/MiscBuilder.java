/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.types.Misc;
import org.blh.beerxml.types.Misc.MISC_TYPE;
import org.blh.beerxml.types.Misc.MISC_USE;
import org.blh.core.units.time.Minutes;

/**
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
