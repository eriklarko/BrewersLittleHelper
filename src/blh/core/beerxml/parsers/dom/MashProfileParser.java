package blh.core.beerxml.parsers.dom;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.types.BeerXMLRecordSet;
import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.MashStep;
import blh.core.beerxml.types.builders.MashProfileBuilder;
import blh.core.beerxml.types.builders.MashStepBuilder;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author thinner
 */
public class MashProfileParser extends RecordSetParser<MashProfile> {

    private MashProfileBuilder builder;
    private RecordSetParser<MashStep> stepParser;

    public MashProfileParser(MashProfileBuilder profileBuilder, RecordSetParser<MashStep> stepParser) {
        super(profileBuilder);
        this.builder = profileBuilder;
        this.stepParser = stepParser;
    }

    @Override
    protected MashProfile parseType(NodeList values) throws ParseException {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);
            if (node.getNodeName().equals("MASH_STEPS")) {
                BeerXMLRecordSet<MashStep> steps = stepParser.parse(node.getChildNodes());
                builder.setMashSteps(steps.getRecords());
            } else {
                builder.set(node.getNodeName(), node.getNodeValue());
            }
        }
        return builder.create();

    }
}
