package vvv;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

public class ConsistentHash<T> {

    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this .hashFunction = hashFunction;
        this .numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle .put(hashFunction .hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle .remove(hashFunction .hash(node.toString() + i));
        }
    }

    public T get(String key) {
        if (circle .isEmpty()) {
            return null ;
        }
        int hash = hashFunction .hash(key);
        // System.out.println("hash---: " + hash);
        if (!circle .containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle .tailMap(hash);
            hash = tailMap.isEmpty() ? circle .firstKey() : tailMap.firstKey();
        }
        // System.out.println("hash---: " + hash);
        return circle .get(hash);
    }

    static class HashFunction {
        int hash(String key) {
            //md5加密后，hashcode
            return hashm(key.getBytes());
        }





        public int hashm(byte[] bytes) {
            return hashm(bytes, bytes.length, -1);
        }

        /**
         * MurmurHash绠楁硶
         * @param data---琚玥ash鐨勫瓧鑺傛暟缁�
         * @param length---鏁扮粍闀垮害
         * @param seed---绠楁硶鐨勭瀛�
         * @return int---鍝堝笇鍚庣殑鏁村舰鍊�
         */
        public int hashm(byte[] data, int length, int seed) {
            int m = 0x5bd1e995;
            int r = 24;

            int h = seed ^ length;

            int len_4 = length >> 2;

            for (int i = 0; i < len_4; i++) {
                int i_4 = i << 2;
                int k = data[i_4 + 3];
                k = k << 8;
                k = k | (data[i_4 + 2] & 0xff);
                k = k << 8;
                k = k | (data[i_4 + 1] & 0xff);
                k = k << 8;
                k = k | (data[i_4 + 0] & 0xff);
                k *= m;
                k ^= k >>> r;
                k *= m;
                h *= m;
                h ^= k;
            }

            // avoid calculating modulo
            int len_m = len_4 << 2;
            int left = length - len_m;

            if (left != 0) {
                if (left >= 3) {
                    h ^= (int) data[length - 3] << 16;
                }
                if (left >= 2) {
                    h ^= (int) data[length - 2] << 8;
                }
                if (left >= 1) {
                    h ^= (int) data[length - 1];
                }

                h *= m;
            }

            h ^= h >>> 13;
            h *= m;
            h ^= h >>> 15;

            return h;
        }
    }












    public static void main(String [] args) {
        JedisPoolConfig w=new JedisPoolConfig();
        w.setMaxTotal(4);
        w.setMaxIdle(4);

        JedisPool jd=new JedisPool(w,"127.0.0.1",6379);
        Jedis jedis=jd.getResource();

        HashSet< String> set = new HashSet< String>();
        set.add( "app1-1" );
        set.add( "app1-2" );
        set.add( "app2-1" );
        set.add( "app2-2" );
        set.add( "app3-1" );
        set.add( "app3-2" );
//        set.add( "D" );
//        set.add( "H" );
//        set.add( "G" );
//        set.add( "I" );


        Map< String, Integer> map = new HashMap< String, Integer>();

        ConsistentHash< String> consistentHash = new ConsistentHash<String>( new HashFunction(), 5000, set);

        int count = 50000;


Set<String> p=jedis.keys("app*");
        for(String k:p){
            jedis.del(k);
        }
//        for (int i = 0; i < count; i++) {
//            String key = consistentHash.get("token"+i);
//            if (map.containsKey(key)) {
//                map.put(consistentHash.get("token"+i), map.get(key) + 1);
//                jedis.hsetnx(key,"token"+i,"aaaaaaaaaaaaaaaaaaaa");
//            } else {
//                map.put(consistentHash.get("token"+i), 1);
//                jedis.hsetnx(key,"token"+i,"aaaaaaaaaaaaaaaaaaaa");
//            }
//            // System.out.println(key);
//        }

        showServer(map);
        map.clear();
        System.out.println(jedis.hkeys("app1-1").size());
//        consistentHash.remove( "A" );
//
//        System. out .println("------- remove A" );
//
//        for (int i = 0; i < count; i++) {
//            String key = consistentHash.get(i+"");
//            if (map.containsKey(key)) {
//                map.put(consistentHash.get(i+""), map.get(key) + 1);
//            } else {
//                map.put(consistentHash.get(i+""), 1);
//            }
//            // System.out.println(key);
//        }
//
//        showServer(map);
//        map.clear();
//        consistentHash.add( "E" );
//        System. out .println("------- add E" );
//
//        for (int i = 0; i < count; i++) {
//            String key = consistentHash.get(i+"");
//            if (map.containsKey(key)) {
//                map.put(consistentHash.get(i+""), map.get(key) + 1);
//            } else {
//                map.put(consistentHash.get(i+""), 1);
//            }
//            // System.out.println(key);
//        }
//
//        showServer(map);
//        map.clear();
//
//        consistentHash.add( "F" );
//        System. out .println("------- add F服务器  业务量加倍" );
//        count = count * 2;
//        for (int i = 0; i < count; i++) {
//            String key = consistentHash.get(i+"");
//            if (map.containsKey(key)) {
//                map.put(consistentHash.get(i+""), map.get(key) + 1);
//            } else {
//                map.put(consistentHash.get(i+""), 1);
//            }
//            // System.out.println(key);
//        }
//
//        showServer(map);

    }

    public static void showServer(Map<String , Integer> map) {
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            System. out .println("服务器 " + m.getKey() + "----" + m.getValue() + "个" );
        }
    }

}    