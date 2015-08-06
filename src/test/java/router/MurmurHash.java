package router;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: ifeng.com</p>
 * @author :Qi LuPeng
 * @version 1.0
 * <p>--------------------------------------------------------------------</p>
 * <p>date                   author                    reason             </p>
 * <p>2010-12-29	        Qi LuPeng               create the class      </p>
 * <p>--------------------------------------------------------------------</p>
 */

public class MurmurHash {
	  private static MurmurHash _instance = null;
	  
	  public static MurmurHash getInstance() {
		_instance = new MurmurHash();
	    return _instance;
	  }
	  
	  public int hash(byte[] bytes) {
		    return hash(bytes, bytes.length, -1);
		  }
		  
	  /**
	   * MurmurHash绠楁硶
	   * @param data---琚玥ash鐨勫瓧鑺傛暟缁�
	   * @param length---鏁扮粍闀垮害
	   * @param seed---绠楁硶鐨勭瀛�
	   * @return int---鍝堝笇鍚庣殑鏁村舰鍊�
	   */
	  public int hash(byte[] data, int length, int seed) {
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
