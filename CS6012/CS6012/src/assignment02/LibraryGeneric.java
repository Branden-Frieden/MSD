package assignment02;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.text.ParseException;
        import java.util.*;

/**
 * Class representation of a library (a collection of library books).
 *
 */
public class LibraryGeneric<Type> {

    private ArrayList<LibraryBookGeneric<Type>> library;

    public LibraryGeneric() {
        library = new ArrayList<LibraryBookGeneric<Type>>();
    }

    /**
     * Add the specified book to the library, assume no duplicates.
     *
     * @param isbn
     *          -- ISBN of the book to be added
     * @param author
     *          -- author of the book to be added
     * @param title
     *          -- title of the book to be added
     */
    public void add(long isbn, String author, String title) {
        library.add(new LibraryBookGeneric(isbn, author, title));
    }

    /**
     * Add the list of library books to the library, assume no duplicates.
     *
     * @param list
     *          -- list of library books to be added
     */
    public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
        library.addAll(list);
    }

    /**
     * Add books specified by the input file. One book per line with ISBN, author,
     * and title separated by tabs.
     *
     * If file does not exist or format is violated, do nothing.
     *
     * @param filename
     */
    public void addAll(String filename) {
        ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

        try (Scanner fileIn = new Scanner(new File(filename))) {

            int lineNum = 1;

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();

                try (Scanner lineIn = new Scanner(line)) {
                    lineIn.useDelimiter("\\t");

                    if (!lineIn.hasNextLong()) {
                        throw new ParseException("ISBN", lineNum);
                    }
                    long isbn = lineIn.nextLong();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Author", lineNum);
                    }
                    String author = lineIn.next();

                    if (!lineIn.hasNext()) {
                        throw new ParseException("Title", lineNum);
                    }
                    String title = lineIn.next();
                    toBeAdded.add(new LibraryBookGeneric(isbn, author, title));
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + " Nothing added to the library.");
            return;
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
                    + ". Nothing added to the library.");
            return;
        }

        library.addAll(toBeAdded);
    }

    /**
     * Returns the holder of the library book with the specified ISBN.
     *
     * If no book with the specified ISBN is in the library, returns null.
     *
     * @param isbn
     *          -- ISBN of the book to be looked up
     */
    public Type lookup(long isbn) {

        // for every book in the library, if the ISBN matches the lookup, return the holder of that book
        for(LibraryBookGeneric<Type> book: library){
            if(book.getIsbn() == isbn){
                return book.getHolder();
            }
        }
        return null;
    }

    /**
     * Returns the list of library books checked out to the specified holder.
     *
     * If the specified holder has no books checked out, returns an empty list.
     *
     * @param holder
     *          -- holder whose checked out books are returned
     */
    public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {

        // create new ArrayList of books
        ArrayList<LibraryBookGeneric<Type>> books = new ArrayList<>();

        if(holder != null) {
            // loop through and check holders, if they match, add to arrayList
            for (LibraryBookGeneric<Type> book : library) {
                if (book.getHolder().equals(holder)) {
                    books.add(book);
                }
            }
        }
        return books; // return the arrayList
    }

    /**
     * Sets the holder and due date of the library book with the specified ISBN.
     *
     * If no book with the specified ISBN is in the library, returns false.
     *
     * If the book with the specified ISBN is already checked out, returns false.
     *
     * Otherwise, returns true.
     *
     * @param isbn
     *          -- ISBN of the library book to be checked out
     * @param holder
     *          -- new holder of the library book
     * @param month
     *          -- month of the new due date of the library book
     * @param day
     *          -- day of the new due date of the library book
     * @param year
     *          -- year of the new due date of the library book
     *
     */
    public boolean checkout(long isbn, Type holder, int month, int day, int year) {

        for(LibraryBookGeneric<Type> book: library){
            if(book.getIsbn() == isbn){

                // if the holder is null, check out, if it isn't null, then the book is checked out and return false
                if(book.getHolder() == null){
                    book.checkOut(holder, month, day, year);
                    return true;   // book is now checked out
                }
                else{
                    return false; // book is already checked out
                }
            }
        }
        return false;  // book doesn't exist
    }

    /**
     * Unsets the holder and due date of the library book.
     *
     * If no book with the specified ISBN is in the library, returns false.
     *
     * If the book with the specified ISBN is already checked in, returns false.
     *
     * Otherwise, returns true.
     *
     * @param isbn
     *          -- ISBN of the library book to be checked in
     */
    public boolean checkin(long isbn) {
        for(LibraryBookGeneric<Type> book: library) {
            if ( book.getIsbn() == isbn ) {

                // if the book holder isn't null then check the book in, otherwise, return false
                if( book.getHolder() != null ){
                    book.checkIn();
                    return true;    // book is now checked in
                }
                else{
                    return false;   // book is already checked in
                }
            }
        }
        return false; // book doesn't exist
    }

    /**
     * Unsets the holder and due date for all library books checked out be the
     * specified holder.
     *
     * If no books with the specified holder are in the library, returns false;
     *
     * Otherwise, returns true.
     *
     * @param holder
     *          -- holder of the library books to be checked in
     */
    public boolean checkin(Type holder) {

        if(holder == null){
            return false;   //null holder not accepted
        }

        // get a list of all books held by the holder
        ArrayList<LibraryBookGeneric<Type>> holderBooks = lookup( holder );

        // return false if there are no books held by this holder
        if( holderBooks.size() == 0){
            return false;
        }

        // check in all books held by the holder
        for(LibraryBookGeneric<Type> book: holderBooks){
            book.checkIn();
        }
        return true;    // books are now all checked in

    }

    /**
     * Returns the list of library books, sorted by ISBN (smallest ISBN first).
     */
    public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
        ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
        libraryCopy.addAll(library);

        OrderByIsbn comparator = new OrderByIsbn();

        sort(libraryCopy, comparator);

        return libraryCopy;
    }

    /**
     * Returns the list of library books, sorted by author
     */
    public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {


        ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>(library);

        OrderByAuthor comparator = new OrderByAuthor();

        sort(libraryCopy, comparator);

        return libraryCopy;
    }

    /**
     * Returns the list of library books whose due date is older than the input
     * date. The list is sorted by date (the oldest first).
     *
     * If no library books are overdue, returns an empty list.
     */
    public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {

        ArrayList<LibraryBookGeneric<Type>> overdueList = new ArrayList<>();

        for(LibraryBookGeneric<Type> book: library){
            if(book.getDueDate().compareTo(new GregorianCalendar(Calendar.getInstance().getTimeZone())) < 0){
                overdueList.add(book);
            }
        }

        sort(overdueList, new OrderByDueDate());

        return overdueList;
    }



    /**
     * Performs a SELECTION SORT on the input ArrayList.
     *    1. Find the smallest item in the list.
     *    2. Swap the smallest item with the first item in the list.
     *    3. Now let the list be the remaining unsorted portion
     *       (second item to Nth item) and repeat steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
        for (int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for (j = i + 1, minIndex = i; j < list.size(); j++)
                if (c.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Comparator that defines an ordering among library books using the ISBN.
     */
    protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

        /**
         * Returns a negative value if lhs is smaller than rhs. Returns a positive
         * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
         */
        public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {

            return (int) (lhs.getIsbn() - rhs.getIsbn());
        }
    }

    /**
     * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
     */
    protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {

        public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
            return  (lhs.getAuthor().compareToIgnoreCase(rhs.getAuthor()));
        }
    }

    /**
     * Comparator that defines an ordering among library books using the due date.
     */
    protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {

        public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
            return  (lhs.getDueDate().compareTo(rhs.getDueDate()));
        }

    }

}
