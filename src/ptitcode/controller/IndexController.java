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
	

	


	
	@RequestMapping("/about-us")
	public String showAboutUs(){	
		// goi den file about-us
		return "about-us";
	}
	
	
	
	@RequestMapping("/admin")
	public String showAdmin(){	
		// goi den file admin
		return "admin";
	}
	
	@RequestMapping("/rank")
	public String query2(ModelMap model){
		Session session =factory.getCurrentSession();
		String hql="from Rank order by score DESC";
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
