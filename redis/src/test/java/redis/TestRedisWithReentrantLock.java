package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author : jiancongchen on 2019/9/15
 */

public class TestRedisWithReentrantLock {

    public static final String IP_ADDRESS = "192.168.2.196";
    public static final int PORT = 6379;

    @Test
    public void test(){
        Jedis jedis = new Jedis(IP_ADDRESS,PORT);
        RedisWithReentrantLock redisWithReentrantLock = new RedisWithReentrantLock(jedis);
        System.out.println(redisWithReentrantLock.lock("LOCK"));
        System.out.println(redisWithReentrantLock.lock("LOCK"));

        System.out.println(redisWithReentrantLock.unlock("LOCK"));
        System.out.println(redisWithReentrantLock.unlock("LOCK"));

    }
}
