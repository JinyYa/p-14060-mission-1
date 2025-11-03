package org.back;

public class Proverb {
    String proverb;
    String author;
    int id;

    public Proverb(String proverb, String author, int i) {
        this.proverb = proverb;
        this.author = author;
        id = i;
    }

    public int getId() {
        return id;
    }
    public String getProverb() {
        return proverb;
    }
    public String getAuthor() {
        return author;
    }
}

