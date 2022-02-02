package com.teachmeskills.task.util.writer;

import com.teachmeskills.task.model.author.Author;
import com.teachmeskills.task.model.sonnet.Sonnet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SonnetWriter {
    private static final Logger LOG = Logger.getLogger("logger");

    private final Sonnet sonnet;
    private final Author author;
    private final List<String> sonnetLines;

    public SonnetWriter(Sonnet sonnet) {
        this.sonnet = sonnet;
        author = sonnet.getAuthor();
        sonnetLines = sonnet.getSonnetLines();
    }

    public void writeTXT(Path directory) {
        if (Files.notExists(directory)) {
            try {
                Files.createDirectory(directory);
                LOG.log(Level.INFO, () -> directory + " created!");
            } catch (IOException e) {
                LOG.log(Level.WARNING, "Directory creation denied!");
                e.printStackTrace();
            }
        }
        if (Files.isDirectory(directory)) {
            String fileName = author.getFirstName() + "_" + author.getLastName() + "_" + sonnet.getTitle() + ".txt";
            Path filePath = directory.resolve(fileName);
            if (!Files.notExists(filePath)) {
                LOG.log(Level.INFO, () -> filePath + " already exist!");
                return;
            }
            try {
                Files.createFile(filePath);
                for (String line : sonnetLines) {
                    Files.writeString(filePath, line + "\n", StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                LOG.log(Level.WARNING, () -> filePath + " wrong!");
                e.printStackTrace();
            }
        } else {
            LOG.log(Level.WARNING, () -> directory + " is not directory!");
            throw new InputMismatchException();
        }
    }
}