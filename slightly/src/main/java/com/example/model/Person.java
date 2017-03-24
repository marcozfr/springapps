package com.example.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
	
	private static Map<String,Person> repository = new HashMap<>();
	
	private String id;
	private String name;
	private boolean isMarried;
	private String spouse;
	private List<String> children;

	static{
		Person p1 = new Person();
		p1.setId("1");
		p1.setMarried(true);
		p1.setChildren(null);
		p1.setName("Maria");
		p1.setSpouse("Pere");
		
		repository.put(p1.getId(), p1);
		
		Person p2 = new Person();
		p2.setId("2");
		p2.setMarried(true);
		p2.setChildren(Arrays.asList("Clara","Ana"));
		p2.setName("Claire");
		p2.setSpouse("Bob");
		
		repository.put(p2.getId(), p2);
	}
	
	public static Person lookup(String id){
		return repository.get(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		this.children = children;
	}

}
