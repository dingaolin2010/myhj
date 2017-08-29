package org.bumishi.admin.interfaces.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class Test {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) {
		

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
 		System.out.println("=====currentUser==============="+currentUser);
		System.out.println("====================");
		System.out.println("====================");
		System.out.println("====================");
	    model.addAttribute("list","list");
		
	    return "test/test";
	}

}
