/**
 * 
 */
package com.insonix.poc.clients;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author Nikhil Mahajan
 *
 */
@Service
public class AggregationClient {

	public String getIPRange(String remoteIP){
		
		
		JsonObject to = new JsonObject();
		to.addProperty("to", remoteIP);
		
		JsonObject from = new JsonObject();
		from.addProperty("from", remoteIP);
		
		JsonArray arr = new JsonArray();
		arr.add(to);arr.add(from);
		
//		JsonObject ranges = new JsonObject();
//		ranges.add("ranges", arr);
		
//		JsonObject field = new JsonObject();
//		field.addProperty("field", "ip");
//		
		
		JsonObject iprange = new JsonObject();
		iprange.addProperty("field", "ip");
		iprange.add("ranges", arr);

		JsonObject ipranges = new JsonObject();
		ipranges.add("ip_range", iprange);
		
		JsonObject aggs = new JsonObject();
		aggs.add("ip_ranges", ipranges);
		
		JsonObject root = new JsonObject();
		root.add("aggs",aggs);


		System.out.println("JSON to==> " + root.toString());
		
//		JsonArray arr2 = new JsonArray();
//		arr2.add(iprange);
		final String uri = "http://122.160.12.25:9200/poc/_search?pretty";
	     
	    RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>(root.toString(), headers);
	    ResponseEntity<String> result = null;
	     try{
	    
	    	 result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	     }catch(Exception e){
	    	 
	     }
	    
	     
	    System.out.println(result);

	    return result.getBody().toString();
	}
	
}
