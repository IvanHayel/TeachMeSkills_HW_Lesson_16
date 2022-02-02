package com.teachmeskills.task.model.author;

import java.time.Year;

public final class Author {
    private String lastName;
    private String firstName;
    private String nationality;
    private Year yearOfBirth;
    private Year yearOfDeath;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setYearOfBirth(Year yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setYearOfDeath(Year yearOfDeath) {
        this.yearOfDeath = yearOfDeath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!lastName.equals(author.lastName)) return false;
        if (!firstName.equals(author.firstName)) return false;
        if (!nationality.equals(author.nationality)) return false;
        if (!yearOfBirth.equals(author.yearOfBirth)) return false;
        return yearOfDeath.equals(author.yearOfDeath);
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + nationality.hashCode();
        result = 31 * result + yearOfBirth.hashCode();
        result = 31 * result + yearOfDeath.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", yearOfDeath=" + yearOfDeath +
                '}';
    }
}