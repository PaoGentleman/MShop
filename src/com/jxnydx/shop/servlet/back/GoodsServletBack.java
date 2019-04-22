package com.jxnydx.shop.servlet.back;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.jxnydx.shop.factory.ServiceBackFactory;
import com.jxnydx.shop.vo.Admin;
import com.jxnydx.shop.vo.Goods;
import com.jxnydx.shop.vo.Item;
import com.jxnydx.util.validate.ValidateUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@WebServlet(name="GoodsServletBack",urlPatterns = "/pages/back/admin/goods/GoodsServletBack/*")
public class GoodsServletBack extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if(status != null) {
            if("insertPre".equals(status)) {
            	path = this.insertPre(request,response);
            } else if("insert".equals(status)) {
            	try {
					path = this.insert(request,response);
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}
            } else if("list".equals(status)) {
            	path = this.list(request);
            } else if("listStatus".equals(status)) {
            	path = this.listStatus(request);
            } else if(status.equals("updateStatus")) {
            	path = this.updateStatus(request);
            } else if(status.equals("updatePre")) {
            	path = this.updatePre(request, response);
            } else if(status.equals("update")) {
            	try {
					path = this.update(request, response);
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}
            } else if(status.equals("delete")){
            	path = this.delete(request);
            }
         }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
    
    public String insertPre(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		Map<String,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().insertPre();
			request.setAttribute("allItems", map.get("allItems"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/pages/back/admin/goods/goods_insert.jsp";
    }
    public String insert(HttpServletRequest request, HttpServletResponse response) throws IOException, SmartUploadException {
    	String msg = null;
        String url = null;  //执行forward.jsp的使用
    	SmartUpload smart = new SmartUpload();
    	try {
			smart.initialize(super.getServletConfig(), request, response);
			smart.upload();
		} catch (ServletException e) {
			e.printStackTrace();
		}
    	String iid = smart.getRequest().getParameter("iid");
    	String title = smart.getRequest().getParameter("title");
    	String price = smart.getRequest().getParameter("price");
    	String amount = smart.getRequest().getParameter("amount");
    	String note = smart.getRequest().getParameter("note");
    	String photo = smart.getRequest().getParameter("photo");
    	String status = smart.getRequest().getParameter("status");
    	if(ValidateUtil.validateRegex(iid, "\\d+") && 
    			ValidateUtil.validateEmpty(title) &&
    			ValidateUtil.validateRegex(price, "\\d+(\\.\\d{1,2})?") &&
    			ValidateUtil.validateRegex(amount, "\\d+") &&
    			ValidateUtil.validateEmpty(note) &&
    			ValidateUtil.validateRegex(status, "\\d")) {
    		Goods vo = new Goods();
    		vo.setTitle(title);
    		vo.setPrice(Double.valueOf(price));
    		vo.setAmount(Integer.valueOf(amount));
    		vo.setNote(note);
    		vo.setStatus(Integer.valueOf(status));
    		vo.setPubdate(new Date());
    		vo.setBow(0);
    		Item item = new Item();
    		item.setIid(Integer.valueOf(iid));
    		vo.setItem(item);
    		String aid = (String) request.getSession().getAttribute("aid");
    		Admin admin = new Admin();
    		admin.setAid(aid);
    		vo.setAdmin(admin);
    		if(smart.getFiles().getSize() > 0) {
    			if(smart.getFiles().getFile(0).getContentType().contains("image")) {
    				vo.setPhoto(UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt());
    			} else {
    				vo.setPhoto("nophoto.jpg");
    			}
    		}
    		try {
				if(ServiceBackFactory.getIGoodsServiceBackInstance().insert(vo)) {
					//此方法不能在我的tomcat上保存，在此重启发布，照片文件回消失
					String filePath = super.getServletContext().getRealPath("/upload/goods/") + vo.getPhoto();
					if(smart.getFiles().getSize() > 0) {
		    			if(smart.getFiles().getFile(0).getContentType().contains("image")) {
		    				smart.getFiles().getFile(0).saveAs(filePath);
		    			}
		    		}
					msg = "商品信息发布成功";
				} else {
					msg = "商品信息发布失败";
				}
				url = "/pages/back/admin/goods/GoodsServletBack/insertPre";
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else {
    		msg = "商品增加数据出错，无法进行商品信息发布！";
    		url = "/pages/back/admin/goods/GoodsServletBack/insertPre";
    	}
    	request.setAttribute("msg",msg);
        request.setAttribute("url",url);
    	return "/pages/forward.jsp";
    }
    public String list(HttpServletRequest request) {
    	int currentPage = 1;
    	int lineSize = 15;
    	String column = null;
    	String keyWord = null;
    	String columeData = "商品名称:title|发布管理员:aid";
    	try {
    		currentPage = Integer.parseInt(request.getParameter("cp"));
    	} catch(Exception e) {}
    	try {
    		lineSize = Integer.parseInt(request.getParameter("ls"));
    	} catch(Exception e) {}
    	keyWord = request.getParameter("kw");
    	column = request.getParameter("col");
    	if(keyWord == null) {
    		keyWord = "";
    	}
    	if(column == null) {
    		column = "title";
    	}
    	try {
    			Map<String,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().list(currentPage, lineSize, column, keyWord);
    			request.setAttribute("allGoods", map.get("allGoods"));
    	    	request.setAttribute("allRecorders", map.get("goodsCount"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("currentPage", currentPage);
    	request.setAttribute("lineSize", lineSize);
    	request.setAttribute("column", column);
    	request.setAttribute("keyWord", keyWord);
    	request.setAttribute("columeData", columeData);
    	request.setAttribute("url", "/MShop/pages/back/admin/goods/GoodsServletBack/list");
    	return "/pages/back/admin/goods/goods_list.jsp";
    }
    public String listStatus(HttpServletRequest request) {
    	int currentPage = 1;
    	int lineSize = 15;
    	String column = null;
    	String keyWord = null;
    	String columeData = "商品名称:title|发布管理员:aid";
    	try {
    		currentPage = Integer.parseInt(request.getParameter("cp"));
    	} catch(Exception e) {}
    	try {
    		lineSize = Integer.parseInt(request.getParameter("ls"));
    	} catch(Exception e) {}
    	keyWord = request.getParameter("kw");
    	column = request.getParameter("col");
    	if(keyWord == null) {
    		keyWord = "";
    	}
    	if(column == null) {
    		column = "title";
    	}
    	int status = Integer.parseInt(request.getParameter("status"));//接收状态
    	try {
    			Map<String,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().listStatus(status,currentPage, lineSize, column, keyWord);
    			request.setAttribute("allGoods", map.get("allGoods"));
    	    	request.setAttribute("allRecorders", map.get("goodsCount"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("currentPage", currentPage);
    	request.setAttribute("lineSize", lineSize);
    	request.setAttribute("column", column);
    	request.setAttribute("keyWord", keyWord);
    	request.setAttribute("columeData", columeData);
    	request.setAttribute("url", "/MShop/pages/back/admin/goods/GoodsServletBack/listStatus");
    	request.setAttribute("paramName", "status");
    	request.setAttribute("paramValue", String.valueOf(status));
    	return "/pages/back/admin/goods/goods_list.jsp";
    }

    public String updateStatus(HttpServletRequest request) {
    	String referer = request.getHeader("referer");	//取得之前的路径
    	String type = request.getParameter("type"); 	//区分类型
    	String msg = null;
    	String url = null;
    	String ids = request.getParameter("ids");
    	if(ValidateUtil.validateEmpty(ids)) {
    		String[] result = ids.split("--");
    		Set<Integer> all = new HashSet<Integer>();
    		for(int x = 0; x < result.length ; x ++) {
    			String[] temp = result[x].split(":");
    			all.add(Integer.parseInt(temp[0]));
    		}
    		try {
    			boolean flag = false;
    			if("up".equalsIgnoreCase(type)) {
	    			flag = ServiceBackFactory.getIGoodsServiceBackInstance().updateUp(all); 
    			}
    			if("down".equalsIgnoreCase(type)) {
	    			flag = ServiceBackFactory.getIGoodsServiceBackInstance().updateDown(all); 
    			} 
    			if("delete".equalsIgnoreCase(type)) {
	    			flag = ServiceBackFactory.getIGoodsServiceBackInstance().updateDelete(all); 
    			}
    			if(flag == true) {
    				msg = "用户状态更新成功！";
    			} else {
    				msg = "用户状态更新失败！";
    			}
    			url = "/pages/back/admin/goods/GoodsServletBack" + referer.substring(referer.lastIndexOf("/"));
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else {
    		msg = "您还未选择更新数据，请重新操作！";
    		url = "/pages/back/admin/goods/GoodsServletBack" + referer.substring(referer.lastIndexOf("/"));
    	}
    	request.setAttribute("msg", msg);
    	request.setAttribute("url", url);
    	return "/pages/forward.jsp";
    }
    public String updatePre(HttpServletRequest request, HttpServletResponse response) {
    	String gid = request.getParameter("gid");
    	String referer = request.getHeader("referer");	//取得之前的路径
    	if(ValidateUtil.validateEmpty(gid)) {
			try {
    			Map<String,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().updatePre(Integer.parseInt(gid));
				request.setAttribute("allItems", map.get("allItems"));
				request.setAttribute("goods", map.get("goods"));
				request.setAttribute("back", "/pages/back/admin/goods/GoodsServletBack" + referer.substring(referer.lastIndexOf("/")));
			} catch(Exception e) {
				e.printStackTrace();
			}
			return "/pages/back/admin/goods/goods_update.jsp";
	    } else {
	    	request.setAttribute("msg", "还未选择要更新的数据,请重新确认！");
	    	request.setAttribute("url", "/pages/back/admin/goods/GoodsServletBack" + referer.substring(referer.lastIndexOf("/")));
	    	return "/pages/forward.jsp";
	    }
    }
    
    public String update(HttpServletRequest request, HttpServletResponse response) throws IOException, SmartUploadException {
    	String msg = null;
        String url = null;  //执行forward.jsp的使用
    	SmartUpload smart = new SmartUpload();
    	try {
			smart.initialize(super.getServletConfig(), request, response);
			smart.upload();
		} catch (ServletException e) {
			e.printStackTrace();
		}
    	String oldpic = smart.getRequest().getParameter("oldpic");
    	String gid = smart.getRequest().getParameter("gid");
    	String iid = smart.getRequest().getParameter("iid");
    	String title = smart.getRequest().getParameter("title");
    	String price = smart.getRequest().getParameter("price");
    	String amount = smart.getRequest().getParameter("amount");
    	String note = smart.getRequest().getParameter("note");
    	String photo = smart.getRequest().getParameter("photo");
    	String status = smart.getRequest().getParameter("status");
    	if(ValidateUtil.validateEmpty(gid) &&
    			ValidateUtil.validateRegex(iid, "\\d+") && 
    			ValidateUtil.validateEmpty(title) &&
    			ValidateUtil.validateRegex(price, "\\d+(\\.\\d{1,2})?") &&
    			ValidateUtil.validateRegex(amount, "\\d+") &&
    			ValidateUtil.validateEmpty(note) &&
    			ValidateUtil.validateRegex(status, "\\d")) {
    		Goods vo = new Goods();
    		vo.setGid(Integer.parseInt(gid));
    		vo.setTitle(title);
    		vo.setPrice(Double.valueOf(price));
    		vo.setAmount(Integer.valueOf(amount));
    		vo.setNote(note);
    		vo.setStatus(Integer.valueOf(status));
    		Item item = new Item();
    		item.setIid(Integer.valueOf(iid));
    		vo.setItem(item);
    		if(smart.getFiles().getSize() > 0) {
    			if(smart.getFiles().getFile(0).getContentType().contains("image")) {
    				if(!"nophoto.jpg".equals(oldpic)) {
    					vo.setPhoto(UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt());
    				} else {
        				vo.setPhoto(oldpic);
        			}
    			} else {
    				vo.setPhoto(oldpic);
    			}
    		} else {
    			vo.setPhoto(oldpic);
    		}
    		try {
				if(ServiceBackFactory.getIGoodsServiceBackInstance().update(vo)) {
					String filePath = super.getServletContext().getRealPath("/upload/goods/") + vo.getPhoto();
					if(smart.getFiles().getSize() > 0) {
		    			if(smart.getFiles().getFile(0).getContentType().contains("image")) {
		    				smart.getFiles().getFile(0).saveAs(filePath);
		    			}
		    		}
					msg = "商品信息修改成功";
				} else {
					msg = "商品信息修改失败";
				}
				url = smart.getRequest().getParameter("back");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else {
    		msg = "商品增加数据出错，无法进行商品信息更新！";
    		url = smart.getRequest().getParameter("back");
    	}
    	request.setAttribute("msg",msg);
        request.setAttribute("url",url);
    	return "/pages/forward.jsp";
    }
    
    public String delete(HttpServletRequest request) {
    	String referer = request.getHeader("referer");	//取得之前的路径
    	String msg = null;
    	String url = null;
    	String ids = request.getParameter("ids");
    	if(ValidateUtil.validateEmpty(ids)) {
    		Set<Integer> allIds = new HashSet<Integer>();
    		Set<String> allPhotos = new HashSet<String>();
    		String[] result = ids.split("--");
    		for(int x = 0; x < result.length; x ++) {
    			String[] temp = result[x].split(":");
    			allIds.add(Integer.parseInt(temp[0]));
    			if(!"nophoto.jpg".equals(temp[1])) {
    				allPhotos.add(temp[1]);
    			}
    		}
    		try {
				if(ServiceBackFactory.getIGoodsServiceBackInstance().delete(allIds)) {
					if(allPhotos.size() > 0) {
						Iterator iter = allPhotos.iterator();
						while(iter.hasNext()) {
							String filePath = super.getServletContext().getRealPath("/upload/goods/") + iter.next();
							File file = new File(filePath);
							if(file.exists()) {
								file.delete();
							}
						}
					}
					msg = "商品信息删除成功";
				} else {
					msg = "商品信息删除失败";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else {
    		msg = "删除的数据错误，请重新操作！";
    	}
    	url = "/pages/back/admin/goods/GoodsServletBack" + referer.substring(referer.lastIndexOf("/"));
    	request.setAttribute("msg", msg);
    	request.setAttribute("url", url);
    	return "/pages/forward.jsp";
    }
}