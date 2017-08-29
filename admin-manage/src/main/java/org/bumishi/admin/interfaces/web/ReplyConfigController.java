package org.bumishi.admin.interfaces.web;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 回复配置
 * @Date 2017-08-29
 * @author Hangar
 */
@Controller
@RequestMapping(value = "/reply")
public class ReplyConfigController {
	 
	/**
	 * 关注时回复查询
	 * @return
	 */
	@RequestMapping(value="/showAttentionReply",method = RequestMethod.GET)
	public String showAttentionReply(){
		System.out.println("================");
		return "reply/attentionReply";
	}
	/**
	 * 关注时回复提交
	 * @return
	 */
	@RequestMapping(value="/submitAttentionReply",method = RequestMethod.POST)
	public String submitAttentionReply(){
		System.out.println("================");
		return "reply/attentionReply";
	}
	
	
	
	
	
	/**
	 * 文本回复查询
	 * @return
	 */
	@RequestMapping(value="/showAttentionReply",method = RequestMethod.GET)
	public String showTextReply(){
		System.out.println("================");
		return "reply/attentionReply";
	}
	/**
	 * 文本回复提交
	 * @return
	 */
	@RequestMapping(value="/submitAttentionReply",method = RequestMethod.POST)
	public String submitTextReply(){
		System.out.println("================");
		return "reply/attentionReply";
	}
	

	
	
	
	/**
	 * 图文回复查询
	 * @return
	 */
	@RequestMapping(value="/showAttentionReply",method = RequestMethod.GET)
	public String showImageReply(){
		System.out.println("================");
		return "reply/attentionReply";
	}
	/**
	 * 图文回复提交
	 * @return
	 */
	@RequestMapping(value="/submitAttentionReply",method = RequestMethod.POST)
	public String submitImageReply(){
		System.out.println("================");
		return "reply/attentionReply";
	}
	
}