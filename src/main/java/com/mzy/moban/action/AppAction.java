//package com.mzy.moban.action;
//
//import com.mzy.moban.model.Brandinfo;
//import com.mzy.moban.service.AppService;
//import com.mzy.moban.util.FormatUtils;
//import com.mzy.moban.util.RedisCache;
//import com.opensymphony.xwork2.ActionSupport;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import redis.clients.jedis.Jedis;
//
//import java.text.ParseException;
//import java.util.*;
//
///**
// * Created by mengzy on 2015/7/16.
// */
//public class AppAction extends ActionSupport{
//    private static Log log = LogFactory.getLog(AppAction.class);
//    @Autowired
//    public AppService appService;
//    @Autowired
//    public RedisCache redisCache;
////    @Autowired
////    public BrandInfoMapper brandInfoMapper;
//
//    public List<Brandinfo> getBrandinfos() {
//        return brandinfos;
//    }
//
//    public void setBrandinfos(List<Brandinfo> brandinfos) {
//        this.brandinfos = brandinfos;
//    }
//
//    public List<Brandinfo> brandinfos;
//
//
//
//    public String userStatistics() throws Exception {
//        log.info("abc");
//
//
//
////        ImmutableList<String> imOflist=ImmutableList.of("peida","jerry","harry");
//
////        brandinfos=appService.getAllBrand();
////        log.info("size:"+brandinfos.size());
////       Set w= redisCache.smembers("m1");
////        System.out.println(w);
//
//        Jedis jedis=redisCache.getConnection();
//        jedis.del("w3ckey");
//        jedis.del("w3ckey1");
//Map a=new HashMap<String,String>();
//        a.put("name","redis tutorial");
//        a.put("description","redis basic commands for caching");
//        a.put("like","20");
//        a.put("visitors", "23000");
//        jedis.hmset("w3ckey1", a);
//        jedis.hset("w3ckey1", "like", "30");
//
//        jedis.hincrByFloat("w3ckey1", "like", 20.6);
//        a= jedis.hgetAll("w3ckey1");
//        System.out.println(a.get("like"));
//
//        System.out.println(jedis.hkeys("w3ckey1"));
//
//        redisCache.closeConnection(jedis);
//
//
////            HttpServletResponse response= ServletActionContext.getResponse();
////	/*
////	 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
////	 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
////	 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
////	 * */
////            response.setContentType("text/html;charset=utf-8");
////            PrintWriter out = response.getWriter();
////            //JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
////     //   List<App> m=appService.getAllApps();
////        out.println(JSON.toJSONString(p));
////            out.flush();
////            out.close();
//
//        //log.info(brandinfos);
//        return "www";
//
//    }
//
//
//    public String yemian() throws Exception {
//               //brandinfos=appService.getAllBrand();
//        Jedis jedis=redisCache.getConnection();
//        jedis.del("aaa");
//        jedis.del("bbb");
//        jedis.zadd("aaa",20,"20a");
//        jedis.zadd("aaa",40,"40a");
//
//        jedis.zadd("bbb",11,"11b");
//        jedis.zadd("bbb", 12, "12b");
//
//        jedis.zunionstore("ccc", "bbb","aaa");
//        System.out.println(jedis.zrange("ccc", 0, -1));
//        System.out.println(jedis.zrange("bbb",0,-1));
//
//
//
//        redisCache.closeConnection(jedis);
//
//
//        return "www";
//
//    }
//
//    public String msetm() throws Exception {
//        log.info("msetm");
//        Jedis jedis=redisCache.getConnection();
//
//        jedis.sadd("m1", "g", "h", "j","milk");
//       long srem= jedis.srem("m1", "a", "g");
//        log.error("srem:" + srem);
//
//
//        Long scard = jedis.scard("m1");
//        log.error("scard:" + scard);
//
//        jedis.sadd("food", "bread", "milk");
//         jedis.sunionstore("m1$food", "m1", "food");
//
//        jedis.sinterstore("m1_food", "m1", "food");
//
//
//        log.info("m1$food:" + jedis.smembers("m1$food"));
//        log.info("m1_food:"+jedis.smembers("m1_food"));
//
//
//        jedis.del("userList");
//        jedis.rpush("userList", "0", "1", "2", "3", "4", "5");
//        String q=jedis.lpop("userList");
//        System.out.println(q);
//
//
//        jedis.lset("userList", 1, "Nick Xu");
////        jedis.ltrim("userList", 2, 3);
//
//        System.out.println(jedis.lrange("userList", 2, 3));
//
//        Long llen = jedis.llen("userList");
//        System.out.println("llen:"+llen);
//
//
//        jedis.zadd("user", 22, "A");
//        jedis.zadd("user", 24, "A");
//        jedis.zadd("user", 21, "B");
//        jedis.zadd("user", 20, "CV");
//        Set<String> user = jedis.zrange("user", 0, -1);
//        System.out.println(user);
//
//
//
//
//
//
//        redisCache.closeConnection(jedis);
//
//
//
////        ImmutableList<String> imOflist=ImmutableList.of("peida","jerry","harry");
//
//
//
//
//        return "www";
//
//    }
//
//
//
//
//
//
//    public static void main(String[] args) throws ParseException {
//        String a="1990-02-28";
//        Date cc= FormatUtils.parseDate(a);
//        System.out.println(cc);
//
//        System.out.println((char)63);
//        System.out.println((char)61);
//        System.out.println((char) 38);
//
//
//
//
//    }
//
//
//
//
//
//}
