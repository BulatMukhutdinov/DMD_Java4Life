package com.innopolis.courses.dmd.premasters.java4life;
/**
 * @author Nikolay Yushkevich,
 *         created on 12.09.2015
 */
public class Record {
    private String mdate;
    private String key;
    private String author;
    private String title; // could be long
    private String pages;
    private int year;
    private int volume;
    private String journal;
    private int number;
    private String path;
    private String doi;

    /**
     * <p>Creates new record of an article</p>
     *
     * @param mdate The date (String datatype)
     * @param key  The key, which is in the article tag
     * @param author  The author
     * @param title  The title
     * @param pages  The number of pages (interval)
     * @param year  The year of issue (int datatype)
     * @param volume  The volume (int datatype)
     * @param number  The number of volume in this year (interval)
     * @param path  The link of DBLP
     * @param doi  The link in DOI system
     *
     */
    public Record(String mdate, String key, String author, String title, String pages, int year, int volume, String journal, int number, String path, String doi) {
        this.mdate = mdate;
        this.key = key;
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.year = year;
        this.volume = volume;
        this.journal = journal;
        this.number = number;
        this.path = path;
        this.doi = doi;

    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Record{" +
                "mdate=" + mdate +
                ", key='" + key + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pages='" + pages + '\'' +
                ", year=" + year +
                ", volume=" + volume +
                ", journal='" + journal + '\'' +
                ", number=" + number +
                ", doi='" + doi + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (getYear() != record.getYear()) return false;
        if (getVolume() != record.getVolume()) return false;
        if (getNumber() != record.getNumber()) return false;
        if (!getMdate().equals(record.getMdate())) return false;
        if (!getKey().equals(record.getKey())) return false;
        if (!getAuthor().equals(record.getAuthor())) return false;
        if (!getTitle().equals(record.getTitle())) return false;
        if (!getPages().equals(record.getPages())) return false;
        if (!getJournal().equals(record.getJournal())) return false;
        if (!getPath().equals(record.getPath())) return false;
        return getDoi().equals(record.getDoi());

    }

    @Override
    public int hashCode() {
        int result = getMdate().hashCode();
        result = 31 * result + getKey().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getPages().hashCode();
        result = 31 * result + getYear();
        result = 31 * result + getVolume();
        result = 31 * result + getJournal().hashCode();
        result = 31 * result + getNumber();
        result = 31 * result + getPath().hashCode();
        result = 31 * result + getDoi().hashCode();
        return result;
    }
}
