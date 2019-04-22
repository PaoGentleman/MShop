package com.jxnydx.shop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.jxnydx.shop.vo.Goods;

public interface IGoodsDAO extends IDAO<Integer, Goods> {
	public List<Goods> findAllByStatus(Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception;
	public Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception;
	public boolean doUpdateStatus(Set<Integer> ids, Integer status) throws Exception;
	public Set<String> findAllByPhoto(Set<Integer> ids) throws Exception;
	/**
	 * 根据商品的分类与状态，进行商品列表显示
	 * @param iid 商品类型
	 * @param status 商品状态
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public List<Goods> findAllByItem(Integer iid,Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception;
	public Integer getAllCountByItem(Integer iid,Integer status,String column,String keyWord) throws Exception;
	/**
	 * 每次访问，访问量+1
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateBow(Integer id) throws Exception;
	/**
	 * 查询指定编号的商品信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<Goods> findAllByGid(Set<Integer> ids) throws SQLException;
	/**
	 * 要进行商品库存量的更新
	 * @param gid
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateByAmount(Integer gid, Integer num) throws SQLException; 
}
