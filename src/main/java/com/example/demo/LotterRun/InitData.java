package com.example.demo.LotterRun;

import com.example.demo.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

public class InitData {


    public static void initData(Jedis jedis) {
        jedis.flushAll();
        jedis.set("1stAwardPeople","0");
        jedis.set("2stAwardPeople","0");
        jedis.set("3stAwardPeople","0");
        jedis.set("noPricePeople","0");
        jedis.set("totalPeople","0");
        jedis.set("1stAwardRemain","10");
        jedis.set("2stAwardRemain","100");
        jedis.set("3stAwardRemain","500");
    }

    public static void initNo1Award(Jedis jedis){
        jedis.watch("1stAwardRemain");
        //开启redis事物
        Transaction tx = jedis.multi();
        //补充一等奖
        tx.incrBy("1stAwardRemain", 10);
        //提交事务，如果此时watch key被改动了，则返回null
        List<Object> listObj = tx.exec();
        if (!listObj.isEmpty()) {
            System.out.println("一等奖已补充");
        } else {
            if (jedis.get("1stAwardRemain").equals("0")){
                initNo1Award(jedis);
            }
        }
    }
    public static void initNo2Award(Jedis jedis){
        jedis.watch("2stAwardRemain");
        //开启redis事物
        Transaction tx = jedis.multi();
        //补充一等奖
        tx.incrBy("2stAwardRemain", 100);
        //提交事务，如果此时watch key被改动了，则返回null
        List<Object> listObj = tx.exec();
        if (!listObj.isEmpty()) {
            System.out.println("二等奖已补充");
        } else {
            if (jedis.get("2stAwardRemain").equals("0")){
                initNo2Award(jedis);
            }
        }
    }
    public static void initNo3Award(Jedis jedis){
        jedis.watch("3stAwardRemain");
        //开启redis事物
        Transaction tx = jedis.multi();
        //补充一等奖
        tx.incrBy("3stAwardRemain", 500);
        //提交事务，如果此时watch key被改动了，则返回null
        List<Object> listObj = tx.exec();
        if (!listObj.isEmpty()) {
            System.out.println("三等奖已补充");
        } else {
            if (jedis.get("3stAwardRemain").equals("0")){
                initNo3Award(jedis);
            }
        }
    }
    public static void checkAwardRemain(Jedis jedis){
        if (jedis.get("1stAwardRemain").equals("0")){
            initNo1Award(jedis);
        }
        if (jedis.get("2stAwardRemain").equals("0")){
            initNo2Award(jedis);
        }
        if (jedis.get("3stAwardRemain").equals("0")){
            initNo3Award(jedis);
        }
    }
}
