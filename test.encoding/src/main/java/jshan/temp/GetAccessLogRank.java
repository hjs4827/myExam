package jshan.temp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jshan.library.redisUtils.JedisManager;
import redis.clients.jedis.Jedis;

public class GetAccessLogRank {
	/**
	 * @param args
	 */
	public static void main(String[] args){
		Jedis jedis = null;
		JedisManager manager = JedisManager.getInstance();
		jedis = manager.getConnection(0);
		try {
			Set<String> keys =  jedis.keys("mediacnt17:*");
			Iterator<String> it = keys.iterator();
			int cnt = 0;
			String key = "";
			int sum = 0;
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			while(it.hasNext()){
				key = it.next();
//				System.out.println(key);
				cnt = Integer.parseInt(jedis.get(key));
				sum += cnt;
//				System.out.println(cnt);
				if(cnt > 5000){
					System.out.println(key +" : " +cnt);
					resultMap.put(key, cnt);
				}
			}
			resultMap.put("total cnt", sum);
			
		}catch(Exception e){
			e.toString();
		}
		
		
	}
}
