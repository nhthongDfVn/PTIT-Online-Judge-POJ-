package ptitcode.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptitcode.entity.Post;

@Transactional
@Controller
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("admin")
	public String showInfoasda(ModelMap model){
		Session session =factory.getCurrentSession();
		String hql="from UserInfo";
		Query query= session.createQuery(hql);
		List <Post> list=query.list();
		model.addAttribute("user",list);
		return "admin";
	}
}
