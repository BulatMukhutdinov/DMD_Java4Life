package com.innopolis.courses.dmd.premasters.java4life;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record implements Serializable {
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

    public Record(Record record) {
        this.mdate = record.mdate;
        this.key = record.key;
        this.publtype = record.publtype;
        this.reviewid = record.reviewid;
        this.rating = record.rating;
        this.authors = record.authors;
        this.editor = record.editor;
        this.title = record.title;
        this.booktitle = record.booktitle;
        this.pages = record.pages;
        this.year = record.year;
        this.address = record.address;
        this.volume = record.volume;
        this.journal = record.journal;
        this.number = record.number;
        this.month = record.month;
        this.url = record.url;
        this.ee = record.ee;
        this.cdrom = record.cdrom;
        this.cite = record.cite;
        this.publisher = record.publisher;
        this.note = record.note;
        this.crossref = record.crossref;
        this.isbn = record.isbn;
        this.series = record.series;
        this.school = record.school;
        this.chapter = record.chapter;
    }

    public void appendTitle(String append) {
        append = append.replaceAll("'", "''").replaceAll(";", ",").replaceAll("\\\\", "").replaceAll("\"", "");
        this.title += append;
    }

    public String getMdate() {
        mdate = mdate == null ? mdate : mdate.replaceAll("\\\\", "");
        mdate = process(mdate);
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getKey() {
        key = key == null ? key : key.replaceAll("\\\\", "");
        key = process(key);
        return key;
    }

    public void setKey(String key) {
        this.key = key.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getPubltype() {
        publtype = publtype == null ? publtype : publtype.replaceAll("\\\\", "");
        publtype = process(publtype);
        return publtype;
    }

    public void setPubltype(String publtype) {
        this.publtype = publtype.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getReviewid() {
        reviewid = reviewid == null ? reviewid : reviewid.replaceAll("\\\\", "");
        reviewid = process(reviewid);
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getRating() {
        rating = rating == null ? rating : rating.replaceAll("\\\\", "");
        rating = process(rating);
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String[] getAuthors() {
        String auth[] = authors.toArray(new String[authors.size()]);
        return auth;
    }

    public void addAuthor(String auth) {
        auth = auth == null ? auth : auth.replaceAll("\\\\", "");
        auth = process(auth);
        authors.add(auth.replaceAll(";", ","));
    }

    public void setAuthors(Set<String> authors) {
        this.authors = new HashSet<String>(authors);
    }

    public String getEditor() {
        editor = editor == null ? editor : editor.replaceAll("\\\\", "");
        editor = process(editor);
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getTitle() {
        title = title.replaceAll("\\\\", "").replaceAll("\"", "");
        title = process(title);
        return title;
    }

    public void setTitle(String title) {
        this.title = title.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getBooktitle() {
        booktitle = booktitle == null ? booktitle : booktitle.replaceAll("\\\\", "");
        booktitle = process(booktitle);
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getPages() {
        pages = pages == null ? pages : pages.replaceAll("\\\\", "");
        pages = process(pages);
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getYear() {
        year = year == null ? year : year.replaceAll("\\\\", "");
        year = process(year);
        return year;
    }

    public void setYear(String year) {
        this.year = year.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getAddress() {
        address = address == null ? address : address.replaceAll("\\\\", "");
        address = process(address);
        return address;
    }

    public void setAddress(String address) {
        this.address = address.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getVolume() {
        volume = volume == null ? volume : volume.replaceAll("\\\\", "");
        volume = process(volume);
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getJournal() {
        journal = journal == null ? journal : journal.replaceAll("\\\\", "");
        journal = process(journal);
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getNumber() {
        number = number == null ? number : number.replaceAll("\\\\", "");
        number = process(number);
        return number;
    }

    public void setNumber(String number) {
        this.number = number.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getMonth() {
        month = month == null ? month : month.replaceAll("\\\\", "");
        month = process(month);
        return month;
    }

    public void setMonth(String month) {
        this.month = month.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getUrl() {
        url = url == null ? url : url.replaceAll("\\\\", "");
        url = process(url);
        return url;
    }

    public void setUrl(String url) {
        this.url = url.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getEe() {
        ee = ee == null ? ee : ee.replaceAll("\\\\", "");
        ee = process(ee);
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getCdrom() {
        cdrom = cdrom == null ? cdrom : cdrom.replaceAll("\\\\", "");
        cdrom = process(cdrom);
        return cdrom;
    }

    public void setCdrom(String cdrom) {
        this.cdrom = cdrom.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getCite() {
        cite = cite == null ? cite : cite.replaceAll("\\\\", "");
        cite = process(cite);
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getPublisher() {
        publisher = publisher == null ? publisher : publisher.replaceAll("\\\\", "");
        publisher = process(publisher);
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getNote() {
        note = note == null ? note : note.replaceAll("\\\\", "");
        note = process(note);
        return note;
    }

    public void setNote(String note) {
        this.note = note.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getCrossref() {
        crossref = crossref == null ? crossref : crossref.replaceAll("\\\\", "");
        crossref = process(crossref);
        return crossref;
    }

    public void setCrossref(String crossref) {
        this.crossref = crossref.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getIsbn() {
        isbn = isbn == null ? isbn : isbn.replaceAll("\\\\", "");
        isbn = process(isbn);
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getSeries() {
        series = series == null ? series : series.replaceAll("\\\\", "");
        series = process(series);
        return series;
    }

    public void setSeries(String series) {
        this.series = series.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getSchool() {
        school = school == null ? school : school.replaceAll("\\\\", "");
        school = process(school);
        return school;
    }

    public void setSchool(String school) {
        this.school = school.replaceAll("'", "''").replaceAll(";", ",");
    }

    public String getChapter() {
        chapter = chapter == null ? chapter : chapter.replaceAll("\\\\", "");
        chapter = process(chapter);
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter.replaceAll("'", "''").replaceAll(";", ",");
    }

    @Override
    public String toString() {
        return mdate + ";" + key + ";" + publtype + ";" + reviewid + ";" + rating + ";" + authors + ";" + editor
                + ";" + title + ";" + booktitle + ";" + pages + ";" + year + ";" + address
                + ";" + volume + ";" + journal + ";" + number + ";" + month
                + ";" + url + ";" + ee + ";" + cdrom + ";" + cite + ";" + publisher + ";" + note + ";" + crossref
                + ";" + isbn + ";" + series + ";" + school + ";" + chapter+"\n";
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

    private String process(String str) {
        if (str == null) return str;
        Pattern unicodeOutliers = Pattern.compile("[^\\x00-\\x7F]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ
                        | Pattern.CASE_INSENSITIVE);
        Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(str);
        return unicodeOutlierMatcher.replaceAll(" ");
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

