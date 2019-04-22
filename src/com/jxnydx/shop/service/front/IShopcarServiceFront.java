package com.jxnydx.shop.service.front;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jxnydx.shop.vo.Goods;
import com.jxnydx.shop.vo.Shopcar;

public interface IShopcarServiceFront {
	/**
	 * 主要实现购物车数据的增加，具备如下操作逻辑:<br>
	 * 		<li>首先要查询购物车数据是否存在，存在则需要保存数据进行自增</li>
	 * 		<li>如果信息不存在，则保存一个新信息并且数量为1</li>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean addCar(Shopcar vo) throws Exception;
	/**
	 * 通过当前的用户ID，查询出所有购买的商品，包含如下内容:<br>
	 * 		<li>根据用户ID查询出购物车中的记录。</li>
	 * 		<li>购买记录包含全部商品编号，所以查询得到全部的商品完整信息</li>
	 * 		<li>由于要考虑到数据的回显问题。所以应该再让其显示出所有的购买记录</li>
	 * @param mid 用户ID
	 * @return 包含有如下内容:<br>
	 * 		<li>key = allShopcars,value = IShopcar.findAllByMember(),Map<Integer,Integer></li>
	 * 		<li>key = allGoods,value = IGoodsDAO.findAllByGid,List<Goods></li>
	 * @throws Exception
	 */
	public Map<String,Object> listCar(String mid) throws Exception;
	/**
	 * 调用IShopcarDAO.doRemoveByMemberAndGoods()方法，删除一个指定用户的指定商品信息
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCar(String mid,Set<Integer> gid) throws Exception;
	/**
	 * 更新购买的所有商品信息，更新之前需要删除掉自己已有的购买信息，而后保存新的购买信息
	 * @param mid
	 * @param vos
	 * @return
	 * @throws Exception
	 */
	public boolean update(String mid,Set<Shopcar> vos) throws Exception;
}
