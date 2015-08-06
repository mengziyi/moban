package router;

import router.bean.DataNode;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**

  @author :chenyong
  @version 1.0
  @date 2013-3-12
 */

public class ConsistencyHash {
	
	private SortedMap<Long,DataNode> nodes;
	
	public DataNode getNode(final String key){
		//所有hash值都是小写
		  long hashKey = MurmurHash.getInstance().hash(key.toLowerCase().getBytes());
		  SortedMap<Long, DataNode> tailMap = nodes.tailMap(hashKey);
		  if(tailMap.isEmpty()){
			  hashKey = nodes.firstKey();
		  }else{
			  hashKey = tailMap.firstKey();
		  }		  
		  return nodes.get(hashKey);   		
	}
	

	public SortedMap<Long, DataNode> getNodes() {
		return nodes;
	}

	public void setNodes(SortedMap<Long, DataNode> nodes) {
		this.nodes = nodes;
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


	public static void main(String[] args) {
		  SortedMap<Integer, String> circle = new TreeMap<Integer, String>();
		HashFunction hf=new HashFunction();

		for(int i=0;i<6;i+=10) {
			circle.put(i, "节点"+i);

		}


        for(int i=0;i<10000;i++){
            String a="token("+i+")";

        }


        Map<Integer,String> redisMap=new HashMap<Integer,String>();
		Integer w=212;
		SortedMap<Integer, String> headMapMap = circle .headMap(w);
		SortedMap<Integer, String> tailMap = circle .tailMap(w);

		System.out.println(headMapMap.lastKey());
		System.out.println(tailMap.firstKey());

	}


}
