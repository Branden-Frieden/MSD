package assignment02;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class LibraryBook extends Book {

    String holder = null;
    GregorianCalendar dueDate = null;

    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);

    }

    public String getHolder(){
        return holder;
    }
    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    // sets holder and due date to null when checked in
    public void checkIn(){
        this.holder = null;
        this.dueDate = null;
    }

    // gives the book a new holder and due date when its checked out
    public void checkOut(String holder, int month, int day, int year) {
        this.holder = holder;
        dueDate = new GregorianCalendar(year, month, day);
    }
}