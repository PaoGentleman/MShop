package com.jxnydx.shop.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import com.jxnydx.shop.vo.Shopcar;

public interface IShopcarDAO extends IDAO<Integer, Shopcar> {
	/**
	 * 数据重复增加时，应该将已有的数据进行自增
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateAmount(String mid,Integer gid) throws SQLException; 
	/**
	 * 根据用户编号以及商品信息查询出购物车中的信息
	 * @param mid 用户ID
	 * @param gid 商品编号
	 * @return
	 * @throws Exception
	 */
	public Shopcar findByMemberAndGoods(String mid,Integer gid) throws Exception;
	/**
	 * 清除一个用户的全部购物车信息
	 * @param mid 用户编号
	 * @return 清除成功返回true，否则返回false
	 * @throws Exception 
	 */
	public boolean doRemoveByMember(String mid) throws SQLException;
	/**
	 * 批量保存购物车数据
	 * @param vos
	 * @return
	 * @throws Exception
	 */
	public boolean doCreateBatch(Set<Shopcar> vos) throws SQLException;
	/**
	 * 删除一个用户一种商品购物车的记录
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveByMemberAndGoods(String mid,Set<Integer> gid) throws SQLException;
	/**
	 * 一个用户购买的商品信息
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Map<Integer,Integer> findAllByMember(String mid) throws SQLException;
}
