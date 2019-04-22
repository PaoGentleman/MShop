package com.jxnydx.shop.service.front;

import com.jxnydx.shop.vo.Member;

public interface IMemberServiceFront {
    /**
     * 实现用户注册操作，注册时要提供有mid,password,regdate,code,staus;<br>
     *     本程序调用如下操作:<br>
     *     <li>调用IMemberDAO.findById()方法判断注册的mid是否存在</li>
     *     <li>调用IMemberDAO.doCreate()方法保存基本信息</li>
     * @param vo    包含有注册信息的vo类对象
     * @return  注册成返回true，否则返回false
     * @throws Exception
     */
    public boolean regist(Member vo) throws Exception;
    /**
     * 实现注册用户激活操作，只有激活后的用户才可以进行登入，本操作调用如下功能:<br>
     * <li>调用IMemberDAO.findByCode()方法判断激活码是否正确！</li>
     * <li>调用IMemberDAO.doUpdateStatus()方法修改用户状态</li>
     * @param 包含有mid与code的数据的对象
     * @return 激活成功返回true，否则返回false
     * @throws Exception
     */
    public boolean active(Member vo) throws Exception;
    /**
     * 用户登录操作，调用的是IMemberDAO.findByLogin()方法，只能登录激活的用户
     * @param vo 包含有mid与password的member对象
     * @return 登录成功返回true，否则返回false
     * @throws Exception
     */
    public boolean login(Member vo) throws Exception;
    
    public Member updatePre(String mid) throws Exception;    
    public boolean update(Member v0) throws Exception;
}
