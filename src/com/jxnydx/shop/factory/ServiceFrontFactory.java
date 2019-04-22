package com.jxnydx.shop.factory;

import com.jxnydx.shop.service.front.IGoodsServiceFront;
import com.jxnydx.shop.service.front.IMemberServiceFront;
import com.jxnydx.shop.service.front.IOrdersServiceFront;
import com.jxnydx.shop.service.front.IShopcarServiceFront;
import com.jxnydx.shop.service.front.impl.GoodsServiceFrontImpl;
import com.jxnydx.shop.service.front.impl.MemberServiceFrontImpl;
import com.jxnydx.shop.service.front.impl.OrdersServiceFrontImpl;
import com.jxnydx.shop.service.front.impl.ShopcarServiceFrontImpl;

public class ServiceFrontFactory {
    public static IMemberServiceFront getIMemberServiceFrontInstance() throws Exception {
        return new MemberServiceFrontImpl();
    }
    
    public static IGoodsServiceFront getIGoodsServiceFrontInstance() throws Exception {
        return new GoodsServiceFrontImpl();
    }
    
    public static IShopcarServiceFront getIShopcarServiceFrontInstance() throws Exception {
        return new ShopcarServiceFrontImpl();
    }
    
    public static IOrdersServiceFront getIOrdersServiceFrontInstance() throws Exception {
        return new OrdersServiceFrontImpl();
    }
}
