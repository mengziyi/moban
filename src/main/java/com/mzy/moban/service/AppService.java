//package com.mzy.moban.service;
//
//import com.mzy.moban.mapper.AppMapper;
//import com.mzy.moban.mapper.BrandInfoMapper;
//import com.mzy.moban.model.Brandinfo;
//import com.mzy.moban.util.RedisCache;
//import com.mzy.moban.util.SerializeUtil;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import redis.clients.jedis.Jedis;
//
//import java.util.List;
//
///**
// * Created by mengzy on 2015/7/16.
// */
//
//@Service
//public class AppService {
//    @Autowired
//    public AppMapper appMapper;
//    @Autowired
//    public BrandInfoMapper brandInfoMapper;
//    @Autowired
//    public RedisCache redisCache;
//    private static Log log = LogFactory.getLog(AppService.class);
//
//
//    public List<Brandinfo> getAllBrand() throws Exception {
//        List<Brandinfo> brandinfos=null;
//
//
//
//
//        Jedis jedis=redisCache.getConnection();
//        boolean p=jedis.exists("brandinfos".getBytes());
//
//        redisCache.closeConnection(jedis);
//
//        if(p) {
//            log.error("命中缓存");
//            brandinfos = (List<Brandinfo>) SerializeUtil.unserialize(jedis.get("brandinfos".getBytes()));
//            System.out.println(brandinfos.size());
//            log.error(brandinfos.get(0).getBrandNameCn());
//            log.error(redisCache.ttl("brandinfos"));
//        }
//        else{
//            log.error("不命中");
//            brandinfos = brandInfoMapper.get(new Brandinfo());
//            jedis=redisCache.getConnection();
//            jedis.set("brandinfos".getBytes(), SerializeUtil.serialize(brandinfos));
//            jedis.expire("brandinfos".getBytes(), 10);
//            redisCache.closeConnection(jedis);
//
//            log.error("添加定时缓存");
//
//
//        }
//
////        MyListener listener = new MyListener();
////        Jedis jedis=redisCache.getConnection();
////        jedis.psubscribe(listener, new String[]{"hello_*"});//使用模式匹配的方式设置频道
////        jedis.publish("hello_foo", "bar123");
////        jedis.publish("hello_test", "hello watson");
////        redisCache.closeConnection(jedis);
//
//
//
//
//
//
//
//
//        return brandinfos;
//    }
//}
//
//
//
//
//
//
//
//
//
