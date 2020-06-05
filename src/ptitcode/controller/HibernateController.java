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
import ptitcode.entity.User;

@Transactional
@Controller
public class HibernateController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/query")
	public String query(ModelMap model){
		Session session =factory.getCurrentSession();
		String hql="from User";
		Query query= session.createQuery(hql);
		List <User> list=query.list();
		//Query query=session.createQuery("update User set username=:username where id=:id");
		//query.setString("username", "nht");
		//query.setInteger("id",4);
		//int modifications=query.executeUpdate();
		model.addAttribute("users",list);
		return "demo1";
	}
	
	
	@RequestMapping("/query1")
	public String query1(ModelMap model){
		Session session =factory.getCurrentSession();
		String hql="from Post";
		Query query= session.createQuery(hql);
		List <Post> list=query.list();
		//Query query=session.createQuery("update User set username=:username where id=:id");
		//query.setString("username", "nht");
		//query.setInteger("id",4);
		//int modifications=query.executeUpdate();
		model.addAttribute("post",list);
		return "demo2";
	}
	
	
	
}
