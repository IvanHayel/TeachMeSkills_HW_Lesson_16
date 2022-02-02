package com.teachmeskills.task.util.analyzer.sax;

import com.teachmeskills.task.model.author.Author;
import com.teachmeskills.task.model.sonnet.Sonnet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Year;

public class SaxParsing extends DefaultHandler {
    private final Author author = new Author();
    private final Sonnet sonnet = new Sonnet();

    private boolean dealingWithLastName = false;
    private boolean dealingWithFirstName = false;
    private boolean dealingWithNationality = false;
    private boolean dealingWithYearOfBirth = false;
    private boolean dealingWithYearOfDeath = false;
    private boolean dealingWithTitle = false;
    private boolean dealingWithLine = false;

    public Sonnet parseSonnet(String sonnetXMLPath) {
        try {
            File xmlFile = Paths.get(sonnetXMLPath).toFile();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFile, this);
            sonnet.setAuthor(author);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return sonnet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "sonnet" -> sonnet.setType(attributes.getValue(0));
            case "lastName" -> dealingWithLastName = true;
            case "firstName" -> dealingWithFirstName = true;
            case "nationality" -> dealingWithNationality = true;
            case "yearOfBirth" -> dealingWithYearOfBirth = true;
            case "yearOfDeath" -> dealingWithYearOfDeath = true;
            case "title" -> dealingWithTitle = true;
            case "line" -> dealingWithLine = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        if (dealingWithLine)
            sonnet.addSonnetLine(String.valueOf(ch, start, length));
        if (dealingWithLastName)
            author.setLastName(String.valueOf(ch, start, length));
        if (dealingWithFirstName)
            author.setFirstName(String.valueOf(ch, start, length));
        if (dealingWithNationality)
            author.setNationality(String.valueOf(ch, start, length));
        if (dealingWithYearOfBirth)
            author.setYearOfBirth(Year.parse(String.valueOf(ch, start, length)));
        if (dealingWithYearOfDeath)
            author.setYearOfDeath(Year.parse(String.valueOf(ch, start, length)));
        if (dealingWithTitle)
            sonnet.setTitle(String.valueOf(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        switch (qName) {
            case "lastName" -> dealingWithLastName = false;
            case "firstName" -> dealingWithFirstName = false;
            case "nationality" -> dealingWithNationality = false;
            case "yearOfBirth" -> dealingWithYearOfBirth = false;
            case "yearOfDeath" -> dealingWithYearOfDeath = false;
            case "title" -> dealingWithTitle = false;
            case "line" -> dealingWithLine = false;
        }
    }
}