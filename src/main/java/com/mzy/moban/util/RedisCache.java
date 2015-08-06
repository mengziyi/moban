package com.mzy.moban.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisCache {

	private static Log log = LogFactory.getLog(RedisCache.class);
	
	private JedisPool jedisPool;

	public boolean set(String key,String value,int expire) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			String result=jedis.set(key, value);
			Long info=jedis.expire(key, expire);
			if(info==0){
				throw new Exception("set expire error");
			}
			if(result.equals("OK")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}

	public Jedis getConnection() {
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis;
	}


	public void closeConnection(Jedis jedis) {
		if (null != jedis) {
			try {
				jedisPool.returnResource(jedis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	public boolean set(String key,String value) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			String result=jedis.set(key, value);
			if(result.equals("OK")){
				return true;
			}else{

				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public String get(String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.get(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public String getRange(String key,long startOffset,long endOffset) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.getrange(key, startOffset, endOffset);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public List<String> getlRange(String key,long start,long end) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.lrange(key, start, end);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	/*
	 * 移除list中指定索引号的项
	 */
	public void RemoveLIndex(String key,long index) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.lset(key, index,"__delete__");
			jedis.lrem(key, 0,"__delete__");
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
		
		
	}
	
	public Map<String,String> getMap(String key){
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.hgetAll(key);
		}catch(Exception e){
			log.error(e);
		}finally{
			this.release(jedis);
		}
		return null;
	}
	
	public boolean setMap(String key,Map<String,String> hash,int expire) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			for(Object k : hash.keySet()){
				if(hash.get(k)==null){
					hash.remove(k);
				}
			}
			String result=jedis.hmset(key, hash);
			Long info=jedis.expire(key, expire);
			if(info==0){
				throw new Exception("set expire error");
			}
			if(result.equals("OK")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public boolean setMap(String key,Map<String,String> hash) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			for(Object k : hash.keySet()){
				if(hash.get(k)==null){
					hash.remove(k);
				}
			}
			String result=jedis.hmset(key, hash);
			if(result.equals("OK")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public List<String> blpop(int time,String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.blpop(time, keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public boolean exists(String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.exists(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public String lpop(String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			String result = jedis.lpop(key);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}

	public List<String> brpop(int time,String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.brpop(time, keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public String rpop(String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			String result = jedis.rpop(key);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public Long lpush(String key, String string) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			Long result=jedis.lpush(key, string);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public Long rpush(String key, String string) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			Long result=jedis.rpush(key, string);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public  Long llen(String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.llen(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}


    public Long hset(String key, String filed, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hset(key, filed, value);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }finally {
            this.release(jedis);
        }
    }


    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }finally {
            this.release(jedis);
        }
    }


	public boolean sadd(String key,String... member) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			Long result=jedis.sadd(key, member);
			if(result==member.length){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}

	public Long scard(String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.scard(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
		
	}
	
	public String spop(String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.spop(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	

	public Set<String> sunion(String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.sunion(keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	public Set<String> sinter(String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.sinter(keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}


	public long sinterstore(String dest ,String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.sinterstore(dest, keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	
	/**
	 * 用于Monitor 状态监控
	 * @return 当前所存Key的个数
	 * @throws Exception
	 */
	public Long dbsize ()throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.dbSize();
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.release(jedis);
		}
	}
	
	
	public long del(String hKey) throws Exception {
		Jedis jedis=null;
		try {
			jedis = getResource();
			if(jedis != null) {
				String[] keys = new String[1];
				keys[0] = hKey;
				return jedis.del(keys);
			}
		} catch(Exception e) {
			log.error("Can't get jedis(delMap)." + hKey);
			throw e;
		} finally {
			this.release(jedis);
		}
		return 0;
	}
	
	private Jedis getResource(){
		return jedisPool.getResource();
	}
	
	public Jedis getRedis(){
		return getResource();
	}
	
	private boolean release(Jedis jedis) {
		if (jedisPool != null && jedis != null) {
			jedisPool.returnResource(jedis);
			return true;
		}
		return false;
	}

	public boolean set(String url,String key,String value,int expire) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			String result=jedis.set(key, value);
			Long info=jedis.expire(key, expire);
			if(info==0){
				throw new Exception("set expire error");
			}
			if(result.equals("OK")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public long incr(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			long newVal = jedis.incr(key);
			return newVal;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public long incrby(String url,String key, long increment) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			long newVal = jedis.incrBy(key, increment);
			return newVal;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public long decr(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			long newVal = jedis.decr(key);
			return newVal;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public long decrby(String url,String key, long decrement) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			long newVal = jedis.decrBy(key, decrement);
			return newVal;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public boolean set(String url,String key,String value) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			String result=jedis.set(key, value);
			if(result.equals("OK")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public String get(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.get(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public String getRange(String url,String key,long startOffset,long endOffset) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.getrange(key, startOffset, endOffset);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public List<String> getlRange(String url,String key,long start,long end) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.lrange(key, start, end);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public Map<String,String> getMap(String url,String key){
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.hgetAll(key);
		}catch(Exception e){
			log.error(e);
		}finally{
			this.releaceResource(jedis);
		}
		return null;
	}

	public boolean setMap(String url,String key,Map<String,String> hash,int expire) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			for(Object k : hash.keySet()){
				if(hash.get(k)==null){
					hash.remove(k);
				}
			}
			String result=jedis.hmset(key, hash);
			Long info=jedis.expire(key, expire);
			if(info==0){
				throw new Exception("set expire error");
			}
			if(result.equals("OK")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public boolean setMap(String url,String key,Map<String,String> hash) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			for(Object k : hash.keySet()){
				if(hash.get(k)==null){
					hash.remove(k);
				}
			}
			String result=jedis.hmset(key, hash);
			if(result.equals("OK")){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public List<String> blpop(String url,int time,String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.blpop(time, keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public String lpop(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			String result = jedis.lpop(key);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public List<String> brpop(String url,int time,String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.brpop(time, keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public String rpop(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			String result = jedis.rpop(key);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public Long lpush(String url,String key, String string) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			Long result=jedis.lpush(key, string);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public Long rpush(String url,String key, String string) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			Long result=jedis.rpush(key, string);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public  Long llen(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.llen(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public boolean sadd(String url,String key,String... member) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			Long result=jedis.sadd(key, member);
			if(result==1){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public Long scard(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.scard(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
		
	}

	public String spop(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.spop(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public Set<String> sunion(String url,String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.sunion(keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	public Set<String> sinter(String url,String... keys) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.sinter(keys);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	
	/**
	 * 用于Monitor 状态监控
	 * @return 当前所存Key的个数
	 * @throws Exception
	 */

	public Long dbsize (String url)throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			return jedis.dbSize();
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public long hset(String url,String key,String field,String value) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			long result = jedis.hset(key, field, value);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	public String hget(String url,String key,String field) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			String result = jedis.hget(key, field);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	public long hdel(String url,String key,String field) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			long result = jedis.hdel(key, field);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	
	public Set<String>  hkeys(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			Set<String> result = jedis.hkeys(key);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public Map<String,String> hgetAll(String url,String key) throws Exception{
		Jedis jedis=null;
		try{
			jedis = getJedis(url);
			Map<String,String> result = jedis.hgetAll(key);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	public Map<String,String> hgetAll(String key)throws Exception{
		Jedis jedis=null;
		try{
			jedis = getResource();
			Map<String,String> result = jedis.hgetAll(key);
			return result;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	
	//TTL 返回给定key的剩余生存时间(time to live)(以秒为单位)
	public int ttl(String key) throws Exception {
		Jedis jedis=null;
		try{
			jedis = getResource();
			int ttl = Integer.parseInt((jedis.ttl(key)) + "");
			return ttl;
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	/*
	 * 释放资源
	 */
	private boolean releaceResource(Jedis jedis) {
		if (jedisPool != null && jedis != null) {
			jedisPool.returnResource(jedis);
			return true;
		}
		return false;
	}

	public void expire(String key, int seconds) throws Exception {
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.expire(key, seconds);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}
	
	private Jedis getJedis(String url){
		String[] param = url.split(":");
		return new Jedis (param[0],Integer.parseInt(param[1]));
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public void lset(String key, int index, String value) throws Exception {
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.lset(key, index, value);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
		
	}

	public void lrem(String key, int index, String value) throws Exception {
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.lrem(key, index, value);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
		
	}

	public Long incrBy(String key, int count) throws Exception {
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.incrBy(key,count);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

	public Set<String> smembers(String key) throws Exception {
		Jedis jedis=null;
		try{
			jedis = getResource();
			return jedis.smembers(key);
		}catch(Exception e){
			log.error(e);
			throw e;
		}finally{
			this.releaceResource(jedis);
		}
	}

}
