package com.jxnydx.shop.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.jxnydx.shop.dao.IDetailsDAO;
import com.jxnydx.shop.vo.Details;
import com.jxnydx.shop.vo.Goods;
import com.jxnydx.shop.vo.Orders;
import com.jxnydx.util.dao.AbstractDAOImpl;

public class DetailsDAOImpl extends AbstractDAOImpl implements IDetailsDAO {

	public DetailsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Details vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Details vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Details findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doCreateBath(List<Details> vos) throws SQLException {
		boolean flag = true;
		String sql = "INSERT INTO details(oid,gid,title,price,amount) VALUES (?,?,?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		Iterator<Details> iter = vos.iterator();
		while(iter.hasNext()) {
			Details vo = iter.next();
			super.pstmt.setInt(1, vo.getOrders().getOid());
			super.pstmt.setInt(2, vo.getGoods().getGid());
			super.pstmt.setString(3, vo.getTitle());
			super.pstmt.setDouble(4, vo.getPrice());
			super.pstmt.setInt(5, vo.getAmount());
			super.pstmt.addBatch();
			System.out.println("Oid = " + vo.getOrders().getOid() + ", Gid = " + vo.getGoods().getGid() + ", Title = " + vo.getTitle() + ", Price" + vo.getPrice() + ", Amount = " + vo.getAmount());
		}
		int[] result =  super.pstmt.executeBatch();
		for(int x = 0; x < result.length; x ++) {
			if(result[x] == 0) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean doCreateOrders(Orders vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Details> findAllByOrders(Integer oid) throws Exception {
		List<Details> all = new ArrayList<Details>();
		String sql = "SELECT odid,oid,gid,title,price,amount FROM details WHERE oid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, oid);
		ResultSet rs = super.pstmt.executeQuery();
		while(rs.next()) {
			Details vo = new Details();
			vo.setOdid(rs.getInt(1));
			Orders orders = new Orders();
			orders.setOid(rs.getInt(2));
			vo.setOrders(orders);
			Goods goods = new Goods();
			goods.setGid(rs.getInt(3));
			vo.setGoods(goods);
			vo.setTitle(rs.getString(4));
			vo.setPrice(rs.getDouble(5));
			vo.setAmount(rs.getInt(6));
			all.add(vo);
		}
		return all;
	}

}
