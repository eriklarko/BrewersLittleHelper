/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.types.Yeast;
import org.blh.beerxml.types.Yeast.YEAST_FLOCCULATION;
import org.blh.beerxml.types.Yeast.YEAST_FORM;
import org.blh.beerxml.types.Yeast.YEAST_TYPE;
import org.blh.core.units.Percentage;
import org.blh.core.units.temperature.Celsius;

/**
 *
 * @author thinner
 */
public interface YeastBuilder extends Builder<Yeast> {

    YeastBuilder setAddToSecondary(boolean addToSecondary);

    YeastBuilder setAmount(double amount);

    YeastBuilder setAmountIsWeight(boolean amountIsWeight);

    YeastBuilder setAttenuation(Percentage attenuation);

    YeastBuilder setBestFor(String bestFor);

    YeastBuilder setFlocculation(YEAST_FLOCCULATION flocculation);

    YeastBuilder setForm(YEAST_FORM form);

    YeastBuilder setLaboratory(String laboratory);

    YeastBuilder setMaxReuse(int maxReuse);

    YeastBuilder setMaxTemperature(Celsius maxTemperature);

    YeastBuilder setMinTemperature(Celsius minTemperature);

    YeastBuilder setName(String name);

    YeastBuilder setNotes(String notes);

    YeastBuilder setProductId(String productId);

    YeastBuilder setTimesCultured(int timesCultured);

    YeastBuilder setType(YEAST_TYPE type);
    
}
