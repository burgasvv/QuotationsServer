package com.burgas.practice2.quotation;

import java.util.Objects;

/**
 * The type Quotation.
 */
public class Quotation {

    private String quotation;
    private String author;

    /**
     * Constructor with parameters. Instantiates a new Quotation.
     *
     * @param quotation the quotation
     * @param author the author
     */
    public Quotation(String quotation, String author) {
        setQuotation(quotation);
        setAuthor(author);
    }

    public String getQuotation() {
        return quotation;
    }

    private void setQuotation(String quotation) {
        quotation = quotation.replace("\"", "");
        quotation = quotation.replace(".", "");
        quotation = quotation.replace("'", "");
        this.quotation = STR."\{'\"'}\{quotation}\{'\"'}.";
    }

    public String getAuthor() {
        return author;
    }

    private void setAuthor(String author) {
        author = author.replace("(c)", "");
        author = author.replace("(C)", "");
        author = author.replace(".", "");
        this.author = STR."(c)\{author}.";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Quotation quotation1 = (Quotation) object;
        return Objects.equals(quotation, quotation1.quotation) && Objects.equals(author, quotation1.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quotation, author);
    }

    @Override
    public String toString() {
        return STR."Quotation: \{quotation} \{author}";
    }
}
