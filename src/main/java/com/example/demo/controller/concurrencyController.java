package com.example.demo.controller;

import com.example.demo.LotterRun.RunThreadpool;
import com.example.demo.util.JedisPoolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import java.util.List;

@Controller
public class concurrencyController {
    private static Jedis jedis = JedisPoolUtil.getJedis();

    @RequestMapping("/1")
    public String cur0(Model model){
        long startTime = System.currentTimeMillis();
        RunThreadpool.contextLoads(1);
        long endTime = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list1 = jedis.lrange("1stUserList",0,-1);
        List<String> list2 = jedis.lrange("2stUserList",0,-1);
        List<String> list3 = jedis.lrange("3stUserList",0,-1);
        model.addAttribute("info","程序执行时间"+(endTime - startTime)+"ms"+" 一等奖:"+list1.size()+" 二等奖:"+list2.size()+" 三等奖:"+list3.size());
        model.addAttribute("st1",list1);
        model.addAttribute("st2",list2);
        model.addAttribute("st3",list3);
        if (list1.size() == 0 && list2.size() == 0 && list3.size() == 0){
            model.addAttribute("noAward","1");
        }
        return "index";
    }

    @RequestMapping("/1000")
    public String cur1(Model model){
        long startTime = System.currentTimeMillis();
        RunThreadpool.contextLoads(1000);
        long endTime = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list1 = jedis.lrange("1stUserList",0,-1);
        List<String> list2 = jedis.lrange("2stUserList",0,-1);
        List<String> list3 = jedis.lrange("3stUserList",0,-1);
        model.addAttribute("info","程序执行时间"+(endTime - startTime)+"ms"+" 一等奖:"+list1.size()+" 二等奖:"+list2.size()+" 三等奖:"+list3.size());
        model.addAttribute("st1",list1);
        model.addAttribute("st2",list2);
        model.addAttribute("st3",list3);
        return "index";
    }
    @RequestMapping("/10000")
    public String cur2(Model model){
        long startTime = System.currentTimeMillis();
        RunThreadpool.contextLoads(10000);
        long endTime = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list1 = jedis.lrange("1stUserList",0,-1);
        List<String> list2 = jedis.lrange("2stUserList",0,-1);
        List<String> list3 = jedis.lrange("3stUserList",0,-1);
        model.addAttribute("info","程序执行时间"+(endTime - startTime)+"ms"+" 一等奖:"+list1.size()+" 二等奖:"+list2.size()+" 三等奖:"+list3.size());
        model.addAttribute("st1",list1);
        model.addAttribute("st2",list2);
        model.addAttribute("st3",list3);
        return "index";
    }
    @RequestMapping("/100000")
    public String cur3(Model model){
        long startTime = System.currentTimeMillis();
        RunThreadpool.contextLoads(100000);
        long endTime = System.currentTimeMillis();
        List<String> list1 = jedis.lrange("1stUserList",0,-1);
        List<String> list2 = jedis.lrange("2stUserList",0,-1);
        List<String> list3 = jedis.lrange("3stUserList",0,-1);
        model.addAttribute("info","程序执行时间"+(endTime - startTime)+"ms"+" 一等奖:"+list1.size()+" 二等奖:"+list2.size()+" 三等奖:"+list3.size());
        model.addAttribute("st1",list1);
        model.addAttribute("st2",list2);
        model.addAttribute("st3",list3);
        return "index";
    }
}
