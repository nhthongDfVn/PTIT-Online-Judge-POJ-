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
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	
	
	
	@RequestMapping("all-post")
	public String showAllPost(ModelMap model){
		Session session =factory.getCurrentSession();
		String hql="from Post";
		Query query= session.createQuery(hql);
		List <Post> list=query.list();
		model.addAttribute("post",list);
		return "admin/view_all_post";
	}

}
