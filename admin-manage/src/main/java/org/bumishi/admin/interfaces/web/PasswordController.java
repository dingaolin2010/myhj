package org.bumishi.admin.interfaces.web;

import org.bumishi.admin.application.UserService;
import org.bumishi.admin.domain.modle.User;
import org.bumishi.admin.interfaces.facade.commondobject.UserCommond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 密码修改
 * @Date 2017-08-29
 * @author Hangar
 *
 */
@Controller
@RequestMapping(value = "/pwd")
public class PasswordController {
	
	@Autowired
	protected UserService userService;

	/**
	 * 显示密码修改界面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showPwd", method = RequestMethod.GET)
	public String chagePwd(@RequestParam(value = "id") String id, Model model) {
		
		String url = "/pwd/" + id + "/modifyPwd"; 
		model.addAttribute("pwdUrl", url);
		return "user/changePwd";
	}

	/**
	 * 密码提交修改
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/{id}/modifyPwd", method = RequestMethod.POST)
	public String modifyPwd(@PathVariable("id") String id,UserCommond user) {
		User modUser = new User();
		modUser.setId(id);
		modUser.setPassword(user.getPassword());
		userService.modify(modUser);
		return "redirect:/user";
	}
}