package test.json;

import static org.junit.Assert.*;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;

public class JsonTest {

	@Test
	public void test() {
		Member member = new Member("한종상", "20", "서울시", "010-3497-7897");
		
		// bean -> json
		JSONObject jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(member));
		System.out.println("toString >>>>" + jsonObject.toString());
		// toString >>>>{"address":"서울시","age":"20","name":"한종상","tel":"010-3497-7897"}
		
		String name = (String) jsonObject.get("name");
		String age = (String) jsonObject.get("age");
		String address = (String) jsonObject.get("address");
		String tel = (String) jsonObject.get("tel");
		
		assertEquals("한종상", name);
		assertEquals("20", age);
		assertEquals("서울시", address);
		assertEquals("010-3497-7897", tel);
		
		// json -> bean
		Member member2 = (Member) JSONObject.toBean(jsonObject, Member.class);
		System.out.println(member2.toString());
		// Member [name=한종상, age=20, address=서울시, tel=010-3497-7897]
		assertEquals("한종상", member2.getName());
		assertEquals("20", member2.getAge());
		assertEquals("서울시", member2.getAddress());
		assertEquals("010-3497-7897", member2.getTel());
	}
	
	@Test
	public void jsonObjectTest(){
		JSONObject root = new JSONObject();
		JSONObject json = new JSONObject();
		json.put("param1", 10);
		json.put("param2", 20);
		
		root.put("root", json);
		System.out.println(root.toString());
		assertEquals("{\"root\":{\"param1\":10,\"param2\":20}}", root.toString());
	}

}
