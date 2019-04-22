package com.jxnydx.shop.service.back.impl;

import com.jxnydx.shop.dbc.DatabaseConnection;
import com.jxnydx.shop.factory.DAOFactory;
import com.jxnydx.shop.service.back.IAdminServiceBack;
import com.jxnydx.shop.vo.Admin;

public class AdminServiceBackImpl implements IAdminServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
 	@Override
	public boolean login(Admin vo) throws Exception {
		try {
			if(DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(vo)) {
				return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastdate(vo.getAid());
			}
			return false;
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
