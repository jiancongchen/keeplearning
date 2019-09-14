package redis;


import org.junit.Test;

/**
 * @author : jiancongchen on 2019/9/14
 */

public class TestRedis {

    @Test
    public void testRedisOperation(){
        RedisRoutineOperation redisRoutineOperation = new RedisRoutineOperation();
//        redisRoutineOperation.stringOperation();
//        redisRoutineOperation.listOperation();
//        redisRoutineOperation.hashOperation();
//        redisRoutineOperation.setOperation();
        redisRoutineOperation.zsetOperation();
    }
}
