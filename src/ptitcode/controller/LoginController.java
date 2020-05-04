package ptitcode.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import ptitcode.entity.User;
import ptitcode.repository.UserRepository;
import ptitcode.repository.UserRepositoryImp;
import ptitcode.service.UserService;
 
@Transactional
@Controller
public class LoginController {
	
	//UserService userService;
	@Autowired
	SessionFactory factory;

    
    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public String showLogin() {
    	return "login";
    }
    
    @RequestMapping(value = "/login1", method = RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, ModelMap model){
    	String uname=request.getParameter("username");
		String passwd= request.getParameter("password");
        ModelAndView view = null;
        Session session = factory.getCurrentSession();
        List<User> users= new ArrayList<User>();
        String hql= "from User U where U.username=? and U.password=?";
        Query query=session.createQuery(hql);
        query.setParameter(0, uname);
        query.setParameter(1, passwd);
        users = query.list();
        model.addAttribute("users",users);
		if (users.size()==1)
			{
				view = new ModelAndView("admin");
			}
			else
			{
				view = new ModelAndView("demo1");    
			}
		return view;     
    }
		
}