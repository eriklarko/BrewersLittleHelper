package blh.core.beerxml.parsers.dom;

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
    private MashStepParser stepParser;

    public MashProfileParser(MashProfileBuilder profileBuilder, MashStepParser stepParser) {
        super(profileBuilder);
        this.builder = profileBuilder;
        this.stepParser = stepParser;
    }

    @Override
    protected MashProfile parseType(NodeList values) {
        for (int i = 0; i < values.getLength(); i++) {
            Node node = values.item(i);
            if (node.getNodeName().equals("MASH_STEPS")) {
                List<MashStep> steps = stepParser.parse(node.getChildNodes());
                builder.setMashSteps(steps);
            } else {
                builder.set(node.getNodeName(), node.getNodeValue());
            }
        }
        return builder.create();

    }
}
