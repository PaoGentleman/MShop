package com.jxnydx.shop.service.front.impl;

import com.jxnydx.shop.dbc.DatabaseConnection;
import com.jxnydx.shop.factory.DAOFactory;
import com.jxnydx.shop.service.front.IMemberServiceFront;
import com.jxnydx.shop.vo.Member;

public class MemberServiceFrontImpl implements IMemberServiceFront{
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean regist(Member vo) throws Exception {
        try {
            if(DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(vo.getMid()) == null) {
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
	@Override
	public boolean active(Member vo) throws Exception {
        try {
        	if(DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findByCode(vo.getMid(), vo.getCode())) {
        		return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(vo.getMid(), 1);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
	}
	@Override
	public boolean login(Member vo) throws Exception {
        try {
        	return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findLogin(vo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
	}
	@Override
	public Member updatePre(String mid) throws Exception {
        try {
        	return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(mid);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
	}
	@Override
	public boolean update(Member vo) throws Exception {
        try {
        	return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateMember(vo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
	}
}
