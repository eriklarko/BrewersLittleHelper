package se.angstroms.blh.anders.context.value;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.blh.core.unit.DoubleUnit;
import org.blh.core.unit.Unit;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.volume.Liters;

/**
 * Turns Units into Strings. Used when outputting values to the GUI
 *
 * @author eriklark
 */
public class UnitStringFormatter {

	public String format(Value<? extends Unit<?>> value) {
        return format(value.get());
    }

	public String format(Unit<?> unit) {

		if (unit instanceof SpecificGravity) {
			SpecificGravity sg = (SpecificGravity) unit;
			return numberFormatted("0.000", sg.value());
		}

        if (unit instanceof ABV) {
            ABV abv = (ABV) unit;
            return numberFormatted("0.0#", abv.value().value()) + "%";
        }

		if (unit instanceof DoubleUnit) {
			DoubleUnit du = (DoubleUnit) unit;
			return numberFormatted("0.##", du.value());
		}


		return String.valueOf(unit);
	}

	private String numberFormatted(String pattern, Object value) {
		NumberFormat nf = new DecimalFormat(pattern);
		return nf.format(value);
	}

	public String getUnitName(Class<? extends Unit<?>> unitClass) {
		if (unitClass == null) {
			return "unknown unit";
		}

		if (unitClass.isAssignableFrom(IBU.class)) {
			return "IBU";
		}

		if (unitClass.isAssignableFrom(ABV.class)) {
			return "ABV";
		}

		if (unitClass.isAssignableFrom(Liters.class)) {
			return "Liter";
		}

		return unitClass.getSimpleName();
	}
}
