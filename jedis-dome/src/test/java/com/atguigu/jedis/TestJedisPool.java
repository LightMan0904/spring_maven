package com.atguigu.jedis;

import com.atguigu.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author light
 * @create 2022-03-26 20:21
 */
public class TestJedisPool {
    @Test
    public void test1(){
        Jedis jedis = null;
        try {
            JedisPool jedisPool = JedisUtils.getJedisPool();
            jedis =  jedisPool.getResource();
            System.out.println(jedis.ping());
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
        }
    }
}
