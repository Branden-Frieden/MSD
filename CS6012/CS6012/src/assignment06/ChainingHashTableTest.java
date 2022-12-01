package assignment06;

import com.sun.net.httpserver.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {

    ChainingHashTable fullTable, emptyTable;
    ArrayList<String> strings;
    @BeforeEach
    void setup(){
        fullTable = new ChainingHashTable(100, new GoodHashFunctor());
        emptyTable = new ChainingHashTable(100, new GoodHashFunctor());
        strings = new ArrayList<>();
        ArrayList<String> words = new ArrayList<String>();
        File file = new File("dictionary.txt");
        try (Scanner fileInput = new Scanner(file)) {

            fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

            while (fileInput.hasNext()) {
                String s = fileInput.next();
                if (!s.equals("")) {
                    words.add(s.toLowerCase());
                    strings.add(s.toLowerCase());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File " + file + " cannot be found.");
        }

        fullTable.addAll( words );
    }

    @Test
    void add() {

        assertTrue(fullTable.add("this"));
        assertTrue(emptyTable.add("that"));
        assertFalse(fullTable.add("this"));
        assertFalse(fullTable.add(null));
        assertFalse(emptyTable.add(null));
        assertFalse(emptyTable.add("that"));

    }

    @Test
    void addAll() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("monster");
        strings.add( "bad" );
        strings.add( "dude" );
        strings.add( "crybaby" );
        strings.add( "sucker" );

        assertTrue(fullTable.addAll(strings));
        assertTrue(emptyTable.addAll(strings));

        strings.add("person");

        assertTrue(fullTable.addAll(strings));
        assertTrue(emptyTable.addAll(strings));

        assertFalse(fullTable.addAll(strings));
        assertFalse(emptyTable.addAll(strings));

    }

    @Test
    void clear() {
        assertFalse(fullTable.add("abandoned"));
        fullTable.clear();
        assertTrue(fullTable.add("abandoned"));
    }

    @Test
    void contains() {

        assertTrue(fullTable.contains("abandoned"));
        assertFalse(fullTable.contains("this"));
        assertFalse(emptyTable.contains("abandoned"));
        assertFalse(fullTable.contains(null));
        assertFalse(emptyTable.contains(null));
    }

    @Test
    void containsAll() {

        ArrayList<String> words = new ArrayList<>();
        words.add( "luck" );
        words.add( "thief" );
        words.add( "weeps" );
        words.add( "abandoned" );


        assertTrue(fullTable.containsAll(words));
        assertFalse(emptyTable.containsAll(words));

        words.add("this");
        assertFalse(fullTable.containsAll(words));
        words.remove("this");
        assertTrue(fullTable.containsAll(words));
        words.add(null);
        assertFalse(fullTable.containsAll(words));

    }

    @Test
    void isEmpty() {
        assertTrue(emptyTable.isEmpty());
        assertFalse(fullTable.isEmpty());
        emptyTable.add("this");
        assertFalse(emptyTable.isEmpty());
    }

    @Test
    void remove() {

        assertTrue(fullTable.remove("abandoned"));
        assertFalse(fullTable.contains("abandoned"));
        assertFalse(fullTable.remove("abandoned"));
        assertFalse(fullTable.remove(null));

    }

    @Test
    void removeAll() {
        ArrayList<String> words = new ArrayList<>();
        words.add( "thief" );
        words.add( "weeps" );
        words.add( "abandoned" );

        assertTrue(fullTable.removeAll(words));
        words.add("luck");
        assertTrue(fullTable.removeAll(words));
        assertFalse(fullTable.removeAll(words));
    }

    @Test
    void size() {
        assertEquals(2914, fullTable.size());
        assertEquals(0, emptyTable.size());
        fullTable.remove("luck");
        emptyTable.add("luck");
        assertEquals(2913, fullTable.size());
        assertEquals(1, emptyTable.size());
    }

    @Test
    void Functors(){
        ArrayList<HashFunctor> functors = new ArrayList<>();
        functors.add(new BadHashFunctor());
        functors.add(new MediocreHashFunctor());
        functors.add(new GoodHashFunctor());

        ArrayList<Integer> capacities = new ArrayList<>();
        capacities.add( 100 );
        capacities.add( 500 );
        capacities.add( 1000 );
        capacities.add( 2000 );
        capacities.add( 4000 );

        int loops = 2000;
        long totalTime = 0;

//        for(HashFunctor functor: functors) {
//            for (Integer capacity: capacities) {
//                totalTime = 0;
//                emptyTable = new ChainingHashTable(capacity, functor);
//                for(int i = 0; i < loops; i++) {
//                    long startTime = System.nanoTime();
//                    emptyTable.addAll(strings);
//                    long endTime = System.nanoTime();
//                    totalTime += endTime - startTime;
//                }
//                long averageTime = totalTime / loops;
//                System.out.println(averageTime);
//            }
//            System.out.println();
//        }

//        for(HashFunctor functor: functors) {
//            for (int listSize = 500; listSize < 2914; listSize += 500) {
//                totalTime = 0;
//                emptyTable = new ChainingHashTable(2000, functor);
//                for(int i = 0; i < loops; i++) {
//                    long startTime = System.nanoTime();
//                    emptyTable.addAll(strings.subList(0, listSize));
//                    long endTime = System.nanoTime();
//                    totalTime += endTime - startTime;
//                }
//                long averageTime = totalTime / loops;
//                System.out.println(averageTime);
//            }
//            System.out.println();
//        }


//        for(HashFunctor functor: functors) {
//            for (Integer capacity : capacities) {
//                emptyTable = new ChainingHashTable(capacity, functor);
//                emptyTable.addAll(strings);
//                int takenSpots = 0;
//                for (int j = 0; j < emptyTable.capacity_; j++) {
//                    if (emptyTable.storage[j].size() != 0)
//                        takenSpots++;
//                }
//                System.out.println(emptyTable.size() - takenSpots);
//            }
//            System.out.println();
//        }


//        for(HashFunctor functor: functors) {
//            for (int listSize = 500; listSize < 2914; listSize += 500) {
//                emptyTable = new ChainingHashTable(2000, functor);
//                emptyTable.addAll(strings.subList(0, listSize));
//                int takenSpots = 0;
//                for (int j = 0; j < emptyTable.capacity_; j++) {
//                    if (emptyTable.storage[j].size() != 0)
//                        takenSpots++;
//                }
//                System.out.println(emptyTable.size() - takenSpots);
//            }
//            System.out.println();
//        }


    }
}