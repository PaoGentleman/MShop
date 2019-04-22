package com.jxnydx.shop.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxnydx.util.CookieUtil;

public class ShopcarCookieUtil {
	public static void addCar(HttpServletRequest request,HttpServletResponse response,int gid,int count) {
		CookieUtil.save(response, request, "sc-" + String.valueOf(gid), String.valueOf(count),7776000);
	}
	public static Map<Integer,Integer> loadCar(HttpServletRequest request) {
		Map<Integer,Integer> result = new HashMap<Integer,Integer>();
		Map<String,String> map = CookieUtil.load(request);
		Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<String, String> me = iter.next();
			if(me.getKey().startsWith("sc-")) {
				result.put(Integer.parseInt(me.getKey().split("-")[1]), Integer.parseInt(me.getValue()));
			}
		}
		return result;
	}
	
	public static void removeCar(Set<Integer> ids,HttpServletRequest request,HttpServletResponse response) {
		Iterator<Integer> iter = ids.iterator();
		while(iter.hasNext()) {
			CookieUtil.save(response,request,"sc-" + String.valueOf(iter.next()),"0",0);
		}
	}
	
	public static void clearCar(HttpServletResponse response,HttpServletRequest request) {
		CookieUtil.clear(response, request);
	}
}