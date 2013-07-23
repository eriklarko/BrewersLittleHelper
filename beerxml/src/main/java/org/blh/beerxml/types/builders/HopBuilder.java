/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.types.Hop;
import org.blh.beerxml.types.Hop.HOP_FORM;
import org.blh.beerxml.types.Hop.HOP_TYPE;
import org.blh.beerxml.types.Hop.HOP_USE;
import org.blh.core.units.Percentage;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.weight.Kilograms;

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

    HopBuilder setForm(HOP_FORM form);

    HopBuilder setHopStabilityIndex(Percentage hopStabilityIndex);

    HopBuilder setHumulene(Percentage humulene);

    HopBuilder setMyrcene(Percentage myrcene);

    HopBuilder setName(String name);

    HopBuilder setNotes(String notes);

    HopBuilder setOrigin(String origin);

    HopBuilder setSubstitutes(String substitutes);

    HopBuilder setTime(Minutes time);

    HopBuilder setType(HOP_TYPE type);

    HopBuilder setUse(HOP_USE use);
    
}
