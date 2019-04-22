package com.jxnydx.shop.service.back.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jxnydx.shop.dbc.DatabaseConnection;
import com.jxnydx.shop.factory.DAOFactory;
import com.jxnydx.shop.service.back.IGoodsServiceBack;
import com.jxnydx.shop.vo.Goods;

public class GoodsServiceBackImpl implements IGoodsServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> insertPre() throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
			return map;
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean insert(Goods vo) throws Exception {
		try {
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("allGoods", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("goodsCount", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
			return map;
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listStatus(int status,int currentPage, int lineSize, String column, String keyWord) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("allGoods", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByStatus(status,currentPage, lineSize, column, keyWord));
			map.put("goodsCount", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status,column, keyWord));
			return map;
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean updateUp(java.util.Set<Integer> gid) throws Exception {
		try {
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(gid, 1);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean updateDown(java.util.Set<Integer> gid) throws Exception {
		try {
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(gid, 0);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean updateDelete(java.util.Set<Integer> gid) throws Exception {
		try {
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateStatus(gid, 2);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> updatePre(int gid) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
			map.put("goods", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findById(gid));
			return map;
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Goods vo) throws Exception {
		try {
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> deleteAll(Set<Integer> ids) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("allPhotos", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByPhoto(ids));
			map.put("flag", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids));
			return map;
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}
}
