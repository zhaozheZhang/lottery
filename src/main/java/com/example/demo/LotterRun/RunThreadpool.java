package com.example.demo.LotterRun;

import com.example.demo.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.*;

public class RunThreadpool {
    private static Jedis jedis = JedisPoolUtil.getJedis();

    public static void contextLoads(int num) {
        InitData.initData(jedis);
        ExecutorService thredPool = new ThreadPoolExecutor(
                10,
                20,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(300000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= num; i++) {
            LotterRun lotterRun = new LotterRun();
            lotterRun.setUserId(i);
            thredPool.execute(lotterRun);
        }
    }
}
