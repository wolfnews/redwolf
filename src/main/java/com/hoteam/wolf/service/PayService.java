package com.hoteam.wolf.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayService {

	/**生成支付请求地址
	 * @param payway
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	public String genPayUrl(String payway,Long order,HttpServletRequest request, HttpServletResponse response);

	/**处理返回请求
	 * @param payway
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean processReturn(String payway,HttpServletRequest request, HttpServletResponse response);
	/**请求通知请求，第三方支付主动发起
	 * @param payway
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public boolean processNotify(String payway,HttpServletRequest request, HttpServletResponse response) throws Exception;

}
