/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Misc;
import blh.core.beerxml.types.Misc.MISC_TYPE;
import blh.core.beerxml.types.Misc.MISC_USE;
import blh.core.units.time.Minutes;

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
