package ptitcode.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptitcode.entity.Post;

@Transactional
@Controller
public class IndexController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/login")
	public String showLogin(){	
		// goi den file login
		return "login";
	}
	@RequestMapping("/register")
	public String showSignup(){	
		// goi den file register
		return "register";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String checkLogin(HttpServletRequest request){
		String uname=request.getParameter("username");
		String passwd= request.getParameter("password");
		if (uname.equals("admin")&&passwd.equals("password")){
			request.setAttribute("user", uname);
			return "hello";
		}
		if (uname.equals("")){
			request.setAttribute("message","Tên người dùng trống");
		}
		else
		if (passwd.equals("")){
			request.setAttribute("message","Mật khẩu trống");
		}
		else{
			request.setAttribute("message", "Sai tài khoản hoặc mật khẩu");
		}
		return "login";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String checkRegister(HttpServletRequest request){
		String uname=request.getParameter("username");
		String passwd= request.getParameter("password");
		String repasswd= request.getParameter("re-password");
		if (passwd.equals(repasswd)){
			request.setAttribute("message", "Đăng kí thành công");
			return "register";
		}
		request.setAttribute("message", "Đăng kí thất bại");
		return "register";
	}

	
	@RequestMapping("/about-us")
	public String showAboutUs(){	
		// goi den file about-us
		return "about-us";
	}
	

	@RequestMapping("/contest")
	public String showContest(){	
		// goi den file contests
		return "contests";
	}
	
	
	
	
	@RequestMapping("/admin")
	public String showAdmin(){	
		// goi den file admin
		return "admin";
	}
	
	@RequestMapping("/rank")
	public String query2(ModelMap model){
		Session session =factory.getCurrentSession();
		String hql="from Rank";
		Query query= session.createQuery(hql);
		List <Post> list=query.list();
		//Query query=session.createQuery("update User set username=:username where id=:id");
		//query.setString("username", "nht");
		//query.setInteger("id",4);
		//int modifications=query.executeUpdate();
		model.addAttribute("rank",list);
		return "rank";
	}
	
}
