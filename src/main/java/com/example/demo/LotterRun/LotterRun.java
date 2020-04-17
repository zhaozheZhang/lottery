package com.example.demo.LotterRun;


import com.example.demo.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LotterRun implements Runnable{

    private Jedis jedis = JedisPoolUtil.getJedis();
    private Integer userId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        InitData.checkAwardRemain(jedis);
        getPrize();
    }

    public void getPrize(){
        Random random = new Random();
        int i = random.nextInt(99);
        if (i<5){
            redisTransaction(1);
        }else if(i < 15){
            redisTransaction(2);
        }else if(i < 30){
            redisTransaction(3);
        }else {
            redisTransaction(4);
        }
    }
    public void redisTransaction(int type){
        if (type == 1){
            jedis.watch("totalPeople");
            jedis.watch("1stAwardPeople");
            jedis.watch("1stAwardRemain");
            Transaction tx = jedis.multi();
            tx.incr("totalPeople");
            tx.incr("1stAwardPeople");
            tx.decr("1stAwardRemain");
            List<Object> listObj = tx.exec();
            if (listObj.isEmpty()) {
                redisTransaction(1);
            }else {
                jedis.lpush("1stUserList",userId.toString());
                JedisPoolUtil.returnJedis(jedis);
            }
        } else if(type == 2){
            jedis.watch("totalPeople");
            jedis.watch("2stAwardPeople");
            jedis.watch("2stAwardRemain");
            Transaction tx = jedis.multi();
            tx.incr("totalPeople");
            tx.incr("2stAwardPeople");
            tx.decr("2stAwardRemain");
            List<Object> listObj = tx.exec();
            if (listObj.isEmpty()) {
                redisTransaction(2);
            }else {
                jedis.lpush("2stUserList",userId.toString());
                JedisPoolUtil.returnJedis(jedis);
            }
        }else if(type == 3) {
            jedis.watch("totalPeople");
            jedis.watch("3stAwardPeople");
            jedis.watch("3stAwardRemain");
            Transaction tx = jedis.multi();
            tx.incr("totalPeople");
            tx.incr("3stAwardPeople");
            tx.decr("3stAwardRemain");
            List<Object> listObj = tx.exec();
            if (listObj.isEmpty()) {
                redisTransaction(3);
            } else {
                jedis.lpush("3stUserList",userId.toString());
                JedisPoolUtil.returnJedis(jedis);
            }
        }else if(type == 4) {
            jedis.watch("totalPeople");
            jedis.watch("noPricePeople");
            Transaction tx = jedis.multi();
            tx.incr("totalPeople");
            tx.incr("noPricePeople");
            List<Object> listObj = tx.exec();
            if (listObj.isEmpty()) {
                redisTransaction(4);
            } else {
                JedisPoolUtil.returnJedis(jedis);
            }
        }
    }
}
