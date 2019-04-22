package com.jxnydx.shop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.jxnydx.shop.vo.Member;

public interface IMemberDAO extends IDAO<String,Member> {
	public Member findById2(String mid) throws SQLException;
	/**
	 * 判断给定的mid与给定的code是否相同
	 * @param mid 用户ID
	 * @param code 用户激活码
	 * @return 用户ID与激活码相匹配，则返回true，否则返回false
	 * @throws Exception
	 */
	public boolean findByCode(String mid,String code) throws Exception;
	/**
	 * 更新指定用户的状态操作
	 * @param mid 用户ID
	 * @param status 用户状态(0表示锁定、1表示激活、2表示代激活)
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateStatus(String mid,Integer status) throws Exception;
	/**
	 * 用户登录检查操作，正常登入后可以查询出用户的照片信息，由于参数接收的是member对象，所以可以将照片信息直接保存在对象中
	 * @param vo 包含了mid与password的对象
	 * @return 登录成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean findLogin(Member vo) throws Exception;
	/**
	 * 根据用户的状态来进行列表操作
	 * @param status
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public List<Member> findAllByStatus(Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception;
	
	public Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception;
	/**
	 * 进行用户的批量更新，状态由外部设置
	 * @param ids
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception;
	
	public boolean doUpdateMember(Member vo) throws Exception;
}
