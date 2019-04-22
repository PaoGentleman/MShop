package com.jxnydx.shop.service.front;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jxnydx.shop.exception.EmptyShopcarException;
import com.jxnydx.shop.exception.UnCompleteMemberInformationException;
import com.jxnydx.shop.exception.UnEnoughAmountException;
import com.jxnydx.shop.vo.Orders;

public interface IOrdersServiceFront {
	/**
	 * 
	 * @param mid 创建订单的用户的ID
	 * @return 订单创建成功返回true，否则返回false
	 * @throws UnCompleteMemberInformationException 个人信息不完整抛出的异常
	 * @throws UnEnoughAmountException 没有足够的库存量时抛出的异常
	 * @throws EmptyShopcarException 购物车没有添加任何商品时抛出的异常
	 * @throws SQLException JDBC执行错误的异常
	 */
	public boolean insert(String mid) throws UnCompleteMemberInformationException,UnEnoughAmountException,EmptyShopcarException,SQLException;
	/**
	 * 查看一个用户的所有订单信息
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> listByMember(String mid,int currentPage,int lineSize) throws Exception;
	/**
	 * 查看一个订单信息，以及对应的订单详情信息
	 * @param mid
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public Orders show(String mid,int oid) throws Exception;
}
