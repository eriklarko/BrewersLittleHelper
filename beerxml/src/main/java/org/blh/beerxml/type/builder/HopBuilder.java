package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.Hop;
import org.blh.beerxml.type.Hop.HOP_FORM;
import org.blh.beerxml.type.Hop.HOP_TYPE;
import org.blh.beerxml.type.Hop.HOP_USE;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.weight.Kilograms;

/**
 * Builder for the Hop type.
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
