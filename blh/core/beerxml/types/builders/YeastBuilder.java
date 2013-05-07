/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Yeast;
import blh.core.beerxml.types.Yeast.FLOCCULATION;
import blh.core.beerxml.types.Yeast.FORM;
import blh.core.beerxml.types.Yeast.TYPE;
import blh.core.units.Percentage;
import blh.core.units.temperature.Celcius;

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

    YeastBuilder setFlocculation(FLOCCULATION flocculation);

    YeastBuilder setForm(FORM form);

    YeastBuilder setLaboratory(String laboratory);

    YeastBuilder setMaxReuse(int maxReuse);

    YeastBuilder setMaxTemperature(Celcius maxTemperature);

    YeastBuilder setMinTemperature(Celcius minTemperature);

    YeastBuilder setName(String name);

    YeastBuilder setNotes(String notes);

    YeastBuilder setProductId(String productId);

    YeastBuilder setTimesCultured(int timesCultured);

    YeastBuilder setType(TYPE type);
    
}
