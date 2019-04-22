package com.jxnydx.shop.service.back;

import java.util.Map;
import java.util.Set;

import com.jxnydx.shop.vo.Goods;

public interface IGoodsServiceBack {
	/**
	 * 商品增加前的数据查询操作，要查询出所有的栏目信息
	 * <li>调用IItemDAO.findAll()方法返回出全部的分类<li>
	 * @return 数据以Map集合的形式返回，返回如下结果:
	 * 		<li>key = allItems,value = IItemDAO.findAll()方法<li>
	 * @throws Exception
	 */
	public Map<String,Object> insertPre() throws Exception;
	/**
	 * 商品增加操作，调用的是IGoodsDAO.doCreate()方法
	 * @param vo 包含有要增加商品的信息
	 * @return 增加成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean insert(Goods vo) throws Exception;
	/**
	 * 进行商品的分页数据显示，要调用IGoodsDAO的如下操作:<br>
	 * <li>调用IGoodsDAO.findAll()方法，查询出全都的数据</li>
	 * <li>调用IGoodsDAO.getAllCount()方法，统计全部数据量</li>
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前页数
	 * @param lineSize 每页显示的数据量
	 * @return 由于要返回的数据有List与Integer两中类型，所以使用Map返回<br>
	 * 		<li>key = allGoods,value = findAllSplit()</li>
	 * 		<li>key = goodsCount,value = getAllCount()</li>
	 * @throws Exception
	 */
	public Map<String,Object> list(int currentPage, int lineSize,String column, String keyWord) throws Exception;
	
	public Map<String,Object> listStatus(int status,int currentPage, int lineSize,String column, String keyWord) throws Exception;
	
	public boolean updateUp(Set<Integer> gid) throws Exception;
	
	public boolean updateDown(Set<Integer> gid) throws Exception;
	
	public boolean updateDelete(Set<Integer> gid) throws Exception;
	/**
	 * 商品修改前的数据查询操作，要查询出所有的栏目信息
	 * <li>调用IGoodsDAO.findById()方法返回出全部的分类<li>
	 * @return 数据以Map集合的形式返回，返回如下结果:
	 * 		<li>key = allItems,value = IItemDAO.findAll()方法<li>
	 * 		<li>key = goods,value = IGoodsDAO.findById()方法<li>
	 * @throws Exception
	 */
	public Map<String,Object> updatePre(int gid) throws Exception;
	
	public boolean update(Goods vo) throws Exception;
	/**
	 * 执行数据的批量删除操作，但是在删除之后要清除对应的商品图片信息
	 * @param ids 
	 * @return 返回的数据包含两个内容:<br>
	 * 		<li>key = flag, value = IGoodsDAO.doRemoveBatch()</li>
	 * 		<li>key = allPhoto, value = IGoodsDAO.findAllByPhoto()</li>
	 * @throws Exception
	 */
	public Map<String,Object> deleteAll(Set<Integer> ids) throws Exception;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Set<Integer> ids) throws Exception;
	
}
