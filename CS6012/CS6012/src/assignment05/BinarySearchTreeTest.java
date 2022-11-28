package assignment05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    BinarySearchTree<String> emptyDictionary, fullDictionary;
    ArrayList<String> words;

    @BeforeEach
    void setUp() {

        words = new ArrayList<>();
        words.add( "luck" );
        words.add( "thief" );
        words.add( "weeps" );
        words.add( "abandoned" );

        emptyDictionary = new BinarySearchTree<>();
        fullDictionary = new BinarySearchTree<>();
        ArrayList<String> words = new ArrayList<String>();

        File file = new File("dictionary.txt");
        try (Scanner fileInput = new Scanner(file)) {

            fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

            while (fileInput.hasNext()) {
                String s = fileInput.next();
                if (!s.equals("")) {
                    words.add(s.toLowerCase());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File " + file + " cannot be found.");
        }

        fullDictionary.addAll( words );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        assertTrue(emptyDictionary.add("kerfuffle"));
        assertTrue(fullDictionary.add("kerfuffle"));
        assertTrue(emptyDictionary.contains("kerfuffle"));
        assertTrue(fullDictionary.contains("kerfuffle"));
    }

    @Test
    void clear() {
        fullDictionary.clear();
        assertFalse(fullDictionary.contains("luck"));
        emptyDictionary.clear();
        assertFalse(fullDictionary.contains("luck"));
    }

    @Test
    void contains() {
        assertTrue(fullDictionary.contains("luck"));
        assertFalse(emptyDictionary.contains("luck"));
        assertFalse(fullDictionary.contains("kerfuffle"));
    }

    @Test
    void containsAll() {

        assertTrue(fullDictionary.containsAll(words));
        assertFalse(emptyDictionary.containsAll(words));

        words.add("kerfuffle");

        assertFalse(fullDictionary.containsAll(words));

    }

    @Test
    void first() {
        try{
            emptyDictionary.first();
            fail();
        } catch( NoSuchElementException e ){}

        assertEquals("a", fullDictionary.first());
    }

    @Test
    void isEmpty() {
        assertTrue(emptyDictionary.isEmpty());
        assertFalse(fullDictionary.isEmpty());
    }

    @Test
    void last() {
        try{
            emptyDictionary.last();
            fail();
        } catch( NoSuchElementException e ){}

        assertEquals("zucchini", fullDictionary.last());
    }

    @Test
    void remove() {
        fullDictionary.remove("luck");
        assertFalse(fullDictionary.contains("luck"));
    }

    @Test
    void removeAll() {

        assertTrue(fullDictionary.removeAll( words ));
        for(String word: words) {
            assertFalse(fullDictionary.contains(word));
        }

        assertTrue(fullDictionary.addAll( words ));

        words.add( "kerfuffle" );
        assertFalse(fullDictionary.removeAll( words ));

    }

    @Test
    void size() {
        assertEquals(2914, fullDictionary.size());
        assertEquals(0, emptyDictionary.size());
        fullDictionary.clear();
        assertEquals(0, fullDictionary.size());
    }

    @Test
    void toArrayList() {
        emptyDictionary.addAll(words);
        ArrayList<String> wordsInOrder = new ArrayList<>();

        wordsInOrder.add( "abandoned" );
        wordsInOrder.add( "luck" );
        wordsInOrder.add( "thief" );
        wordsInOrder.add( "weeps" );

        assertEquals(wordsInOrder, emptyDictionary.toArrayList());

    }
}