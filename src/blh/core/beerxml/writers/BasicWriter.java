package blh.core.beerxml.writers;

import blh.core.beerxml.BeerXMLWriter;
import blh.core.beerxml.ClassToRecordNameMapper;
import blh.core.beerxml.UnknownRecordSetException;
import blh.core.beerxml.types.BeerXMLRecord;
import blh.core.beerxml.types.BeerXMLRecordSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Erik Larkö
 * @since May 21, 2013 9:28:03 PM
 */
public class BasicWriter implements BeerXMLWriter<BeerXMLRecord> {

    private ClassToRecordNameMapper recordNameMapper;

    public BasicWriter(ClassToRecordNameMapper recordNameMapper) {
        this.recordNameMapper = recordNameMapper;
    }

    @Override
    public void write(File file, List<BeerXMLRecordSet<BeerXMLRecord>> recordSets) throws IOException, UnknownRecordSetException {
        List<String> xml = new LinkedList<>();
        for (BeerXMLRecordSet<BeerXMLRecord> recordSet : recordSets) {
            xml.addAll(transform(recordSet));
        }

        write(xml, file);
    }

    private List<String> transform(BeerXMLRecordSet recordSet) throws UnknownRecordSetException {
        List<BeerXMLRecord> records = recordSet.getRecords();
        String recordSetName = this.recordNameMapper.getRecordSetName(recordSet);

        List<String> recordSetXML = new LinkedList<>();
        recordSetXML.add("<" + recordSetName + ">");
        for (BeerXMLRecord record : records) {
            if (record == null) {
                continue;
            }
            recordSetXML.addAll(transform(record));
        }
        recordSetXML.add("</" + recordSetName + ">");

        return recordSetXML;
    }

    private List<String> transform(BeerXMLRecord record) throws UnknownRecordSetException {
        String recordTag = this.recordNameMapper.getRecordName(record);
        List<String> recordNodeXML = new LinkedList<>();
        recordNodeXML.add("<" + recordTag + ">");

        for (Map.Entry<String, String> tagAndValue : record.getBeerXMLTagsAndValues().entrySet()) {
            if (tagAndValue.getValue() != null) {
                String tag = "<" + tagAndValue.getKey() + ">";
                tag += tagAndValue.getValue();
                tag += "</" + tagAndValue.getKey() + ">";

                recordNodeXML.add(tag);
            }
        }

        List<BeerXMLRecord> subRecords = record.getSubRecords();
        if (subRecords != null) {
            for (BeerXMLRecord subRecord : subRecords) {
                if (subRecord != null) {
                    recordNodeXML.addAll(transform(subRecord));
                }
            }
        }

        List<BeerXMLRecordSet> subRecordSets = record.getSubRecordSets();
        if (subRecordSets != null) {
            for (BeerXMLRecordSet subRecordSet : subRecordSets) {
                recordNodeXML.addAll(transform(subRecordSet));
            }
        }

        recordNodeXML.add("</" + recordTag + ">");
        return recordNodeXML;
    }

    private void write(List<String> xml, File file) throws IOException {
        String indentation = "    ";
        String currentIndentation = "";

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            for (String row : xml) {
                if (isClosingTag(row)) {
                    currentIndentation = currentIndentation.substring(0, currentIndentation.length() - indentation.length());
                }

                writer.write(currentIndentation + row + System.lineSeparator());

                if (isOpeningTag(row)) {
                    currentIndentation += indentation;
                }
            }
        }
    }

    private boolean isOpeningTag(String row) {
        return !row.contains("/");
    }

    private boolean isClosingTag(String row) {
        return row.charAt(1) == '/';
    }
}
