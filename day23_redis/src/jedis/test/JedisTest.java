package jedis.test;

import jedis.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {
    @org.junit.Test
    public void Test1(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("username","zhangsan");
        jedis.close();

    }

    @org.junit.Test
    public void Test2(){
        Jedis jedis = new Jedis();
        jedis.set("username","zhangsan");
        //获取
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用setex()方法存储可以指定过期时间的key value
        jedis.setex("activecode",20,"hehe");  //过20秒后自动删除该键值对


        jedis.close();

    }

    /**
     * 哈希表数据结构操作
     */

    @org.junit.Test
    public void Test3(){
        Jedis jedis = new Jedis();
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","male");

        //获取hash
        String name = jedis.hget("user", "name");
        System.out.println(name);

        Map<String, String> user = jedis.hgetAll("user");
        Set<String> keySet = user.keySet();
        for(String key:keySet){
            String value = user.get(key);
            System.out.println(key+":"+value);
        }
        jedis.close();
    }


    /**
     * list操作
     */
    @org.junit.Test
    public void Test4(){
        Jedis jedis = new Jedis();
        jedis.lpush("mylist","a","b","c");
        jedis.rpush("mylist","a","b","c");
        //获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);
        String element1 = jedis.lpop("mylist");
        System.out.println(element1);
        List<String> mylist2 = jedis.lrange("mylist",0,-1);
        System.out.println(mylist2);
        jedis.close();
    }

    /**
     * set操作
     */
    @org.junit.Test
    public void Test5(){
        Jedis jedis = new Jedis();
        jedis.sadd("myset","java","c","javascript");
        //set获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        jedis.close();
    }

    /**
     * sortedSet操作
     */
    @org.junit.Test
    public void Test6(){
        Jedis jedis = new Jedis();
        jedis.zadd("mysortedset",3,"xiaohong");
        jedis.zadd("mysortedset",10,"dawang");
        jedis.zadd("mysortedset",2,"lisi");
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
        jedis.close();
    }

    /**
     * 连接池使用
     */
    @org.junit.Test
    public void test7(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
        //获取连接
        Jedis resource = jedisPool.getResource();
        resource.set("haha","heihei");
        //关闭，归还到连接池中
        resource.close();
    }

    /**
     * 工具类使用
     */
    @org.junit.Test
    public void test8(){
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("hello","world");
        //关闭，归还到连接池中
        jedis.close();
    }
}
