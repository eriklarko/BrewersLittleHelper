package org.blh.core.ingredients;

import org.blh.core.units.Percentage;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class Yeast {

	private final String name;
	private final String manufacturer;
	private final Percentage attenuation;

	public Yeast(String name, String manufacturer, Percentage attenuation) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.attenuation = attenuation;
	}

	public String getName() {
		return name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public Percentage getAttenuation() {
		return attenuation;
	}
}
