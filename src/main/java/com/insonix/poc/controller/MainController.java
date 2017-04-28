/**
 * 
 */
package com.insonix.poc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insonix.poc.clients.AggregationClient;

/**
 * @author Nikhil Mahajan
 *
 */
@RestController
public class MainController {

	@Autowired
	AggregationClient aggregationClient;

	@RequestMapping("iplist")
	public String getIPRange(HttpServletRequest request) {
		Map<String, String> moStr = new HashMap<>();
		moStr.put("request.getRemoteAddr()", request.getRemoteAddr());
		moStr.put("request.getRemoteHost()", request.getRemoteHost());
		moStr.put("request.getRemoteUser()", request.getRemoteUser());

//		String ipRange = "111.160.12.25"; // for localhost testing
		String ipRange = request.getRemoteAddr();
		String result = "<div>IPv4 Range Aggregartion for IP Address: <strong> " + ipRange
				+ "</strong> is  <br/><br/> </div><pre><code>" + aggregationClient.getIPRange(ipRange) + "</code></pre>";
		return result;
	}

}
