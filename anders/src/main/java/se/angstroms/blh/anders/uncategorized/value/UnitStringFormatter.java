package se.angstroms.blh.anders.uncategorized.value;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.blh.core.unit.DoubleUnit;
import org.blh.core.unit.Unit;
import org.blh.core.unit.gravity.SpecificGravity;

/**
 * Turns Units into Strings. Used when outputting values to the GUI
 *
 * @author eriklark
 */
public class UnitStringFormatter {

	public String format(Unit<?> unit) {

		if (unit instanceof SpecificGravity) {
			SpecificGravity sg = (SpecificGravity) unit;
			return numberFormatted("0.000", sg.value());
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
}
