package com.jxnydx.shop.dao;

import java.sql.SQLException;
import java.util.List;

import com.jxnydx.shop.vo.Details;
import com.jxnydx.shop.vo.Orders;

public interface IDetailsDAO extends IDAO<Integer, Details> {
	/**
	 * 批量创建订单详情
	 * @param vos
	 * @return
	 * @throws Exception
	 */
	public boolean doCreateBath(List<Details> vos) throws SQLException;
	
	public boolean doCreateOrders(Orders vo) throws SQLException;
	/**
	 * 更具订单编号查询出一个订单的完整信息
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public List<Details> findAllByOrders(Integer oid) throws Exception;
}
