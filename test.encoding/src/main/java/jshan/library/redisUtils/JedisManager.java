package jshan.library.redisUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.exceptions.JedisException;
//import com.adgather.util.NetworkUtils;

/**
 * POOL 관리
 * @author Administrator
 */
public class JedisManager {
	/**
	 * logger
	 */
	static Logger logger = Logger.getLogger(JedisManager.class);
	/**
	 * PORT
	 */
	protected static final int REDIS_PORT = 6379;
	
	/**
	 * CONNECTION LIST
	 */
	private final Set<Jedis> connectionList = new HashSet<Jedis>();
	/**
	 * JEDISPOOL
	 */
	private final JedisPool pool;
  
	/**
	 * POOL 생성
	 */
    public JedisManager() {
    	String redis_host = "119.205.215.106";
    	String redis_pw = "Fpeltmdbwj2015!)^";
    	this.pool = new JedisPool(new JedisPoolConfig(),redis_host, REDIS_PORT,10000,redis_pw);
    }
    
    /**
     * INSTANCE
     * @author Administrator
     */
    private static class LazyHolder {
		private static final JedisManager INSTANCE = new JedisManager();
	}
    
    /**
     * RETURN INSTANCE
     * @return
     */
	public static JedisManager getInstance() {
		return LazyHolder.INSTANCE;
	}
    
    /**
     * JEDIS CONNECTION
     * @return
     */
	final public Jedis getConnection(int db) {
		try {
			Jedis jedis = this.pool.getResource();
			jedis.select(db);
			this.connectionList.add(jedis);
			return jedis;
		}
		catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
	
	final public Jedis getConnection() {
		Jedis jedis = this.pool.getResource();
		this.connectionList.add(jedis);
		return jedis;
	}
	
	/**
	 * POOL DESTORY
	 */
	final public void destoryPool() {
		Iterator<Jedis> jedisList = this.connectionList.iterator();
		while (jedisList.hasNext()) {
			Jedis jedis = jedisList.next();
			this.pool.returnResource(jedis);
		}
		this.pool.destroy();
	}
	
	/**
	 * 자원회수
	 * @param jedis
	 */
	final public void returnResource(Jedis jedis)	{
		this.pool.returnResource(jedis);
	}
	
	/**
	 * 에러발생 자원회수
	 * @param jedis
	 */
	final public void returnBrokenResource(Jedis jedis)	{
		this.pool.returnBrokenResource(jedis);
	}
    
}  
