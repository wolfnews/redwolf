package com.hoteam.wolf.service.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.config.PayConfig;
import com.hoteam.wolf.common.enums.OrderStatus;
import com.hoteam.wolf.dao.OrderDao;
import com.hoteam.wolf.domain.Order;
import com.hoteam.wolf.service.OrderService;
import com.hoteam.wolf.service.PayService;
import com.hoteam.wolf.tenpay.RequestHandler;
import com.hoteam.wolf.tenpay.ResponseHandler;
import com.hoteam.wolf.tenpay.client.ClientResponseHandler;
import com.hoteam.wolf.tenpay.client.TenpayHttpClient;
import com.hoteam.wolf.tenpay.util.TenpayUtil;

/**
 * 处理支付流程服务接口
 * @author dmw
 *
 */
@Service("payService")
public class PayServiceImpl implements PayService {

	private static Logger logger = Logger.getLogger(PayServiceImpl.class);
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private PayConfig payConfig;
	@Autowired
	private OrderService orderService;

	@Override
	public String genPayUrl(String payway, Long orderId, HttpServletRequest request, HttpServletResponse response) {
		Order order = orderDao.load(orderId);
		/* 商品价格（包含运费），以分为单位 */
		int fee = order.getTotal().intValue() * 100;
		// 获取提交的商品名称
		String product_name = order.getName();
		// 获取提交的备注信息
		String remarkexplain = order.getDesp();
		String desc = "商品：" + product_name + ",备注：" + remarkexplain;
		// 获取提交的订单号
		String out_trade_no = order.getSn();
		// 支付方式
		// 1表示即时到账，2表示中介担保
		String trade_mode = "1";

		String currTime = TenpayUtil.getCurrTime();
		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init();
		// 设置密钥
		reqHandler.setKey(payConfig.getKey());
		// 设置支付网关
		reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");
		// -----------------------------
		// 设置支付参数
		// -----------------------------
		reqHandler.setParameter("partner", payConfig.getPartner()); // 商户号
		reqHandler.setParameter("out_trade_no", out_trade_no); // 商家订单号
		reqHandler.setParameter("total_fee", String.valueOf(fee)); // 商品金额,以分为单位
		reqHandler.setParameter("return_url", payConfig.getReturnUrl()); // 交易完成后跳转的URL
		reqHandler.setParameter("notify_url", payConfig.getNotifyUrl()); // 接收财付通通知的URL
		reqHandler.setParameter("body", desc); // 商品描述
		reqHandler.setParameter("bank_type", "DEFAULT"); // 银行类型(中介担保时此参数无效)
		reqHandler.setParameter("spbill_create_ip", getRemoteHost(request)); // 用户的公网ip，不是商户服务器IP
		reqHandler.setParameter("fee_type", "1"); // 币种，1人民币
		reqHandler.setParameter("subject", desc); // 商品名称(中介交易时必填)

		// 系统可选参数
		reqHandler.setParameter("sign_type", "MD5"); // 签名类型,默认：MD5
		reqHandler.setParameter("service_version", "1.0"); // 版本号，默认为1.0
		reqHandler.setParameter("input_charset", "GBK"); // 字符编码
		reqHandler.setParameter("sign_key_index", "1"); // 密钥序号

		// 业务可选参数
		reqHandler.setParameter("attach", ""); // 附加数据，原样返回
		reqHandler.setParameter("product_fee", ""); // 商品费用，必须保证transport_fee +
													// product_fee=total_fee
		reqHandler.setParameter("transport_fee", "0"); // 物流费用，必须保证transport_fee
														// +
														// product_fee=total_fee
		reqHandler.setParameter("time_start", currTime); // 订单生成时间，格式为yyyymmddhhmmss
		reqHandler.setParameter("time_expire", ""); // 订单失效时间，格式为yyyymmddhhmmss
		reqHandler.setParameter("buyer_id", ""); // 买方财付通账号
		reqHandler.setParameter("goods_tag", ""); // 商品标记
		reqHandler.setParameter("trade_mode", trade_mode); // 交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
		reqHandler.setParameter("transport_desc", ""); // 物流说明
		reqHandler.setParameter("trans_type", "1"); // 交易类型，1实物交易，2虚拟交易
		reqHandler.setParameter("agentid", ""); // 平台ID
		reqHandler.setParameter("agent_type", ""); // 代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
		reqHandler.setParameter("seller_id", ""); // 卖家商户号，为空则等同于partner
		// 请求的url
		try {
			return reqHandler.getRequestURL();
		} catch (UnsupportedEncodingException e) {
			logger.error("generate request url exception：", e);
			return null;
		}
	}

	@Override
	public boolean processReturn(String payway, HttpServletRequest request, HttpServletResponse response) {
		boolean success = false;
		//---------------------------------------------------------
		//财付通支付应答（处理回调）示例，商户按照此文档进行开发即可
		//---------------------------------------------------------
		//创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(payConfig.getKey());
		logger.info("前台回调返回参数:"+resHandler.getAllParameters());
		//判断签名
		if(resHandler.isTenpaySign()) {
		    //通知id
//			String notify_id = resHandler.getParameter("notify_id");
			//商户订单号
			String out_trade_no = resHandler.getParameter("out_trade_no");
			//财付通订单号
//			String transaction_id = resHandler.getParameter("transaction_id");
			//金额,以分为单位
			String total_fee = resHandler.getParameter("total_fee");
			//如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
//			String discount = resHandler.getParameter("discount");
			//支付结果
			String trade_state = resHandler.getParameter("trade_state");
			//交易模式，1即时到账，2中介担保
			String trade_mode = resHandler.getParameter("trade_mode");
			if("1".equals(trade_mode)){       //即时到账 
				logger.info("开始处理及时到账");
				if( "0".equals(trade_state)){
			        //------------------------------
					//即时到账处理业务开始
					//------------------------------
					//注意交易单不要重复处理
					Order order = this.orderDao.load(out_trade_no);
					if(null == order){
						logger.error("订单["+out_trade_no+"]不存在");
					}else{
						if((order.getTotal().intValue())*100 == Integer.valueOf(total_fee)){
							//注意判断返回金额
							//------------------------------
							order.setState(OrderStatus.PAID.name());
							orderDao.update(order);
							//即时到账处理业务完毕
							//------------------------------
							logger.info("即时到帐付款成功");
							success = true;
						}else{
							logger.error("即时到账付款失败：金额不对账：系统："+order.getTotal().intValue()*100+"|返回："+total_fee);
							success = false;
						}
					}
				}else{
					logger.error("即时到帐付款失败");
					success = false;
				}
			}else if("2".equals(trade_mode)){    //中介担保
				logger.info("开始处理中介担保付款");
				if( "0".equals(trade_state)){
					//中介担保处理业务开始

					//注意交易单不要重复处理；注意判断返回金额
					
					//中介担保处理业务完毕
					logger.info("中介担保付款成功");
					success = true;
				}else{
					logger.error("中介担保付款失败");
					logger.error("trade_state=" + trade_state);
					success = false;
				}
			}
		} else {
			logger.error("认证签名失败");
			success = false;
		}
		return success;
	}

	@Override
	public boolean processNotify(String payway, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean success = false;
		// ---------------------------------------------------------
		// 财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
		// ---------------------------------------------------------
		// 创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(payConfig.getKey());
		logger.info("后台回调返回参数:" + resHandler.getAllParameters());
		// 判断签名
		if (resHandler.isTenpaySign()) {
			// 通知id
			String notify_id = resHandler.getParameter("notify_id");
			// 创建请求对象
			RequestHandler queryReq = new RequestHandler(null, null);
			// 通信对象
			TenpayHttpClient httpClient = new TenpayHttpClient();
			// 应答对象
			ClientResponseHandler queryRes = new ClientResponseHandler();
			// 通过通知ID查询，确保通知来至财付通
			queryReq.init();
			queryReq.setKey(payConfig.getKey());
			queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
			queryReq.setParameter("partner", payConfig.getPartner());
			queryReq.setParameter("notify_id", notify_id);
			// 通信对象
			httpClient.setTimeOut(5);
			// 设置请求内容
			httpClient.setReqContent(queryReq.getRequestURL());
			logger.info("验证ID请求字符串:" + queryReq.getRequestURL());
			// 后台调用
			if (httpClient.call()) {
				// 设置结果参数
				queryRes.setContent(httpClient.getResContent());
				logger.info("验证ID返回字符串:" + httpClient.getResContent());
				queryRes.setKey(payConfig.getKey());
				// 获取id验证返回状态码，0表示此通知id是财付通发起
				String retcode = queryRes.getParameter("retcode");
				// 商户订单号
				String out_trade_no = resHandler.getParameter("out_trade_no");
				// 财付通订单号
//				String transaction_id = resHandler.getParameter("transaction_id");
				// 金额,以分为单位
				String total_fee = resHandler.getParameter("total_fee");
				// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
//				String discount = resHandler.getParameter("discount");
				// 支付结果
				String trade_state = resHandler.getParameter("trade_state");
				// 交易模式，1即时到账，2中介担保
				String trade_mode = resHandler.getParameter("trade_mode");
				// 判断签名及结果
				if (queryRes.isTenpaySign() && "0".equals(retcode)) {
					logger.info("id验证成功");
					if ("1".equals(trade_mode)) { // 即时到账
						if ("0".equals(trade_state)) {
							// ------------------------------
							// 即时到账处理业务开始
							// ------------------------------
							// 处理数据库逻辑; 注意交易单不要重复处理; 注意判断返回金额
							Result result = processOrder(out_trade_no, total_fee);
							if(result.isSuccess()){
								logger.info("订单处理流程成功！");
							}else{
								logger.error("订单后续处理流程异常："+result.getMessage());
							}
							// 即时到账处理业务完毕
							logger.info("即时到账支付成功");
							// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
							resHandler.sendToCFT("success");
							success = true;
						} else {
							logger.error("即时到账支付处理失败");
							resHandler.sendToCFT("fail");
							success = false;
						}
					} else if ("2".equals(trade_mode)) { // 中介担保
						// ------------------------------
						// 中介担保处理业务开始
						// ------------------------------
						// 处理数据库逻辑
						// 注意交易单不要重复处理
						// 注意判断返回金额
						int iStatus = TenpayUtil.toInt(trade_state);
						switch (iStatus) {
						case 0: // 付款成功

							break;
						case 1: // 交易创建

							break;
						case 2: // 收获地址填写完毕

							break;
						case 4: // 卖家发货成功

							break;
						case 5: // 买家收货确认，交易成功

							break;
						case 6: // 交易关闭，未完成超时关闭

							break;
						case 7: // 修改交易价格成功

							break;
						case 8: // 买家发起退款

							break;
						case 9: // 退款成功

							break;
						case 10: // 退款关闭

							break;
						default:
						}
						// 中介担保处理业务完毕
						logger.info("trade_state = " + trade_state);
						// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
						resHandler.sendToCFT("success");
						success = true;
					}
				} else {
					// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
					logger.error("查询验证签名失败或id验证失败" + ",retcode:" + queryRes.getParameter("retcode"));
					success = false;
				}
			} else {
				logger.error("后台调用通信失败");
				logger.error(httpClient.getResponseCode());
				logger.error(httpClient.getErrInfo());
				// 有可能因为网络原因，请求已经处理，但未收到应答。
				success = false;
			}
		} else {
			logger.error("通知签名验证失败");
			success = false;
		}
		return success;
	}

	private String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	private Result processOrder(String sn,String fee){
		Order order = this.orderDao.load(sn);
		if(null == order){
			logger.error("订单["+sn+"]不存在");
			return new Result(false, "订单["+sn+"]不存在");
		}
		int realTotal = order.getTotal().intValue()*100;
		int returnFee = Integer.valueOf(fee);
		if(realTotal == returnFee){//订单实际金额和第三方支付平台返回金额相等
			logger.info("开始处理订单流程，给用户充值");
			if(order.getState().equals(OrderStatus.PAID.name())){
				Result result = orderService.process(order.getId());
				logger.info("处理结果:"+result.toString());
				return result;
			}else{
				logger.error("当前订单【"+sn+"】不满足充值条件");
				return new Result(false, "当前订单【"+sn+"】不满足充值条件");
			}
		}else{
			logger.error("订单金额不符");
			return new Result(false, "订单金额不符");
		}
	
	}
}
