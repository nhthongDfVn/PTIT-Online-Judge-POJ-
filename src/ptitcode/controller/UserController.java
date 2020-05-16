package ptitcode.controller;
import java.io.File;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.javafx.sg.prism.NGShape.Mode;

import ptitcode.entity.Post;
import ptitcode.entity.User;
import ptitcode.entity.UserInfo;
 
@Transactional
@Controller
public class UserController {
	
	//UserService userService;
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(ModelMap model) {
    	model.addAttribute("user", new User());
    	return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String executeLogin(ModelMap model, @ModelAttribute("user")User user,HttpSession session,BindingResult error){
		if (user.getUsername().trim().length()==0){
			error.rejectValue("username","user","Vui lòng nhập trường này");
		}
		if (user.getPassword().trim().length()==0){
			error.rejectValue("password","user","Vui lòng nhập trường này");
		}
		
		if (!error.hasErrors()){
			if (isUserExist(user.getUsername(),user.getPassword())==true){
				model.addAttribute("message","User exist");
				session.setAttribute("username",user.getUsername());
				if (user.getUsername().equalsIgnoreCase("admin")){
					session.setAttribute("isAdmin",true);
				}
				else{
					session.setAttribute("isAdmin",false);
				}
			}    
			else {
				model.addAttribute("message","Not found");
			}
		}	
		return "login";
    }
    
    @RequestMapping("/logout")
    public String logout(ModelMap model, HttpSession session){
    	session.removeAttribute("username");
    	session.removeAttribute("isAdmin");
    	return "redirect:index.htm";
    }
    
    
    @RequestMapping("/register")
	public String showSignup(ModelMap model){	
		// goi den file register
    	
    	model.addAttribute("user", new User());
		return "register";
	}
    

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String checkRegister(ModelMap model,@ModelAttribute("user")User user1 ,HttpServletRequest request, BindingResult error){
		boolean okay=true;
		
		String repasswd= request.getParameter("re-password");
		
		if (user1.getUsername().trim().length()==0){
			error.rejectValue("username","user","Vui lòng nhập tên tài khoản");
		}
		if (user1.getPassword().trim().length()==0){
			error.rejectValue("password","user","Vui lòng nhập mật khẩu");
		}
		if (repasswd.trim().length()==0){
			model.addAttribute("errmess","Vui lòng nhập lại mật khẩu một lần nữa");
			okay=false;
		}
		if (!user1.getPassword().trim().equals(repasswd.trim())){	
			model.addAttribute("errmess","Mật khẩu chưa trùng khớp");
			okay=false;
		}
		User user= new User(user1.getUsername(), user1.getPassword());
		
		if (user1.getUsername().trim().length()>0&&isExist(user.getUsername())==true){
			error.rejectValue("username","user","Tài khoản đã tồn tại. Vui lòng chọn tên khác");
		}
		
		if (!error.hasErrors()&&okay==true)
		{
			
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(user);
				t.commit();
				model.addAttribute("message", "success");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "fail");
			} finally {
				session.close();
				model.addAttribute("user", new User());
			}
			
		}
		return "register";
	}
	
	@RequestMapping(value="/profile/view/{id}", method=RequestMethod.GET)
	public String viewUserProfile(ModelMap model,@PathVariable("id") String username){
		Session session = factory.getCurrentSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class,username);
		model.addAttribute("userInfo",userInfo);
		return "profile/viewUserInfo";
	}
	
	@RequestMapping(value="/profile/update/{id}", method=RequestMethod.GET)
	public String updateUserProfile(ModelMap model,@PathVariable("id") String username){
		Session session = factory.getCurrentSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class,username);
		model.addAttribute("userInfo",userInfo);
		return "profile/updateUserInfo";
	}
	
	@RequestMapping(value="/profile/update/{id}", method=RequestMethod.POST)
	public String updateUserProfile(ModelMap model,@PathVariable("id") String username,@ModelAttribute("userInfo")UserInfo userInfo, BindingResult error){
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		userInfo.setUsername(username);
		
		try {
			session.update(userInfo);
			t.commit();
			model.addAttribute("message","success");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","fail");	
			System.out.println(e.getMessage());
		} finally {
			UserInfo post1 = (UserInfo) session.get(UserInfo.class,username);
			model.addAttribute("userInfo", post1);
			session.close();			
		}
		return "profile/updateUserInfo";
	}
	
	
	
	public boolean saveImage(MultipartFile photo){
		boolean result=true;
		Random rand = new Random();
		try {
			String name="";
			for (int i=0;i<7;i++){
				name=name+String.valueOf(rand.nextInt(99));
			}
			String photoPath = context.getRealPath("/image/" + name);
			photo.transferTo(new File(photoPath));
		} catch (Exception e) {
			System.out.print(e.getMessage());
			result=false;
		}	
		return result;
	}
	
	
	
    
    
    public boolean isUserExist(String username, String password) {
		Session session =factory.getCurrentSession();
		User user=null;
		boolean re=false;
		user=(User)session.get(User.class,username);
		
		if (user!=null){
			if (user.getPassword().equals(password))
				re=true; 
			else 
				re=false;
		}
		return re;		
	}
    
    public boolean isExist(String username){
    	Session session =factory.getCurrentSession();
		User user=null;
		boolean re=false;
		user=(User)session.get(User.class,username);
		
		if (user!=null)
				re=true; 
			else 
				re=false;
		return re;		
    }
    
		
}