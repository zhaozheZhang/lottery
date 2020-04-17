package com.example.demo;

import com.example.demo.LotterRun.InitData;
import com.example.demo.LotterRun.LotterRun;
import com.example.demo.util.JedisPoolUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.concurrent.*;

@SpringBootTest
class DemoApplicationTests {
    private static Jedis jedis = JedisPoolUtil.getJedis();
    @Test
    void contextLoads() {
        InitData.initData(jedis);
        ExecutorService thredPool = new ThreadPoolExecutor(
                10,
                20,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(300000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= 5000; i++) {
            LotterRun lotterRun = new LotterRun();
            lotterRun.setUserId(i);
            thredPool.execute(lotterRun);
        }
    }
}
