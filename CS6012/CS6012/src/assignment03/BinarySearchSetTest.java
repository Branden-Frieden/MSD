package assignment03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {
    BinarySearchSet<Integer> intNaturalSearchSet1, intReversedSearchSet1, intEmptySearchSet;
    Comparator<Integer> reverseInt;
    public class reverseIntCompare implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    @BeforeEach
    void setUp() {
        intEmptySearchSet = new BinarySearchSet<>();

        intNaturalSearchSet1 = new BinarySearchSet<>();
        intNaturalSearchSet1.add(5);
        intNaturalSearchSet1.add(12);
        intNaturalSearchSet1.add(3);
        intNaturalSearchSet1.add(-8);
        intNaturalSearchSet1.add(4);
        intNaturalSearchSet1.add(2);
        intNaturalSearchSet1.add(1);
        intNaturalSearchSet1.add(0);
        intNaturalSearchSet1.add(14);
        intNaturalSearchSet1.add(19);

        reverseInt = new reverseIntCompare();
        intReversedSearchSet1 = new BinarySearchSet<>(reverseInt);
        intReversedSearchSet1.add( 3 );
        intReversedSearchSet1.add( 5 );
        intReversedSearchSet1.add( 9 );
        intReversedSearchSet1.add( 6 );
        intReversedSearchSet1.add( -12 );

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void addIntegers() {

        assert(intEmptySearchSet.add(35));
        assert(!intNaturalSearchSet1.add(3));
        assert(intNaturalSearchSet1.add(1000));
        assert(!intNaturalSearchSet1.add(25.443));
        assert(!intReversedSearchSet1.add(3));
        assert(intReversedSearchSet1.add(1000));

    }

    @Test
    void comparator() {
        assertEquals(reverseInt, intReversedSearchSet1.comparator());
        assertNull(intNaturalSearchSet1.comparator());
        assertNull(intEmptySearchSet.comparator());
    }

    @Test
    void first() {
        try{ intEmptySearchSet.first();}
        catch(NoSuchElementException e){
            System.out.println("First exception thrown correctly");
        }
        assertEquals(-8, intNaturalSearchSet1.first());
        assertEquals(9, intReversedSearchSet1.first());
    }

    @Test
    void last() {
        try{intEmptySearchSet.last();}
        catch(NoSuchElementException e){
            System.out.println("Last exception thrown correctly");
        }
        assertEquals(19, intNaturalSearchSet1.last());
        assertEquals(-12, intReversedSearchSet1.last());
    }


    @Test
    void addAll() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add( 0 );
        ints.add( 3 );
        ints.add( 5 );

        assertTrue(intEmptySearchSet.addAll(ints));
        assertTrue(intReversedSearchSet1.addAll(ints));
        assertFalse(intNaturalSearchSet1.addAll(ints));

        assertEquals(3, intEmptySearchSet.size());
        assertEquals(6, intReversedSearchSet1.size());
        assertEquals(10, intNaturalSearchSet1.size());
    }

    @Test
    void clear() {
        intEmptySearchSet.clear();
        intReversedSearchSet1.clear();
        intNaturalSearchSet1.clear();
        assertTrue(intEmptySearchSet.isEmpty());
        assertTrue(intReversedSearchSet1.isEmpty());
        assertTrue(intNaturalSearchSet1.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(intEmptySearchSet.contains( 1 ));
        assertFalse(intNaturalSearchSet1.contains( -4 ));
        assertFalse(intReversedSearchSet1.contains( 2 ));

        assertTrue(intNaturalSearchSet1.contains( 0 ));
        assertTrue(intReversedSearchSet1.contains( 9 ));
    }

    @Test
    void containsAll() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add( 0 );
        ints.add( 3 );
        ints.add( 5 );

        assertFalse(intEmptySearchSet.containsAll(ints));
        assertFalse(intReversedSearchSet1.containsAll(ints) );
        assertTrue(intNaturalSearchSet1.containsAll(ints));


    }

    @Test
    void isEmpty() {
        assertTrue(intEmptySearchSet.isEmpty());
        assertFalse(intReversedSearchSet1.isEmpty());
        assertFalse(intNaturalSearchSet1.isEmpty());
    }

    @Test
    void iterator() {

    }

    @Test
    void remove() {
        assertFalse(intEmptySearchSet.remove(1));
        assertFalse(intNaturalSearchSet1.remove(12345));
        assertFalse(intReversedSearchSet1.remove(12345));

        assertTrue(intNaturalSearchSet1.remove(4));
        assertTrue(intReversedSearchSet1.remove(5));

        assertFalse(intNaturalSearchSet1.contains(4));
        assertFalse(intReversedSearchSet1.contains(5));
    }

    @Test
    void removeAll() {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add( 0 );
        ints.add( 3 );
        ints.add( 5 );

        assertTrue(intNaturalSearchSet1.removeAll( ints ));
        assertTrue(intReversedSearchSet1.removeAll( ints ));
        assertFalse(intEmptySearchSet.removeAll( ints ));

        assertFalse(intNaturalSearchSet1.contains(5));
        assertFalse(intReversedSearchSet1.contains(5));
        assertEquals(7, intNaturalSearchSet1.size());
        assertEquals(3, intReversedSearchSet1.size());
    }

    @Test
    void size() {
        assertEquals(0, intEmptySearchSet.size());
        assertEquals(10, intNaturalSearchSet1.size());
        assertEquals(5, intReversedSearchSet1.size());
    }

    @Test
    void toArray() {
        Object[] naturalEqualArray = new Object[]{-8, 0, 1, 2, 3, 4, 5, 12, 14, 19, null, null, null,
                                                    null, null, null, null, null, null, null};
        Object[] reversedEqualArray = new Object[]{9, 6, 5, 3, -12, null, null, null, null, null};


        for( int i = 0; i < 20; i++) {
            assertEquals(naturalEqualArray[i], intNaturalSearchSet1.toArray()[i]);
        }
        for( int i = 0; i < 10; i++) {
            assertEquals(reversedEqualArray[i], intReversedSearchSet1.toArray()[i]);
            assertNull(intEmptySearchSet.toArray()[i]);
        }
    }
}