package jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jedis工具类
 */
public class JedisPoolUtils {
    private static JedisPool jedisPool;
    static {
        InputStream rs = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建properties对象
        Properties properties = new Properties();
        //关联文件
        try {
            properties.load(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        //初始化jedispool
        jedisPool = new JedisPool(config,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));

    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
