package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiancongchen on 2019/9/15
 */

public class RedisWithReentrantLock {

    private ThreadLocal<Map<String,Integer>> lockers = new ThreadLocal<>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis){
        this.jedis = jedis;
    }

    private boolean _lock(String key){
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(5000);
        return jedis.set(key,"",setParams) != null;
    }

    private void _unlock(String key){
        jedis.del(key);
    }

    private Map<String,Integer> currentLockers(){
        Map<String,Integer> refs = lockers.get();
        if(refs != null){
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key){
        Map<String,Integer> refs = currentLockers();
        Integer refInt = refs.get(key);
        if(refInt != null){
            refs.put(key, refInt + 1);
            return true;
        }
        boolean OK = this._lock(key);
        if(!OK){
            return false;
        }
        refs.put(key,1);
        return true;
    }

    public boolean unlock(String key){
        Map<String,Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if(refCnt == null){
            return false;
        }
        refCnt -= 1;
        if(refCnt > 0){
            refs.put(key,refCnt);
        }else{
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }
}