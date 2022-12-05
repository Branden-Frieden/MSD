package assignment06;

public class GoodHashFunctor implements HashFunctor{
    public GoodHashFunctor(){}

    @Override
    public int hash(String item) {
        int hash = 5381;
        int c;

        for(int i = 0; i < item.length(); i++) {
            c = item.charAt(i);
            hash =  hash * 33 + c;
        }
        return Math.abs(hash);
    }
}