package test.json;

public class Member {
	private String name;
	private String age;
	private String address;
	private String tel;
	public Member(){}
	public Member(	String name,
					String age,
					String address,
					String tel) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", address=" + address + ", tel=" + tel + "]";
	}
	
	// getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
