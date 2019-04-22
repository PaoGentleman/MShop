package com.jxnydx.shop.service.back;

import com.jxnydx.shop.vo.Admin;

public interface IAdminServiceBack {
	/**
	 * 实现管理员的登录操作，本操作执行两个方法
	 * <li>调用IAdminDAO.findLogin()方法确定管理员的账号密码是否正确</li>
	 * <li>调用IAdminDAO.doUpdateLastdate()方法，更新管理员的最新登录时间</li>
	 * @param vo 只包含aid与password数据
	 * @return 登录成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean login(Admin vo) throws Exception;
}
