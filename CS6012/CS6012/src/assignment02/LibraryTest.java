package assignment02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void testEmpty() {
        Library lib = new Library();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testNonEmpty() {

        var lib = new Library();
        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        assertNull(lib.lookup(9780330351690L)); //not checked out
        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        var booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 1);
        assertEquals(booksCheckedOut.get(0),new LibraryBook(9780330351690L, "Jon Krakauer", "Into the Wild"));
        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9780330351690L);
        assertTrue(res);
        res = lib.checkin("Jane Doe");
        assertFalse(res);
    }

    @Test
    public void testLargeLibrary(){
        // test a medium library
        var lib = new Library();
        lib.addAll("Mushroom_Publishing.txt");


        var res = lib.checkout( 9781843190394L, "Branden", 11, 1, 2021);
        assertTrue(res);

        var res2 = lib.checkout( 9781843193319L, "Branden", 11, 1, 2021);
        assertTrue(res2);


        assertEquals("Branden", lib.lookup(9781843190394L));
        assertEquals("Branden", lib.lookup(9781843193319L));

        ArrayList<LibraryBook> checkedOutBooks = lib.lookup("Branden");

        assertEquals( new LibraryBook(9781843190394L, "Kate Clarke", "The Royal United Hospital") ,checkedOutBooks.get(0));
        assertEquals( new LibraryBook(9781843193319L, "Alan Burt Akers", "Transit to Scorpio") ,checkedOutBooks.get(1));

        assertEquals(new GregorianCalendar(2021, 11, 1), checkedOutBooks.get( 0 ).getDueDate());
        lib.checkin("Branden");


        assertNull(lib.lookup(9781843190394L));
        assertNull(lib.lookup(9781843193319L));
    }

}