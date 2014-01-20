package org.blh.beerxml.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.blh.beerxml.ClassToRecordNameMapper;
import org.blh.beerxml.ClassToRecordNameMapper.NoRecordNameException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;

/**
 * A very simple BeerXML writer.
 *
 * @author Erik Larkö
 * @since May 21, 2013 9:28:03 PM
 */
public class BasicWriter implements BeerXMLWriter<BeerXMLRecord> {

    private final ClassToRecordNameMapper recordNameMapper;

    public BasicWriter(ClassToRecordNameMapper recordNameMapper) {
        this.recordNameMapper = recordNameMapper;
    }

    @Override
    public void write(File file, List<BeerXMLRecordSet<BeerXMLRecord>> recordSets) throws IOException, NoRecordNameException {
        List<String> xml = new LinkedList<>();
        for (BeerXMLRecordSet<BeerXMLRecord> recordSet : recordSets) {
            xml.addAll(transform(recordSet));
        }

        write(xml, file);
    }

    private List<String> transform(BeerXMLRecordSet recordSet) throws NoRecordNameException {
        List<BeerXMLRecord> records = recordSet.getRecords();
        String recordSetName = this.recordNameMapper.getRecordSetName(recordSet);

        List<String> recordSetXML = new LinkedList<>();
        recordSetXML.add(getOpeningTag(recordSetName));
        for (BeerXMLRecord record : records) {
            if (record == null) {
                continue;
            }
            recordSetXML.addAll(transform(record));
        }
        recordSetXML.add(getClosingTag(recordSetName));

        return recordSetXML;
    }

    private List<String> transform(BeerXMLRecord record) throws NoRecordNameException {
        String recordTag = this.recordNameMapper.getRecordName(record);
        List<String> recordNodeXML = new LinkedList<>();
        recordNodeXML.add(getOpeningTag(recordTag));

        transformRecord(record, recordNodeXML);
        transformSubRecords(record, recordNodeXML);
        transformSubRecordSets(record, recordNodeXML);

        recordNodeXML.add(getClosingTag(recordTag));
        return recordNodeXML;
    }

    private void transformRecord(BeerXMLRecord record, List<String> recordNodeXML) {
        for (Map.Entry<String, String> tagAndValue : record.getBeerXMLTagsAndValues().entrySet()) {
            if (tagAndValue.getValue() != null) {
                String tag = getOpeningTag(tagAndValue.getKey());
                tag += tagAndValue.getValue();
                tag += getClosingTag(tagAndValue.getKey());

                recordNodeXML.add(tag);
            }
        }
    }

    private void transformSubRecords(BeerXMLRecord record, List<String> recordNodeXML) throws NoRecordNameException {
        List<BeerXMLRecord> subRecords = record.getSubRecords();
        if (subRecords != null) {
            for (BeerXMLRecord subRecord : subRecords) {
                if (subRecord != null) {
                    recordNodeXML.addAll(transform(subRecord));
                }
            }
        }
    }

    private void transformSubRecordSets(BeerXMLRecord record, List<String> recordNodeXML) throws NoRecordNameException {
        List<BeerXMLRecordSet> subRecordSets = record.getSubRecordSets();
        if (subRecordSets != null) {
            for (BeerXMLRecordSet subRecordSet : subRecordSets) {
                recordNodeXML.addAll(transform(subRecordSet));
            }
        }
    }

    private String getOpeningTag(String tagName) {
        return "<" + tagName + ">";
    }

    private String getClosingTag(String tagName) {
        return "</" + tagName + ">";
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
