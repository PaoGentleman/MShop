package com.jxnydx.shop.service.front;

import java.util.Map;

import com.jxnydx.shop.vo.Goods;

public interface IGoodsServiceFront {
	/**
	 * 进行全部商品列表时使用，本操作要执行如下调用<br>
	 * 		<li>要调用IItemDAO.findAll()方法查询出全部的商品分类</li>
	 * 		<li>要调用IGoodsDAO.findAllByStatus()方法查询出全部数据</li>
	 * 		<li>要调用IItemDAO.findAllByStatus()方法查询出全部数据量</li>
	 * 		<li>调用的时候status=1，即为上架的商品信息</li>
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return	返回的数据一共包含三类内容:<br>
	 * 		<li>key = allItems,value = IItemDAO.findAll(),保存的时List<Item></li>
	 * 		<li>key = allGoods,value = IGoodsDAO.findAllByStatus,保存的时List<Goods></li>
	 * 		<li>key = goodsCount,value = IGoodsDAO.getAllCountByStatus(),保存的时Integer</li>
	 * @throws Exception
	 */
	public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception;
	/**
	 * 进行全部商品列表时使用，本操作要执行如下调用<br>
	 * 		<li>要调用IItemDAO.findAll()方法查询出全部的商品分类</li>
	 * 		<li>要调用IGoodsDAO.findAllByItem()方法查询出全部数据</li>
	 * 		<li>要调用IItemDAO.findAllByItem()方法查询出全部数据量</li>
	 * 		<li>调用的时候status=1，即为上架的商品信息</li>
	 * @param iid 商品类型编号
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return 返回的数据一共包含三类内容:<br>
	 * 		<li>key = allItems,value = IItemDAO.findAll(),保存的时List<Item></li>
	 * 		<li>key = allGoods,value = IGoodsDAO.findAllByItem,保存的时List<Goods></li>
	 * 		<li>key = goodsCount,value = IGoodsDAO.getAllCountByItem(),保存的时Integer</li>
	 * @throws Exception
	 */
	public Map<String,Object> listByItem(int iid,int currentPage,int lineSize,String column,String keyWord) throws Exception;
	/**
	 * 本操作要显示商品的完整信息，包含如下操作:<br>
	 * 		<li>调用IGoodsDAO.findById()方法，可以根据商品编号查询商品完整信息</li>
	 * 		<li>调用IGoodsDAO.doUpdateBow()方法，更新访问次数</li>
	 * 		<li>调用IItemDAO.findById()方法，查询商品分类名称</li>
	 * @param iid
	 * @return 商品分类设置在对象中返回，如果有数据返回实例化对象，否则返回null
	 * @throws Exception
	 */
	public Goods show(int gid) throws Exception;
}
