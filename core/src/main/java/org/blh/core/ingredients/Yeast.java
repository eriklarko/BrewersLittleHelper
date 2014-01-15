package org.blh.core.ingredients;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class Yeast {

	private final String name;
	private final String manufacturer;
	private final String attenuation;

	public Yeast(String name, String manufacturer, String attenuation) {
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

	public String getAttenuation() {
		return attenuation;
	}
}
