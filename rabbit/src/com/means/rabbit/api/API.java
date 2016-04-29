package com.means.rabbit.api;

import com.means.rabbit.RabbitApplication;

public class API {

	public static String text = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/hotel/contentlist";

	public static String hotelList = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/contentlist";

	/** 用户注册 */
	public static String register = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/registaction";

	/** 获取验证码 */
	public static String mobilecode = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/mobilecode";

	/** 用户登录 */
	public static String login = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/user/login";

	/** 退出登录 */
	public static String logout = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/user/logout";

	/** 修改密码 */
	public static String resetpswdbyphone = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/resetpswdbyphone";

	/** 商家/酒店列表猜你喜欢列表 */
	public static String guesslikelist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/contentlist";

	/** 修改昵称 */
	public static String editaction = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/editaction";

	/** 旅行小蜜/紧急求助资讯列表分页 */
	public static String contentlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/cms/contentlist";

	/** 旅行小蜜/紧急求助资详情 */
	public static String contentview = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/cms/contentview";

	/** 城市列表 */
	public static String citylist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/citylist";

	/** 代购列表分页 */
	public static String dgcontentlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/dgcontentlist";

	/** 商家区域列表 */
	public static String arealist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/arealist";

	/** 商家分类列表 */
	public static String catlist = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/index/catlist";

	/** 代购品牌分类 */
	public static String brandlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/labellist";

	/** 酒店详情 */
	public static String hoteldetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/contentview";

	/** 酒店附近团购 */
	public static String hotelDetailNearTuangou = RabbitApplication
			.getInstance().getBaseUrl() + "/home/shop/itemlist";

	/** 酒店预订列表 */
	public static String hotelDetailOrderList = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/itemlist";

	/** 美食列表 */
	public static String foodList = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/contentlist";

	/** 消息列表分页 */
	public static String msglist = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/index/msglist";

	/** 消息删除 */
	public static String msgdelete = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/msgdelete";

	/** 关于我们 */
	public static String aboutdetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/aboutdetail";

	/** 修改手机 */
	public static String changephone = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/changephone";

	/** 修改头像 */
	public static String editfceimg = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/editfceimg";

	/** 商家详情 */
	public static String shopDetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/contentview";

	/** 团购/代购/酒店商家的发布评论 */
	public static String addcomment = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/addcomment";

	/** 评论列表 */
	public static String commentlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/commentlist";

	/**
	 * 
	 * 
	 */
	public static String tuangouDetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/itemview";

	/** 财务分页明细 */
	public static String accountlog = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/accountlog";

	/** 申请提现 */
	public static String cashapply = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/cashapply";

	/** 我的财务 */
	public static String accountview = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/accountview";

	/** 我的订单列表分页 */
	public static String orderlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/orderlist";

	/** 酒店下单 */
	public static String addHotelOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/orderadd";

	/** 酒店订单预览 */
	public static String preHotelOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/orderpreview";
	public static String addOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/orderadd";

	/** 酒店订单预览 */

	/** 酒店订单详情 */
	public static String hotelOrderDetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/orderview";

	/** 团购订单预览 */
	public static String preTuangouOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/orderpreview";

	/** 团购订单下单 */
	public static String addTuangouOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/orderadd";

	/** 团购订单详情 */
	public static String tuangouOrderDetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/orderview";

	/** 首页猜你喜欢分页列表 */
	public static String likelist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/likelist";

	/** 首页幻灯+中间内容列表 */
	public static String configlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/configlist";

	/** 收获地址列表分页 */
	public static String addressuserlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/cart/addressuserlist";

	/** 添加收获地址 */
	public static String addaddress = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/cart/addaddress";

	/** 取消订单 */
	public static String cancelOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/ordercancel";

	/** 代购订单预览 */
	public static String preDaigouOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/dgorderpreview";

	/** 代购订单下单 */
	public static String addDaigouOrder = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/dgorderadd";

	/** 代购订单预览 */
	public static String daigouOrderDetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/dgorderview";

	/** 代购详情 */
	public static String daigouDetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/dgview";

	/** 支付 */
	public static String pay = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/shop/orderpay";

	/** 获取个人信息 */
	public static String userinfo = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/user/info";

	/** 优惠买单 */
	public static String youhuibuy = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/shoppay";

	/** 我的积分 */
	public static String paycredit = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/paycredit";

	/** 翻译 */
	public static String translation = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/fanyi";

	/** 首页搜索 */
	public static String search = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/index/search";

	/** 首页搜索 */
	public static String uploadImg = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/attachment/uploadimage";

	/** 商家订单列表 */
	public static String orderbusinesslist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/hotel/orderbusinesslist";

	/** 商家订单列详情 */
	public static String orderbusinessdetail = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/orderbusinessview";

	/** 使用消费码 */
	public static String usecode = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/shop/ercodepay";

	/** 积分记录 */
	public static String creditloglist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/creditloglist";

	/** 全部评论 */
	public static String commentuserlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/shop/commentuserlist";

	/** 获取城市 */
	public static String getcity = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/index/getcityid";

	/** 列表广告位 */
	public static String listad = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/index/adtoplist";

	/** 收藏 */
	public static String collect = RabbitApplication.getInstance().getBaseUrl()
			+ "/home/index/collect";

	/** 取消收藏 */
	public static String collectdel = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/collectdel";

	/** 收藏列表 */
	public static String collectlist = RabbitApplication.getInstance()
			.getBaseUrl() + "/home/index/collectlist";

}
