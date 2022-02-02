package com.teachmeskills.task.model.sonnet;

import com.teachmeskills.task.model.author.Author;

import java.util.ArrayList;
import java.util.List;

public class Sonnet {
    private final List<String> sonnetLines;

    private String type;
    private Author author;
    private String title;

    public Sonnet() {
        sonnetLines = new ArrayList<>();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void addSonnetLine(String line) {
        sonnetLines.add(line);
    }

    public List<String> getSonnetLines() {
        return sonnetLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sonnet sonnet = (Sonnet) o;

        if (!type.equals(sonnet.type)) return false;
        if (!author.equals(sonnet.author)) return false;
        if (!title.equals(sonnet.title)) return false;
        return sonnetLines.equals(sonnet.sonnetLines);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + sonnetLines.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Sonnet{" +
                "type='" + type + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", sonnetLines=" + sonnetLines +
                '}';
    }
}