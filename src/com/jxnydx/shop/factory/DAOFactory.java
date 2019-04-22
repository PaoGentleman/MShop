package com.jxnydx.shop.factory;

import com.jxnydx.shop.dao.IAdminDAO;
import com.jxnydx.shop.dao.IDetailsDAO;
import com.jxnydx.shop.dao.IGoodsDAO;
import com.jxnydx.shop.dao.IItemDAO;
import com.jxnydx.shop.dao.IMemberDAO;
import com.jxnydx.shop.dao.IOrdersDAO;
import com.jxnydx.shop.dao.IShopcarDAO;
import com.jxnydx.shop.dao.impl.AdminDAOImpl;
import com.jxnydx.shop.dao.impl.DetailsDAOImpl;
import com.jxnydx.shop.dao.impl.GoodsDAOImpl;
import com.jxnydx.shop.dao.impl.ItemDAOImpl;
import com.jxnydx.shop.dao.impl.MemberDAOImpl;
import com.jxnydx.shop.dao.impl.OrdersDAOImpl;
import com.jxnydx.shop.dao.impl.ShopcarDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    public static IMemberDAO getIMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }
    
    public static IAdminDAO getIAdminDAOInstance(Connection conn) {
        return new AdminDAOImpl(conn);
    }
    
    public static IItemDAO getIItemDAOInstance(Connection conn) {
        return new ItemDAOImpl(conn);
    }
    
    public static IGoodsDAO getIGoodsDAOInstance(Connection conn) {
        return new GoodsDAOImpl(conn);
    }
    
    public static IShopcarDAO getIShopcarDAOInstance(Connection conn) {
        return new ShopcarDAOImpl(conn);
    }
    
    public static IOrdersDAO getIOrdersDAOInstance(Connection conn) {
        return new OrdersDAOImpl(conn);
    }
    
    public static IDetailsDAO getIDetailsDAOInstance(Connection conn) {
        return new DetailsDAOImpl(conn);
    }
}
