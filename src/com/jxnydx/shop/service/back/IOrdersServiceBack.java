package com.jxnydx.shop.service.back;

import java.util.Map;

import com.jxnydx.shop.vo.Orders;

public interface IOrdersServiceBack {
	public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception;
	public Orders show(int oid) throws Exception;
}
