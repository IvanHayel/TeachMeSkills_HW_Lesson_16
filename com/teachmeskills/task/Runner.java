package com.teachmeskills.task;

import com.teachmeskills.task.model.sonnet.Sonnet;
import com.teachmeskills.task.util.analyzer.dom.DomParsing;
import com.teachmeskills.task.util.analyzer.sax.SaxParsing;
import com.teachmeskills.task.util.writer.SonnetWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {
    private static final String PROPERTY_PATH =
            "src/main/java/com/teachmeskills/task/resources/application.properties";
    private static final Properties PROPS = new Properties();
    private static final Logger LOG = Logger.getLogger("logger");
    private static final SaxParsing SAX = new SaxParsing();
    private static final DomParsing DOM = new DomParsing();

    public static void main(String[] args) {
        loadProperties();
        analyzeXML();
    }

    private static void loadProperties(){
        try(FileInputStream propertiesFile = new FileInputStream(PROPERTY_PATH)) {
            PROPS.load(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void analyzeXML(){
        String sonnetXMLPath = PROPS.getProperty("XMLpath");
        switch(PROPS.getProperty("analyzer")){
            case "1" -> {
                LOG.log(Level.INFO, "Parsing with SAX analyzer started ...");
                Sonnet sonnet = SAX.parseSonnet(sonnetXMLPath);
                LOG.log(Level.INFO, "Parsing with SAX analyzer successfully ended!");
                writeSonnet(sonnet,PROPS.getProperty("TXTDirectorySAX"));
            }
            case "2" -> {
                LOG.log(Level.INFO, "Parsing with DOM analyzer started ...");
                Sonnet sonnet = DOM.parseSonnet(sonnetXMLPath);
                LOG.log(Level.INFO, "Parsing with DOM successfully ended!");
                writeSonnet(sonnet,PROPS.getProperty("TXTDirectoryDOM"));
            }
            default -> LOG.log(Level.WARNING, "Check properties file! (analyzer = 1 or 2)");
        }
    }

    private static void writeSonnet(Sonnet sonnet, String txtDirectory){
        LOG.log(Level.INFO, "Text file writing started ...");
        SonnetWriter sonnetWriter = new SonnetWriter(sonnet);
        Path dir = Paths.get(txtDirectory);
        sonnetWriter.writeTXT(dir);
        LOG.log(Level.INFO, "Text file writing successfully ended!");
    }
}