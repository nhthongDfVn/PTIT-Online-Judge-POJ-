package ptitcode.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

import ptitcode.entity.MD5;
import ptitcode.entity.Post;
import ptitcode.entity.Rank;
import ptitcode.entity.Submit;
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
			error.rejectValue("username","user","Vui lòng nhập Tên tài khoản");
		}
		if (user.getPassword().trim().length()==0){
			error.rejectValue("password","user","Vui lòng nhập mật khẩu");
		}
		
		if (!error.hasErrors()){
			MD5 v= new MD5();
			user.setPassword(v.getMd5(user.getPassword()));
			if (isUserExist(user.getUsername(),user.getPassword())==true){
				session.setAttribute("username",user.getUsername());
				if (user.getUsername().equalsIgnoreCase("admin")){
					session.setAttribute("isAdmin",true);
				}
				else{
					session.setAttribute("isAdmin",false);
				}
			}    
			else {
				model.addAttribute("message","fail");
				model.addAttribute("user", new User());
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
			
			// save in userInfo
			UserInfo userInfo;
			userInfo= new UserInfo();
			userInfo.setUsername(user.getUsername());
			Session session3 = factory.openSession();
			Transaction t3 = session3.beginTransaction();
			try {
				session3.save(userInfo);
				t3.commit();	
			} catch (Exception e) {
				t3.rollback();
				e.getMessage();
			} 
			
			
			
			// save in User
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			Session session1 = factory.openSession();
			Transaction t1 = session1.beginTransaction();
			Rank rank= new Rank();
			rank.setUsername(user.getUsername());
			rank.setScore(0);
			MD5 v= new MD5();
			user.setPassword(v.getMd5(user.getPassword()));
			try {
				session.save(user);
				t.commit();
				session1.save(rank);
				t1.commit();
				setDefaultImage(user.getUsername());
				model.addAttribute("message", "success");
			} catch (Exception e) {
				t.rollback();
				t1.rollback();
				model.addAttribute("message", "fail");
			} finally {
				session.close();
				session1.close();
				model.addAttribute("user", new User());
			}
		}
		return "register";
	}
	
	
	
	
	
	@RequestMapping(value="/profile/view/{id}", method=RequestMethod.GET)
	public String viewUserProfile(ModelMap model,@PathVariable("id") String username){
		
		if (isExist(username)==false){
			return "404";
		}
		
		
		Session session = factory.getCurrentSession();
		UserInfo userInfo = null;
		userInfo = (UserInfo) session.get(UserInfo.class,username);
		
		if (userInfo==null){
			userInfo= new UserInfo();
			userInfo.setUsername(username);
			Session session1 = factory.openSession();
			Transaction t = session1.beginTransaction();
			try {
				session.save(userInfo);
				t.commit();	
			} catch (Exception e) {
				t.rollback();
				e.getMessage();
			} 
			userInfo = (UserInfo) session.get(UserInfo.class,username);	
		}
		
		model.addAttribute("userInfo",userInfo);
		return "profile/viewUserInfo";
	}
	
	@RequestMapping(value="/profile/update/{id}", method=RequestMethod.GET)
	public String updateUserProfile(ModelMap model,@PathVariable("id") String username, HttpSession sessionUser){
		if (sessionUser.getAttribute("username")==null){
			return "403";
		}
		String sUser=(String) sessionUser.getAttribute("username");
		if (!sUser.equals(username)){
			return "403";
		}
		
		
		Session session = factory.getCurrentSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class,username);
		model.addAttribute("userInfo",userInfo);
		
		
		
		return "profile/updateUserInfo";
	}
	
	@RequestMapping(value="/profile/update/{id}", method=RequestMethod.POST)
	public String updateUserProfile(ModelMap model,@PathVariable("id") String username,@ModelAttribute("userInfo")UserInfo userInfo,HttpSession sessionUser, BindingResult error){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		String sUser=(String) sessionUser.getAttribute("username");
		if (!sUser.equals(username)){
			model.addAttribute("message","fail");	
			UserInfo post1 = (UserInfo) session.get(UserInfo.class,username);
			model.addAttribute("userInfo", post1);
			System.out.println("Tại đây 1");
			return "profile/updateUserInfo";
		}
		if (!userInfo.getEmail().equals("")){
			if (EmailIsExist(userInfo.getEmail(),username)==true){
				model.addAttribute("message","fail");	
				UserInfo post1 = (UserInfo) session.get(UserInfo.class,username);
				model.addAttribute("userInfo", post1);
				System.out.println("Tại đây 1");
				return "profile/updateUserInfo";
			}
		}
		
		
		
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
	
	@RequestMapping(value="/profile/change-password/{id}", method=RequestMethod.GET)
	public String chanegUserPasword(ModelMap model,@PathVariable("id") String username, HttpSession sessionUser ){
		if (sessionUser.getAttribute("username")==null){
			return "403";
		}
		
		String sUser=(String) sessionUser.getAttribute("username");
		if (!sUser.equals(username)){
			return "403";
		}
		Session session = factory.getCurrentSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class,username);
		model.addAttribute("userInfo",userInfo);	
		
		model.addAttribute("id","username");
		return "profile/changeUserPassword";
	}
	
	@RequestMapping(value="/profile/change-password/{id}", method=RequestMethod.POST)
	public String chanegUserPasword1(ModelMap model,HttpServletRequest request,@PathVariable("id") String username, HttpSession sessionUser){
		
		String sUser=(String) sessionUser.getAttribute("username");
		if (!sUser.equals(username)){
			model.addAttribute("message","fail");	
			model.addAttribute("id","username");
			return "profile/changeUserPassword";
		}
		
		
		String oldpass= request.getParameter("oldpass");
		String newpass= request.getParameter("newpass");
		String renewpass= request.getParameter("re-newpass");
		
		if (oldpass.trim().length()==0){
			model.addAttribute("oldpasserr","Không được để trống");
		}
		else if (newpass.trim().length()==0){
			model.addAttribute("newpasserr","Không được để trống");
		}
		else
		if (renewpass.trim().length()==0){
			model.addAttribute("renewpasserr","Không được để trống");
		}
		else if (!newpass.equals(renewpass)){
			model.addAttribute("newpasserr","Nhập lại mật khẩu chưa chính xác");
		}else{
			// check in database
			MD5 v= new MD5();
			oldpass= v.getMd5(oldpass);
			if (isUserExist(username,oldpass)==false){
				model.addAttribute("oldpasserr","Lỗi: Sai mật khẩu");
			}
			else{
				// old password true
				// update new passowd
				Session session = factory.openSession();
				Transaction t = session.beginTransaction();
				User user= new User();
				user.setUsername(username);
				MD5 v1= new MD5();
				user.setPassword(v1.getMd5(newpass));
				
				try {
					session.update(user);
					t.commit();
					model.addAttribute("message", "success");
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "fail");
				} finally {
					session.close();
				}
				
				
			}
		}
		
		
		
		
		model.addAttribute("id","username");
		return "profile/changeUserPassword";
	}
	
	
	
	
	
	@RequestMapping(value="/profile/update/image/{id}", method=RequestMethod.GET)
	public String updateUserImageGET(ModelMap model,HttpServletRequest request,@PathVariable("id") String username, HttpSession sessionUser){
		if (sessionUser.getAttribute("username")==null){
			return "403";
		}
		
		String sUser=(String) sessionUser.getAttribute("username");
		if (!sUser.equals(username)){
			return "403";
		}
		Session session = factory.getCurrentSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class,username);
		model.addAttribute("userInfo",userInfo);	
		
		return "profile/updateUserImage";
	}
	
	
	@RequestMapping(value="/profile/update/image/{id}", method=RequestMethod.POST)
	public String updateUserImageProfile(ModelMap model,@PathVariable("id") String username, @RequestParam("image") MultipartFile image){
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		if (image.isEmpty()){
			model.addAttribute("uploadErr", "Vui lòng chọn file");
		}
		else
		{
			if (saveProfileImage(image, username)==true){
				model.addAttribute("message","success");
			}
			else{
				model.addAttribute("message","fail");
			}
		}
		//UserInfo post1 = (UserInfo) session.get(UserInfo.class,username);
		//model.addAttribute("userInfo", post1);
	
		return "profile/updateUserImage";
	}
	
	
	@RequestMapping(value="/profile/view/submit/{id}", method=RequestMethod.GET)
	public String viewUserSubmit(ModelMap model,@PathVariable("id") String username){
		
		Session session = factory.getCurrentSession();
		String hql = "FROM Submit WHERE username=:username ORDER BY submitID DESC";
		Query query = session.createQuery(hql);
		query.setString("username",username);
		List<Submit> list = query.list();
		model.addAttribute("submit",list);
		
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class,username);
		model.addAttribute("userInfo",userInfo);	
		return "profile/yoursubmission";
	}
	
	
	
	public boolean saveProfileImage(MultipartFile photo, String username){
		boolean result=true;
		try {
			System.out.println(context.getRealPath(""));
			String photoPath = context.getRealPath("/images/profile/" + username+".png");
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
    
    public boolean EmailIsExist(String email, String username){
    	UserInfo user= null;
    	boolean re=false;
    	Session session = factory.getCurrentSession();
		String hql = "FROM UserInfo  WHERE email=:email";
		Query query = session.createQuery(hql);
		query.setString("email",email);
		List<UserInfo> list = query.list();
		 if (list.size()>0) user= list.get(0);
		if (user!= null){
			re=true;
		}
		if (user.getUsername().equals(username)){
			re=false;
		}
		return re;		
    }
    
    
    public void setDefaultImage(String username){
		String s="cd ../../Workspace/Eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PTITCoding/images/profile& COPY default.png "+username+".png";
		
		String commandArray[] = {"cmd", "/c", s}; 
		try {
			Process process = Runtime.getRuntime().exec(commandArray); 
		 
		    BufferedReader reader = new BufferedReader(
		            new InputStreamReader(process.getInputStream()));
		 
		    reader.close();
		 
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
    
		
}