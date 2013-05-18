/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.Hop.FORM;
import blh.core.beerxml.types.Hop.TYPE;
import blh.core.beerxml.types.Hop.USE;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public interface HopBuilder extends Builder<Hop> {

    HopBuilder setAlpha(Percentage alpha);

    HopBuilder setAmount(Kilograms amount);

    HopBuilder setBeta(Percentage beta);

    HopBuilder setCaryophyllene(Percentage caryophyllene);

    HopBuilder setCohumulone(Percentage cohumulone);

    HopBuilder setForm(FORM form);

    HopBuilder setHopStabilityIndex(Percentage hopStabilityIndex);

    HopBuilder setHumulene(Percentage humulene);

    HopBuilder setMyrcene(Percentage myrcene);

    HopBuilder setName(String name);

    HopBuilder setNotes(String notes);

    HopBuilder setOrigin(String origin);

    HopBuilder setSubstitutes(String substitutes);

    HopBuilder setTime(Minutes time);

    HopBuilder setType(TYPE type);

    HopBuilder setUse(USE use);
    
}
