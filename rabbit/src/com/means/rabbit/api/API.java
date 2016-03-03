package com.means.rabbit.api;

public class API {

	public static String Baseurl = "http://cn.lazybunny.c.wanruankeji.com";
	public static String text = Baseurl + "/home/hotel/contentlist";

	public static String hotelList = Baseurl + "/home/hotel/contentlist";

	/** 用户注册 */
	public static String register = Baseurl + "/home/user/registaction";

	/** 获取验证码 */
	public static String mobilecode = Baseurl + "/home/user/mobilecode";

	/** 用户登录 */
	public static String login = Baseurl + "/home/user/login";
	
	/** 修改密码 */
	public static String resetpswdbyphone = Baseurl + "/home/user/resetpswdbyphone";
	
	/** 旅行小蜜/紧急求助资讯列表分页 */
	public static String contentlist = Baseurl + "/home/cms/contentlist";
	
	/** 旅行小蜜/紧急求助资详情 */
	public static String contentview = Baseurl + "/home/cms/contentview";
	
	/** 城市列表 */
	public static String citylist = Baseurl + "/home/index/citylist";

}
