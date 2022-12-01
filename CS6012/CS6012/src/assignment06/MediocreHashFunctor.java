package assignment06;

public class MediocreHashFunctor implements HashFunctor{
    @Override
    public int hash(String item) {
        int hash = 0;
        int c;
        for(int i = 0; i < item.length(); i++){
            c = item.charAt(i);
            hash += c;
        }

        return Math.abs(hash);
    }
}
