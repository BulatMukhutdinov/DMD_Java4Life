package com.innopolis.courses.dmd.premasters.java4life;

import java.util.*;

public class Record {
    private String mdate;
    private String key;
    private String publtype;
    private String reviewid;
    private String rating;
    private Set<String> authors;
    private String editor;
    private String title; // could be long
    private String booktitle;
    private String pages;
    private String year;
    private String address;
    private String volume;
    private String journal;
    private String number;
    private String month;
    private String url;
    private String ee;
    private String cdrom;
    private String cite;
    private String publisher;
    private String note;
    private String crossref;
    private String isbn;
    private String series;
    private String school;
    private String chapter;

    public Record() {
        this.authors = new HashSet<String>();
        this.title = "";
    }

    /**
     * <p>Creates new record of an article</p>
     *
     * @param mdate   The date (String datatype)
     * @param key     The key, which is in the article tag
     * @param authors The author
     * @param title   The title
     * @param pages   The number of pages (interval)
     * @param year    The year of issue (int datatype)
     * @param volume  The volume (int datatype)
     * @param number  The number of volume in this year (interval)
     * @param url     The link of DBLP
     * @param ee      The link in DOI system
     */
    public Record(String mdate, String key, String publtype, String reviewid, String rating, Set<String> authors, String editor, String title, String booktitle, String pages, String year, String address, String volume, String journal, String number, String month, String url, String ee, String cdrom, String cite, String publisher, String note, String crossref, String isbn, String series, String school, String chapter) {
        this.mdate = mdate;
        this.key = key;
        this.publtype = publtype;
        this.reviewid = reviewid;
        this.rating = rating;
        this.authors = authors;
        this.editor = editor;
        this.title = title;
        this.booktitle = booktitle;
        this.pages = pages;
        this.year = year;
        this.address = address;
        this.volume = volume;
        this.journal = journal;
        this.number = number;
        this.month = month;
        this.url = url;
        this.ee = ee;
        this.cdrom = cdrom;
        this.cite = cite;
        this.publisher = publisher;
        this.note = note;
        this.crossref = crossref;
        this.isbn = isbn;
        this.series = series;
        this.school = school;
        this.chapter = chapter;
    }

    public void appendTitle(String append) {
        append = append.replaceAll("'", "''");
        this.title += append;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate.replaceAll("'", "''");
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key.replaceAll("'", "''");
    }

    public String getPubltype() {
        return publtype;
    }

    public void setPubltype(String publtype) {
        this.publtype = publtype.replaceAll("'", "''");
    }

    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid.replaceAll("'", "''");
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        rating = rating.replaceAll("'", "''");
        this.rating = rating;
    }

    public String [] getAuthors() {
        String auth[] = authors.toArray(new String[authors.size()]);
        return auth;
    }

    public void addAuthor(String auth){
        authors.add(auth);
    }
    public void setAuthors(Set<String> authors) {
        this.authors = new HashSet<String>(authors);
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        editor = editor.replaceAll("'", "''");
        this.editor = editor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title.replaceAll("'", "''");
        this.title = title;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle.replaceAll("'", "''");
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages.replaceAll("'", "''");
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year.replaceAll("'", "''");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.replaceAll("'", "''");
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume.replaceAll("'", "''");
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal.replaceAll("'", "''");
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number.replaceAll("'", "''");
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month.replaceAll("'", "''");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url.replaceAll("'", "''");
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee.replaceAll("'", "''");
    }

    public String getCdrom() {
        return cdrom;
    }

    public void setCdrom(String cdrom) {
        this.cdrom = cdrom.replaceAll("'", "''");
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite.replaceAll("'", "''");
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher.replaceAll("'", "''");
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note.replaceAll("'", "''");
    }

    public String getCrossref() {
        return crossref;
    }

    public void setCrossref(String crossref) {
        this.crossref = crossref.replaceAll("'", "''");
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn.replaceAll("'", "''");
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series.replaceAll("'", "''");
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school.replaceAll("'", "''");
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter.replaceAll("'", "''");
    }

    @Override
    public String toString() {
        return "Record{" +
                "mdate='" + mdate + '\'' +
                ", key='" + key + '\'' +
                ", publtype='" + publtype + '\'' +
                ", reviewid='" + reviewid + '\'' +
                ", rating='" + rating + '\'' +
                ", authors=" + authors +
                ", editor='" + editor + '\'' +
                ", title='" + title + '\'' +
                ", booktitle='" + booktitle + '\'' +
                ", pages='" + pages + '\'' +
                ", year='" + year + '\'' +
                ", address='" + address + '\'' +
                ", volume='" + volume + '\'' +
                ", journal='" + journal + '\'' +
                ", number='" + number + '\'' +
                ", month='" + month + '\'' +
                ", url='" + url + '\'' +
                ", ee='" + ee + '\'' +
                ", cdrom='" + cdrom + '\'' +
                ", cite='" + cite + '\'' +
                ", publisher='" + publisher + '\'' +
                ", note='" + note + '\'' +
                ", crossref='" + crossref + '\'' +
                ", isbn='" + isbn + '\'' +
                ", series='" + series + '\'' +
                ", school='" + school + '\'' +
                ", chapter='" + chapter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (!getMdate().equals(record.getMdate())) return false;
        if (!getKey().equals(record.getKey())) return false;
        if (getPubltype() != null ? !getPubltype().equals(record.getPubltype()) : record.getPubltype() != null)
            return false;
        if (getReviewid() != null ? !getReviewid().equals(record.getReviewid()) : record.getReviewid() != null)
            return false;
        if (getRating() != null ? !getRating().equals(record.getRating()) : record.getRating() != null) return false;
        if (getAuthors() != null ? !getAuthors().equals(record.getAuthors()) : record.getAuthors() != null)
            return false;
        if (getEditor() != null ? !getEditor().equals(record.getEditor()) : record.getEditor() != null) return false;
        if (!getTitle().equals(record.getTitle())) return false;
        if (getBooktitle() != null ? !getBooktitle().equals(record.getBooktitle()) : record.getBooktitle() != null)
            return false;
        if (getPages() != null ? !getPages().equals(record.getPages()) : record.getPages() != null) return false;
        if (getYear() != null ? !getYear().equals(record.getYear()) : record.getYear() != null) return false;
        if (getAddress() != null ? !getAddress().equals(record.getAddress()) : record.getAddress() != null)
            return false;
        if (getVolume() != null ? !getVolume().equals(record.getVolume()) : record.getVolume() != null) return false;
        if (getJournal() != null ? !getJournal().equals(record.getJournal()) : record.getJournal() != null)
            return false;
        if (getNumber() != null ? !getNumber().equals(record.getNumber()) : record.getNumber() != null) return false;
        if (getMonth() != null ? !getMonth().equals(record.getMonth()) : record.getMonth() != null) return false;
        if (getUrl() != null ? !getUrl().equals(record.getUrl()) : record.getUrl() != null) return false;
        if (getEe() != null ? !getEe().equals(record.getEe()) : record.getEe() != null) return false;
        if (getCdrom() != null ? !getCdrom().equals(record.getCdrom()) : record.getCdrom() != null) return false;
        if (getCite() != null ? !getCite().equals(record.getCite()) : record.getCite() != null) return false;
        if (getPublisher() != null ? !getPublisher().equals(record.getPublisher()) : record.getPublisher() != null)
            return false;
        if (getNote() != null ? !getNote().equals(record.getNote()) : record.getNote() != null) return false;
        if (getCrossref() != null ? !getCrossref().equals(record.getCrossref()) : record.getCrossref() != null)
            return false;
        if (getIsbn() != null ? !getIsbn().equals(record.getIsbn()) : record.getIsbn() != null) return false;
        if (getSeries() != null ? !getSeries().equals(record.getSeries()) : record.getSeries() != null) return false;
        if (getSchool() != null ? !getSchool().equals(record.getSchool()) : record.getSchool() != null) return false;
        return !(getChapter() != null ? !getChapter().equals(record.getChapter()) : record.getChapter() != null);

    }

    @Override
    public int hashCode() {
        int result = getMdate().hashCode();
        result = 31 * result + getKey().hashCode();
        result = 31 * result + (getPubltype() != null ? getPubltype().hashCode() : 0);
        result = 31 * result + (getReviewid() != null ? getReviewid().hashCode() : 0);
        result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
        result = 31 * result + (getAuthors() != null ? getAuthors().hashCode() : 0);
        result = 31 * result + (getEditor() != null ? getEditor().hashCode() : 0);
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + (getBooktitle() != null ? getBooktitle().hashCode() : 0);
        result = 31 * result + (getPages() != null ? getPages().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getVolume() != null ? getVolume().hashCode() : 0);
        result = 31 * result + (getJournal() != null ? getJournal().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getMonth() != null ? getMonth().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getEe() != null ? getEe().hashCode() : 0);
        result = 31 * result + (getCdrom() != null ? getCdrom().hashCode() : 0);
        result = 31 * result + (getCite() != null ? getCite().hashCode() : 0);
        result = 31 * result + (getPublisher() != null ? getPublisher().hashCode() : 0);
        result = 31 * result + (getNote() != null ? getNote().hashCode() : 0);
        result = 31 * result + (getCrossref() != null ? getCrossref().hashCode() : 0);
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + (getSeries() != null ? getSeries().hashCode() : 0);
        result = 31 * result + (getSchool() != null ? getSchool().hashCode() : 0);
        result = 31 * result + (getChapter() != null ? getChapter().hashCode() : 0);
        return result;
    }
}

