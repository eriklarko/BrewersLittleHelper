package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.Yeast;
import org.blh.beerxml.type.Yeast.YEAST_FLOCCULATION;
import org.blh.beerxml.type.Yeast.YEAST_FORM;
import org.blh.beerxml.type.Yeast.YEAST_TYPE;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.temperature.Celsius;

/**
 * Builder for the Yeast type.
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
