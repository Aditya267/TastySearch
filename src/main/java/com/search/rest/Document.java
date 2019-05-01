package com.search.rest;

public class Document implements Comparable<Document>{
	int id;
	String content;
	Double score;
	
	static int count = 0;
	public Document() {
		// TODO Auto-generated constructor stub
		this.id = ++count;
	}
	public Document(String content, double score) {
		this.content =  content;
		this.score = score;
	}
	@Override
	public int compareTo(Document o) {
		// TODO Auto-generated method stub
		return this.score.compareTo(o.score);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.id == ((Document)obj).id;
	}
}
