package assignment02;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class LibraryBookGeneric<Type> extends Book {

    Type holder_ = null;
    GregorianCalendar dueDate_ = null;

    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);

    }

    public Type getHolder(){
        return holder_;
    }
    public GregorianCalendar getDueDate(){
        return dueDate_;
    }

    // sets holder and due date to null for checking a book in
    public void checkIn(){
        holder_ = null;
        dueDate_ = null;
    }

    // gives the book a new holder and due date
    public void checkOut(Type holder, int month, int day, int year) {

        holder_ = holder;
        dueDate_ = new GregorianCalendar(year, month, day);
    }
}