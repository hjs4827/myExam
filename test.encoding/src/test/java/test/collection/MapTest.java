package test.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;



public class MapTest {
	@Test
	public void mapSortingByValues(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		map.put("D", 4);
		map.put("E", 5);
		map.put("F", 6);
		map.put("G", 7);
		System.out.println("before sorting");
		Set<Entry<String,Integer>> set= map.entrySet();
		
		Iterator<Entry<String,Integer>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			System.out.print(entry.getKey()+" : ");
			System.out.println(entry.getValue());
		}
		
		// do sort
		List<Entry<String,Integer>> list = new LinkedList<Entry<String,Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String,Integer>>() {

			public int compare(	Entry<String, Integer> o1,
								Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		// LinkedHashMap은 순서값을 보장한다.
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		it = list.iterator();
		while(it.hasNext()){
			Map.Entry<String, Integer> entry = it.next();
			sortMap.put(entry.getKey(), entry.getValue());
		}
		
		System.out.println("after sorting");
		Set<Entry<String, Integer>> set2 = sortMap.entrySet();
		it = set2.iterator();
		while(it.hasNext()){
			Map.Entry<String, Integer> entry = it.next();
			System.out.print(entry.getKey()+" : ");
			System.out.println(entry.getValue());
		}
	}
}
