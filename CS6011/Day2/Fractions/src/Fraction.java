public class Fraction {
    long numerator;
    long denominator;

    public Fraction(){
        numerator = 0;
        denominator = 1;
    }

    public Fraction( long n, long d){
        numerator = n;
        denominator = d;
        if(d == 0){
            denominator = 1;
        }
    }

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
        String StrNumerator = String.valueOf(numerator);
        String StrDenominator = String.valueOf(denominator);
        String output = StrNumerator + "/" + StrDenominator;
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
        return gcd;
    }

    public void reduce(){
        long gcd = this.GCD()
        numerator /= gcd;
        denominator /= gcd;
    }

    public static void main(String[] args) {
    }
}
