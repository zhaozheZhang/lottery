package com.example.demo.util;

import redis.clients.jedis.Jedis;


/***
 * Jedis工具类
 */
public class JedisUtil {


    /**
     * 设置Strring类型的值
     * @param key
     * @param value
     * @return
     */
    public static boolean setKey(String key, String value) {

        Jedis jedis = null;

        try {
            jedis = JedisPoolUtil.getJedis();
            String set = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return true;

    }


    /***
     * 获取String类型的值
     * @param key
     * @return
     */
    public static String getValue(String key) {

        Jedis jedis = null;
        String value = "";
        try {
            jedis = JedisPoolUtil.getJedis();
            value = jedis.get(key);
            return value;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return value;
    }

    /***
     * 根据key值删除数据,可批量删除
     * @param keys
     * @return
     */
    public static boolean delKey (String... keys) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            jedis.del(keys);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return false;
    }


    /**
     * 判断key值是否存在
     * @param key
     * @return
     */
    public static boolean isExists(String key) {
        Jedis jedis = null;
        boolean isExists = false;
        try{
            jedis = JedisPoolUtil.getJedis();
            isExists = jedis.exists(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return isExists;
    }


}

