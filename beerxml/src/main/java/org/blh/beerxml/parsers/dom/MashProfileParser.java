package org.blh.beerxml.parsers.dom;

import org.blh.beerxml.ParseException;
import org.blh.beerxml.types.BeerXMLRecordSet;
import org.blh.beerxml.types.MashProfile;
import org.blh.beerxml.types.MashStep;
import org.blh.beerxml.types.builders.MashProfileBuilder;
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
    public MashProfile parseRecord(NodeList values) throws ParseException {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);
            
            if(node.getNodeType() != Node.ELEMENT_NODE) {
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
