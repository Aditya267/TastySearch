package com.search.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class Search {
	
	// This hashMap conatins every single word in dataset as key and value as the set of documents in which that word occurs.
	private  HashMap<String, Set<Integer>> keyWordsToDoc = new HashMap<String, Set<Integer>>();
	// This is our in memory dataset. Key : docID and Value : Document object.
	private HashMap<Integer, Document> db = new HashMap<Integer, Document>();
	
	private final static int sampleSize = 100000;

	private static Search search = null;
	
	public static Search getInstance() throws Exception {
		if (search == null) {
			search = new Search();
		}
		return search;
	}
	// The constructor creates a in-memory dataset hashMap for 100k samples from the dataset file given.
   // This all creates the keyWordsToDoc hashMap.
	private Search() throws Exception {

		File f = this.getFileFromResources("finefoods.txt");

        BufferedReader b = new BufferedReader(new FileReader(f));
        String readLine = "";
        int sample = sampleSize;
        String content = "";
        Document document = new Document();
        while (true) {
        	readLine = b.readLine();
            	
        	if (readLine.isEmpty()) { // new review
        		document.content = content;
        		db.put(Document.count, document);
        		document = new Document();
        		if (Document.count > sample) {
        			break;
        		}
        		content = "";
        		continue;
        	}
        	content = content + readLine + "\n";
            	
        	if (readLine.contains("review/score:")) { //
        		document.score = Double.parseDouble(readLine.substring(14));
        		
        	}
        	if (readLine.contains("review/summary:")) { //
        		createWordIndex(readLine.substring(15), Document.count);
            		
        	}
        	if (readLine.contains("review/text:")) { //
        		createWordIndex(readLine.substring(12), Document.count);
            		
        	}
                
        }
            b.close();
            
        
	
	}
	
	
	
	
	// get file from classpath, resources folder
    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
        	return new File( java.net.URLDecoder.decode(resource.getFile()));
        }

    }
	
	/**
	 * This functions creates the mapping of all the words present in dataset to the documents which have these words.
	 * @param str: a single word
	 * @param docId: id of document in which the above word is.
	 */
	private  void createWordIndex(String str, int docId) {
		
		String temp = str.toLowerCase().trim();
		char charArray[] = temp.toCharArray();
		
		String word = "";
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (c >= 'a' && c <= 'z') {
				word = word + c;
			} else {
				word.trim();
				if (!word.isEmpty()) {
					if (!keyWordsToDoc.containsKey(word)) {
						keyWordsToDoc.put(word, new HashSet<Integer>());
					}
					keyWordsToDoc.get(word).add(docId);
				}
				word = "";
			}
		}
		word.trim();
		if (!word.isEmpty()) {
			if (!keyWordsToDoc.containsKey(word)) {
				keyWordsToDoc.put(word, new HashSet<Integer>());
			}
			keyWordsToDoc.get(word).add(docId);
		}
	}
	
	/** 
	 * This function searches the dataset and returns K documents of best match
	 * @param query : List of words
	 * @param k: No of documents. to be return.
	 * @return: Top K documents.
	 */
	public  PriorityQueue<Result> getTopK (List<String> query, int k) {
		HashMap<Integer, Result> matchDocuments = new HashMap<Integer, Result>();
		PriorityQueue<Result> topKDocs = new PriorityQueue<Result>(k);
		for (int i = 0; i < query.size(); i++) {
			Set<Integer> matches = keyWordsToDoc.get(query.get(i).toLowerCase().trim());
			if(matches == null)
				continue;
			
			Iterator<Integer> matchesIterator = matches.iterator();
			while (matchesIterator.hasNext()) {
				int docId = matchesIterator.next();
				Result result = null;
				if (matchDocuments.containsKey(docId)) {
					result = matchDocuments.get(docId);
					topKDocs.remove(result);
					result.updateNoOfWordMatched();
				} else {
					result = new Result(db.get(docId),1);
					matchDocuments.put(docId, result);
				}
				if (!topKDocs.contains(result)) {
					if (topKDocs.size() < k) {
						topKDocs.add(result);
					} else {
						if (topKDocs.peek().compareTo(result) < 0) {
							topKDocs.poll();
							topKDocs.add(result);
						}
					}
				}
				
			}	
		}
		return topKDocs;
	}
	
	
}



