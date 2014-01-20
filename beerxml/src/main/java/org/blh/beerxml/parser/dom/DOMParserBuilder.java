package org.blh.beerxml.parser.dom;

import org.blh.beerxml.type.Equipment;
import org.blh.beerxml.type.Fermentable;
import org.blh.beerxml.type.Hop;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.MashStep;
import org.blh.beerxml.type.Misc;
import org.blh.beerxml.type.Recipe;
import org.blh.beerxml.type.Style;
import org.blh.beerxml.type.Water;
import org.blh.beerxml.type.Yeast;

/**
 * Builder class for DOMParser.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class DOMParserBuilder {

    private RecordSetParser<Recipe> recipeParser;
    private RecordSetParser<Style> styleParser;
    private RecordSetParser<Equipment> equipmentParser;
    private RecordSetParser<Hop> hopsParser;
    private RecordSetParser<Fermentable> fermentablesParser;
    private RecordSetParser<Misc> miscsParser;
    private RecordSetParser<Yeast> yeastsParser;
    private RecordSetParser<Water> watersParser;
    private RecordSetParser<MashStep> mashStepsParser;
    private RecordSetParser<MashProfile> mashProfileParser;

    public DOMParserBuilder() {
    }

    public DOMParserBuilder setRecipeParser(RecordSetParser<Recipe> recipeParser) {
        this.recipeParser = recipeParser;
        return this;
    }

    public DOMParserBuilder setStyleParser(RecordSetParser<Style> styleParser) {
        this.styleParser = styleParser;
        return this;
    }

    public DOMParserBuilder setEquipmentParser(RecordSetParser<Equipment> equipmentParser) {
        this.equipmentParser = equipmentParser;
        return this;
    }

    public DOMParserBuilder setHopsParser(RecordSetParser<Hop> hopsParser) {
        this.hopsParser = hopsParser;
        return this;
    }

    public DOMParserBuilder setFermentablesParser(RecordSetParser<Fermentable> fermentablesParser) {
        this.fermentablesParser = fermentablesParser;
        return this;
    }

    public DOMParserBuilder setMiscsParser(RecordSetParser<Misc> miscsParser) {
        this.miscsParser = miscsParser;
        return this;
    }

    public DOMParserBuilder setYeastsParser(RecordSetParser<Yeast> yeastsParser) {
        this.yeastsParser = yeastsParser;
        return this;
    }

    public DOMParserBuilder setWatersParser(RecordSetParser<Water> watersParser) {
        this.watersParser = watersParser;
        return this;
    }

    public DOMParserBuilder setMashStepsParser(RecordSetParser<MashStep> mashStepsParser) {
        this.mashStepsParser = mashStepsParser;
        return this;
    }

    public DOMParserBuilder setMashProfileParser(RecordSetParser<MashProfile> mashProfileParser) {
        this.mashProfileParser = mashProfileParser;
        return this;
    }

    public DOMParser createDOMParser() {
        return new DOMParser(recipeParser, styleParser, equipmentParser, hopsParser,
                fermentablesParser, miscsParser, yeastsParser, watersParser, mashStepsParser, mashProfileParser);
    }

}
