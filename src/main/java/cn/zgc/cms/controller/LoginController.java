package cn.zgc.cms.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zgc.cms.model.Role;
import cn.zgc.cms.model.RoleType;
import cn.zgc.cms.model.User;
import cn.zgc.cms.service.IUserService;
import cn.zgc.cms.util.Captcha;

/** 
 * @author gczhang  E-mail:coding_zgc@163.com 
 * 
 */
@Controller
public class LoginController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,String checkcode,Model model,HttpSession session) {
		// 判断验证码是否一致
		String cc = (String) session.getAttribute("checkCode");
		if(!cc.equalsIgnoreCase(checkcode)){
			model.addAttribute("error","验证码出错，请重新输入");
			return "admin/login";
		}
		User loginUser = userService.login(username, password);
		session.setAttribute("user", loginUser);
		List<Role> rs = userService.listUserRoles(loginUser.getId());
		boolean isAdmin = isAdmin(rs);
		session.setAttribute("isAdmin", isAdmin);
		if(!isAdmin)
			session.setAttribute("allActions", getAllActions(rs, session));
		session.removeAttribute("checkCode");
		return "redirect:/admin";
	}
	
	private Object getAllActions(List<Role> rs, HttpSession session) {
		
		return null;
	}
	
	// 判断是否为管理员
	private boolean isAdmin(List<Role> rs) {
		for(Role role : rs){
			if(role.getRoleType()==RoleType.ROLE_ADMIN)
				return true;
		}
		return false;
	}

	@RequestMapping("/drawCode")
	public void drawCode(HttpSession session,HttpServletResponse response){
		response.setContentType("image/jpg");   //没有这句也可以
		String checkCode = Captcha.getInstance().generateCheckCode();
		//将验证码放入session 
		session.setAttribute("checkCode", checkCode);
		BufferedImage image = Captcha.getInstance().generateCheckImg(checkCode,200,30);
		try {
			OutputStream out = response.getOutputStream();
			ImageIO.write(image, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
