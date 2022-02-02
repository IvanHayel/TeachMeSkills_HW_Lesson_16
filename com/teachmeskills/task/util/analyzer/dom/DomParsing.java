package com.teachmeskills.task.util.analyzer.dom;

import com.teachmeskills.task.model.author.Author;
import com.teachmeskills.task.model.sonnet.Sonnet;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Year;

public class DomParsing {
    private final Author author = new Author();
    private final Sonnet sonnet = new Sonnet();

    public Sonnet parseSonnet(String sonnetXMLPath) {
        try {
            File xmlFile = Paths.get(sonnetXMLPath).toFile();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            extractInformation(document);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        sonnet.setAuthor(author);
        return sonnet;
    }

    private void extractInformation(Document document) {
        document.getDocumentElement().normalize();

        String lastName = document.getElementsByTagName("lastName").item(0).getTextContent();
        author.setLastName(lastName);
        String firstName = document.getElementsByTagName("firstName").item(0).getTextContent();
        author.setFirstName(firstName);
        String nationality = document.getElementsByTagName("nationality").item(0).getTextContent();
        author.setNationality(nationality);
        Year yearOfBirth = Year.parse(document.getElementsByTagName("yearOfBirth").item(0).getTextContent());
        author.setYearOfBirth(yearOfBirth);
        Year yearOfDeath = Year.parse(document.getElementsByTagName("yearOfDeath").item(0).getTextContent());
        author.setYearOfDeath(yearOfDeath);

        String type = document.getDocumentElement().getAttribute("type");
        sonnet.setType(type);
        String title = document.getElementsByTagName("title").item(0).getTextContent();
        sonnet.setTitle(title);
        NodeList sonnetLines = document.getElementsByTagName("line");
        for (int counter = 0; counter < sonnetLines.getLength(); counter++) {
            sonnet.addSonnetLine(sonnetLines.item(counter).getTextContent());
        }
    }
}