package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : jiancongchen on 2019/9/
 * Redis 的常用操作
 */

public class RedisRoutineOperation {

    public Jedis jedis;

    public static final String IP_ADDRESS = "192.168.2.196";
    public static final int PORT = 6379;

    public RedisRoutineOperation(){
        this.jedis = new Jedis(IP_ADDRESS,PORT);
    }

    public void stringOperation() {
        //添加数据,key为name放入value值为chenjiancong
        //set name chenjiancong
        jedis.set("name", "chenjiancong");
        //读取key为name的值
        System.out.println("拼接前:" + jedis.get("name"));

        //向key为name的值后面加上数据 ---拼接
        //append name  is my name;
        jedis.append("name", " is my name;");
        System.out.println("拼接后:" + jedis.get("name"));

        //删除某个键值对
        //del name
        jedis.del("name");
        System.out.println("删除后:" + jedis.get("name"));

        //s设置多个键值对
        //mset name chenjiancong age 20 email jiancong.chen@outlook.com
        jedis.mset("name", "chenjiancong", "age", "20", "email", "jiancong.chen@outlook.com");

        //用于将键的整数值递增1。如果键不存在，则在执行操作之前将其设置为0。
        // 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误。此操作限于64位有符号整数。
        //incr age
        jedis.incr("age");

        List<String> restult = jedis.mget("name","age","email");
        for(String temp : restult){
            System.out.println(temp);
        }

        //expire name 5 5s后过期
        jedis.expire("name",5);
        System.out.println("now name:" + jedis.get("name"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5s 后，name:" + jedis.get("name"));

        //setex name 5 jiancongchen 5s后过期，等价于set + expire
        jedis.setex("name",5,"jiancongchen");
        System.out.println("now name:" + jedis.get("name"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("setex 5s 后，name:" + jedis.get("name"));

        //setnx name chen
        jedis.setnx("name","chen");
        System.out.println("name 应该是 jiancongchen 没有变化:" + jedis.get("name"));

        jedis.del("name");
        //setnx name chen
        jedis.setnx("name","chen");
        System.out.println("name 应该是 chen 发生变化:" + jedis.get("name"));
    }

    public void listOperation(){
        //右边进，左边出，实现队列
        //rpush language python java C++
        jedis.rpush("language","python","java","C++");
        System.out.println("language length:" + jedis.llen("language"));
        System.out.println(jedis.lpop("language"));
        System.out.println(jedis.lpop("language"));
        System.out.println(jedis.lpop("language"));
        System.out.println(jedis.lpop("language"));

        //右边进，右边出，实现栈
        jedis.rpush("language","python","java","C++");
        System.out.println(jedis.rpop("language"));
        System.out.println(jedis.rpop("language"));
        System.out.println(jedis.rpop("language"));
        System.out.println(jedis.rpop("language"));

        jedis.rpush("language","python","java","C++");
        //lindex language 1 复杂度为O(n) 慎用
        System.out.println(jedis.lindex("language",1));

        //lrange language 0 -1 -1表示倒数第一个元素
        List<String> result = jedis.lrange("language",0,-1);
        for(String temp : result){
            System.out.println("language:" + temp);
        }

        //ltrim language 1 -1 O(n)慎用，保留指定区间的元素
        jedis.ltrim("language",1,-1);
        List<String> trimResult = jedis.lrange("language",0,-1);
        for(String temp : trimResult){
            System.out.println("language:" + temp);
        }
    }

    public void hashOperation(){
        //hset books java "think in java" 命令行字符串如果包含空格，需要用引号括起来
        jedis.hset("books","java","think in java");
        jedis.hset("books","golang","concurrency in go");
        jedis.hset("books","python","python cookbook");
        HashMap<String,String> hashMap = (HashMap)jedis.hgetAll("books");
        for(Map.Entry<String,String> entry : hashMap.entrySet()){
            System.out.println("key :" + entry.getKey());
            System.out.println("value :" + entry.getValue());
        }

        //hget books java
        System.out.println(jedis.hget("books","java"));
        jedis.hset("books","golang","learning go programming");
        System.out.println(jedis.hget("books","golang"));

        jedis.hmget("books","java","effective java","python","learning python");
        HashMap<String,String> resultHashMap = (HashMap)jedis.hgetAll("books");
        for(Map.Entry<String,String> entry : resultHashMap.entrySet()){
            System.out.println("key :" + entry.getKey());
            System.out.println("value :" + entry.getValue());
        }
    }

    public void setOperation(){
        //sadd books python
        jedis.sadd("setBooks","python");
        jedis.sadd("setBooks","java","C#");

        Set<String> stringSet = jedis.smembers("setBooks");
        for(String temp : stringSet){
            System.out.println(temp);
        }

        //sismember books java
        System.out.println("java 应该存在：" + jedis.sismember("setBooks","java"));
        System.out.println("test 应该不存在：" + jedis.sismember("setBooks","test"));

        //scard books
        System.out.println("length:" + jedis.scard("setBooks"));

        //spop books
        System.out.println(jedis.spop("setBooks"));
    }

    public void zsetOperation(){
        jedis.zadd("zsetBooks",9.0,"think in java");
        jedis.zadd("zsetBooks",8.9,"java concurrency");
        jedis.zadd("zsetBooks",8.6,"java cookbook");

        //zrange zsetBooks 0 -1 按照score排序列出，参数区间为排名范围
        Set<String> stringSet = jedis.zrange("zsetBooks",0,-1);
        for(String temp : stringSet){
            System.out.println(temp);
        }

        Set<String> revrange = jedis.zrevrange("zsetBooks",0,-1);
        for(String temp : revrange){
            System.out.println(temp);
        }

        System.out.println("length:" + jedis.zcard("zsetBooks"));
        System.out.println("score:" + jedis.zscore("zsetBooks","think in java"));
        System.out.println("rank:" + jedis.zrank("zsetBooks","java cookbook"));

        Set<String> byScore = jedis.zrangeByScore("zsetBooks",0,8.91);
        for(String temp : byScore){
            System.out.println(temp);
        }

        Set<Tuple> tuples = jedis.zrangeByScoreWithScores("zsetBooks",Double.NEGATIVE_INFINITY,9.91);
        for(Tuple tuple : tuples){
            System.out.println(tuple.getElement());
            System.out.println(tuple.getScore());
        }

        jedis.zrem("zsetBooks","think in java");
    }
}
