package assignment02;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class LibraryBookGeneric<Type> extends Book {

    Type holder = null;
    GregorianCalendar dueDate = null;

    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);

    }

    public Type getHolder(){
        return holder;
    }
    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    // sets holder and due date to null for checking a book in
    public void checkIn(){
        this.holder = null;
        this.dueDate = null;
    }

    // gives the book a new holder and due date
    public void checkOut(Type holder, int month, int day, int year) {

        this.holder = holder;
        dueDate = new GregorianCalendar(year, month, day);
    }
}