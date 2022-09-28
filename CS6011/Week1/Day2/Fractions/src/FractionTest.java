import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FractionTest {


    @Test
    public void runAllTests(){
        Fraction f1 = new Fraction(1,2);
        Fraction f2 = new Fraction(2,3);
        Fraction f1_plus_f2 = new Fraction(7,6);
        Fraction f1_minus_f2 = new Fraction(-1,6);
        Fraction f1_reciprocal = new Fraction(2,1);
        Fraction f1_times_f2 = new Fraction(2,6);
        Fraction f1_dividedBy_f2 = new Fraction(3,4);
        String f1_as_String = "1/2";
        double f1_as_double = .5;

        Assertions.assertEquals(f1.plus(f2).toString(), f1_plus_f2.toString() );
        Assertions.assertEquals(f1.minus(f2).toString(), f1_minus_f2.toString());
        Assertions.assertEquals(f1.reciprocal().toString(), f1_reciprocal.toString());
        Assertions.assertEquals(f1.times(f2).toString(), f1_times_f2.toString());
        Assertions.assertEquals(f1.dividedBy(f2).toString(), f1_dividedBy_f2.toString());
        Assertions.assertEquals(f1.toString(), f1_as_String);
        Assertions.assertEquals(f1.toDouble(), f1_as_double);



    }
}