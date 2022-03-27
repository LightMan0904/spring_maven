package com.atguigu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author light
 * @create 2022-03-26 20:15
 */
public class JedisUtils {
    private static  volatile JedisPool jedisPool = null;
    private JedisUtils(){}
    public static JedisPool getJedisPool(){
        if(jedisPool == null){
            synchronized (JedisUtils.class){
                if(jedisPool == null){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxTotal(100*100);
                    poolConfig.setMaxWaitMillis(100*100);
                    jedisPool = new JedisPool(poolConfig,"127.0.0.1");
                }
            }
        }
        return jedisPool;
    }
    public static void releaseJedis(Jedis jedis){
        jedis.close();
    }
}
