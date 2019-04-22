package com.jxnydx.shop.factory;

import com.jxnydx.shop.service.back.IAdminServiceBack;
import com.jxnydx.shop.service.back.IGoodsServiceBack;
import com.jxnydx.shop.service.back.IItemServiceBack;
import com.jxnydx.shop.service.back.IMemberServiceBack;
import com.jxnydx.shop.service.back.IOrdersServiceBack;
import com.jxnydx.shop.service.back.impl.AdminServiceBackImpl;
import com.jxnydx.shop.service.back.impl.GoodsServiceBackImpl;
import com.jxnydx.shop.service.back.impl.ItemServiceBackImpl;
import com.jxnydx.shop.service.back.impl.MemberServiceBackImpl;
import com.jxnydx.shop.service.back.impl.OrdersServiceBackImpl;
import com.jxnydx.shop.service.front.IMemberServiceFront;
import com.jxnydx.shop.service.front.impl.MemberServiceFrontImpl;

public class ServiceBackFactory { 
	public static IAdminServiceBack getIAdminServiceBackInstance() throws Exception {
        return new AdminServiceBackImpl();
    }
	
	public static IMemberServiceBack getIMemberServiceBackInstance() throws Exception {
		  return new MemberServiceBackImpl();
	}
	
	public static IItemServiceBack getIItemServiceBackInstance() throws Exception {
		  return new ItemServiceBackImpl();
	}
	
	public static IGoodsServiceBack getIGoodsServiceBackInstance() throws Exception {
		  return new GoodsServiceBackImpl();
	}
	
	public static IOrdersServiceBack getIOrdersServiceBackInstance() throws Exception {
		  return new OrdersServiceBackImpl();
	}
}
