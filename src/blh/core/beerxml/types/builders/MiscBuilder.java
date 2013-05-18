/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Misc;
import blh.core.beerxml.types.Misc.TYPE;
import blh.core.beerxml.types.Misc.USE;
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

    MiscBuilder setType(TYPE type);

    MiscBuilder setUse(USE use);

    MiscBuilder setUseFor(String useFor);
    
}
