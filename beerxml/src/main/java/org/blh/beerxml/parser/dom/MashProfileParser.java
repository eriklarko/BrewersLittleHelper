package org.blh.beerxml.parser.dom;

import org.blh.beerxml.ParseException;
import org.blh.beerxml.type.BeerXMLRecordSet;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.MashStep;
import org.blh.beerxml.type.builder.MashProfileBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Parses the MashProfile record set.
 *
 * @author thinner
 */
public class MashProfileParser extends RecordSetParser<MashProfile> {

    private final MashProfileBuilder builder;
    private final RecordSetParser<MashStep> stepParser;

    public MashProfileParser(MashProfileBuilder profileBuilder, RecordSetParser<MashStep> stepParser) {
        super(profileBuilder);
        this.builder = profileBuilder;
        this.stepParser = stepParser;
    }

    @Override
    public MashProfile parseRecord(NodeList values) throws ParseException {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (node.getNodeName().equals("MASH_STEPS")) {
                BeerXMLRecordSet<MashStep> steps = stepParser.parseRecordSet(node.getChildNodes());
                builder.setMashSteps(steps.getRecords());
            } else {
                builder.set(node.getNodeName(), node.getTextContent());
            }
        }
        return builder.create();

    }
}
