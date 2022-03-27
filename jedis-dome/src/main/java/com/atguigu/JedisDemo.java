package com.atguigu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.Random;

/**
 * @author light
 * @create 2022-03-22 19:21
 */
public class JedisDemo {
    public static void main(String[] args) {
        insertCode("123123");
//        verifyCode("123123","585014");
        System.out.println("hot-fix test");
    }

    public void test1(){
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
    }

    public static void verifyCode(String phone,String code){
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        String codeKey = "VerifyCode:" + phone + ":code";
        String vcode = jedis.get(codeKey);
        if(code.equals(vcode)){
            System.out.println("code is true");
        }else {
            System.out.println("code is false");
        }
        jedis.close();
    }


    public static void insertCode(String phone){
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        String countKey = "VerifyCode:" + phone +":count";
        String codeKey = "VerifyCode:" + phone + ":code";
        String phoneCount = jedis.get(countKey);
        if(phoneCount == null){
            jedis.setex(countKey,24*60*60,"1");
        }else if(Integer.parseInt(phoneCount) <= 2){
            jedis.incr(countKey);
        }else {
            System.out.println("cant send");
            jedis.close();
        }
        String code = getCode();
        jedis.setex(codeKey,120,code);
        jedis.close();
    }

    // 1. 获取验证码
    public static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
