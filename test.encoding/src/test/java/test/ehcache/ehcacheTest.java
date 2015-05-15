package test.ehcache;

import java.util.Random;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;

/**
 * @author hjs4827
 * 참고 사이트 
 * http://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte2:fdl:ehcache
 * http://javacan.tistory.com/123
 * http://ehcache.org/documentation/2.8/code-samples
 */
public class ehcacheTest {
	@Test
	public void ehcacheStoreFileTest(){
		CacheManager cm =  CacheManager.create();
		Cache ch =  cm.getCache("MediaCodeCache");
		// 캐쉬 데이터 저장
		for (int i = 0; i < 15000; i++) {
			ch.put(new Element("key"+i, "value"+i));
		}
		System.out.println("size>>>"+ch.getSize());
		System.out.println("MemoryStoreSize>>>"+ch.getMemoryStoreSize()); //현제 메모리 객체 갯수
		System.out.println("DiskStoreSize>>>"+ch.getDiskStoreSize()); //디스크에 저장된 객체 갯수
		
		// 히트
		Random rNum = new Random();
		int keyIdx = 0;
		String value = "";
		for(int i=0; i< 10000; i++){
			keyIdx = rNum.nextInt(100);
			value = ch.get("key"+keyIdx).toString();
			System.out.println(value);
		}
		System.out.println("InMemoryHits>>>"+ch.getStatistics().getInMemoryHits());
		System.out.println("OnDiskHits>>>"+ch.getStatistics().getOnDiskHits());
		System.out.println("CacheMisses>>>"+ch.getStatistics().getCacheMisses());
	}
}
