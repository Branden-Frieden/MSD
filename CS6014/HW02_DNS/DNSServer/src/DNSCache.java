import java.util.HashMap;

public class DNSCache {
    HashMap<DNSQuestion, DNSRecord> cache;

    DNSCache(){
        cache = new HashMap<>();
    }
    void store(DNSQuestion question, DNSRecord record){
        if(!query(question)){
            cache.put(question, record);
        }
    }

    boolean query(DNSQuestion question) {
        if (cache.containsKey(question)) {
            if (cache.get(question).isExpired()) {
                cache.remove(question);
                return false;
            }
            return true;
        }
        return false;
    }

    DNSRecord getValue(DNSQuestion key){
        return cache.get(key);
    }

}
