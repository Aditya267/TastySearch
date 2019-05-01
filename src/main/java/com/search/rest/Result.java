package com.search.rest;

public class Result implements Comparable<Result> {
	Document document;
	int noOfWordMatched = 0;
	
	public Result(Document document, int noOfWordMatched) {
		this.document = document;
		this.noOfWordMatched = noOfWordMatched;
	}
	void updateNoOfWordMatched(){
		noOfWordMatched++;
	}
	@Override
	public int compareTo(Result o) {
		
		if (this.noOfWordMatched < o.noOfWordMatched) {
			return -1;
		}
		if (this.noOfWordMatched > o.noOfWordMatched) {
			return 1;
		} else {
			return this.document.compareTo(o.document);
		}
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.document.equals(((Result)obj).document);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	
}