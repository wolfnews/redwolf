package com.hoteam.wolf.http;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.hoteam.wolf.common.Result;
public class NoticeClient implements Callable<Result> {

	private static Logger logger = Logger.getLogger(NoticeClient.class);
	private HttpClient client;


	public NoticeClient(HttpClient client) {
		super();
		this.client = client;
	}


	@Override
	public Result call() throws Exception {
		logger.info(Thread.currentThread().getName()+" start...");
		long start = System.currentTimeMillis();
		String result = client.post(null, "http://101.200.189.179/mobile/notice/list");
		logger.info(result);
		long cost = System.currentTimeMillis() - start;
		logger.info(Thread.currentThread().getName()+"cost:"+cost+" ms");
		return new Result(true, "success");
	}

}
