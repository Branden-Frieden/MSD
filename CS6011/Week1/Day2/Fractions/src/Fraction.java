import java.io.InvalidObjectException;

interface Comparable<T>{
    int compareTo(T o);
}


public class Fraction {
    long numerator;
    long denominator;

    public Fraction(){
        numerator = 0;
        denominator = 1;
    }

   // construct fraction
    public Fraction( long n, long d) throws ArithmeticException{
        numerator = n;
        denominator = d;
        if(d == 0) {
            throw new ArithmeticException("cannot have a fraction with denominator 0");
        }
    }

    // multiply the denominators by eachother and the corresponding numerators to match then add together
    public Fraction plus( Fraction rhs ){
        if(denominator ==rhs.denominator){
            Fraction newFraction = new Fraction((numerator + rhs.numerator), denominator);
            return newFraction;
        }
        else {
            long newDenominator = denominator * rhs.denominator;
            long newNumerator = numerator * rhs.denominator + rhs.numerator * denominator;
            Fraction newFraction = new Fraction(newNumerator, newDenominator);
            return newFraction;
        }
    }

    public Fraction minus(Fraction rhs){
        if(denominator == rhs.denominator){
            Fraction newFraction = new Fraction((numerator - rhs.numerator), denominator);
            return newFraction;
        }
        else {
            long newDenominator = denominator * rhs.denominator;
            long newNumerator = numerator * rhs.denominator - rhs.numerator * denominator;
            Fraction newFraction = new Fraction(newNumerator, newDenominator);
            return newFraction;
        }
    }

    // multiply numerators together and put them over the denominators multiplied
    public Fraction times(Fraction rhs){
        long newNumerator = numerator * rhs.numerator;
        long newDenominator = denominator * rhs.denominator;
        Fraction newFraction = new Fraction(newNumerator, newDenominator);
        return newFraction;
    }

    public Fraction dividedBy(Fraction rhs){
        long newNumerator = numerator * rhs.denominator;
        long newDenominator = denominator * rhs.numerator;
        Fraction newFraction = new Fraction(newNumerator, newDenominator);
        return newFraction;
    }

    public Fraction reciprocal(){
        Fraction newFraction = new Fraction(denominator, numerator);
        return newFraction;
    }

    public String toString(){
        String output = numerator + "/" + denominator;
        return output;
    }

    public double toDouble(){
        return (double)numerator/(double)denominator;
    }

    private long GCD(){
        long gcd = numerator;
        long remainder = denominator;
        while( remainder != 0 ) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return Math.abs(gcd);
    }

    public void reduce(){
        long gcd = this.GCD();
        numerator /= gcd;
        denominator /= gcd;
    }

    public int compareTo(Fraction frac){
        double diff = (this.minus(frac)).toDouble();
        if( diff < 0 ){
            return -1;
        } else if ( diff == 0) {
            return 0;
        } else{
            return 1;
        }
    }

    public static void main(String[] args) {
    }
}
