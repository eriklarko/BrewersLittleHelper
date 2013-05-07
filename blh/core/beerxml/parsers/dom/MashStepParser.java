package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.types.MashStep;
import blh.core.beerxml.types.builders.MashStepBuilder;

/**
 *
 * @author thinner
 */
public class MashStepParser extends RecordSetParser<MashStep> {

    public MashStepParser(MashStepBuilder builder) {
        super(builder);
    }
}
