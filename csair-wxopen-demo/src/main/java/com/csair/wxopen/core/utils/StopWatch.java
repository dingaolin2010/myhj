package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StopWatch {
private static final Logger log = LoggerFactory.getLogger(StopWatch.class);
	
	private String name;
	private List<String> phrases;
	private List<Long> mills;
	
	public StopWatch(){
		mills = new ArrayList<Long>();
		mills.add(System.currentTimeMillis());
		phrases = new ArrayList<String>();
		phrases.add("START");
	}
	
	public StopWatch(String name){
		this();
		this.name = name;
	}
	
	public void stop(){
		phrases.add("STEP" + mills.size());
		mills.add(System.currentTimeMillis());
	}
	
	public void stop(String phraseName){
		mills.add(System.currentTimeMillis());
		phrases.add(phraseName);
	}
	
	/**
	 * 获取阶段耗时，返回毫秒
	 * @param index
	 * @return
	 */
	public long getPhrase(int index){
		if(index <= 0 || index >= mills.size()) return -1;
		return mills.get(index) - mills.get(index-1);
	}
	
	/**
	 * 获取总共耗时
	 * @return
	 */
	public long getTotal(){
		int len = mills.size();
		return mills.get(len-1) - mills.get(0);
	}
	
	/**
	 * 打印耗时情况
	 */
	public void print(){
		StringBuffer sb = new StringBuffer();
		int len = mills.size();
		for(int i=0; i<len-1; i++){
			sb.append("[" + phrases.get(i+1) + "]" + (mills.get(i+1) - mills.get(i)) + "/");
		}
		if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);
//		System.out.println("[计时器:" + name + "][phrase]" + sb.toString() + "ms,[total]" + (mills.get(len-1) - mills.get(0)) + "ms");
		log.info("[计时器:" + name + "][phrase]" + sb.toString() + "ms,[total]" + (mills.get(len-1) - mills.get(0)) + "ms");
	}
}
