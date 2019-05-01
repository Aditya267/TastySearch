package com.search.rest;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/search")
public class SearchService {

	@GET
	@Path("/query")
	public Response addUser(@QueryParam("string") String query) {
		
		try {
			List<String> queryWords = Arrays.asList(query.split(","));
	        Search search = Search.getInstance();
			int k = 20;
	        PriorityQueue<Result> topR = search.getTopK(queryWords, k);
	        
	        
	        String output = "";
	        if (topR.isEmpty()) {
	        	output = "No Match Found";
	        }
	        while (!topR.isEmpty()) {
	        	Result result = topR.poll();
	        	output = result.document.content + "\n" + output;
	        }
	        

			return Response.status(200)
					.entity(output)
					.type(MediaType.TEXT_PLAIN)
					.build();
		} catch (Exception e) {
			return Response.status(503)
					.entity(e.getMessage())
					.type(MediaType.TEXT_PLAIN)
					.build();
		}
		

	}

}