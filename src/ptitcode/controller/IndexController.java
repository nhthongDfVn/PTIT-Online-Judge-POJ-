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

import ptitcode.entity.Exercise;
import ptitcode.entity.Post;
import ptitcode.entity.Rank;
import ptitcode.entity.User;

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
	
	@RequestMapping("/help")
	public String showHelp(){	
		// goi den file help
		return "help";
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
	
	@RequestMapping(value="search",method=RequestMethod.POST)
	public String searchk(ModelMap model, HttpServletRequest request){
		String keyword= request.getParameter("keyword");
		
		
		// search username
		Session session =factory.getCurrentSession();
		String hql="from User where username like concat('%',:username,'%')";
		Query query= session.createQuery(hql);
		query.setParameter("username",keyword);
		List <User> list=query.list();
		model.addAttribute("user",list);
		
		
		// search post
		Session session1 = factory.getCurrentSession();
		String hql1 = "from Post where title like concat('%',:username,'%') or body like concat('%',:username,'%')";
		Query query1 = session1.createQuery(hql1);
		query1.setParameter("username", keyword);
		List<Post> list1 = query1.list();
		model.addAttribute("post", list1);
		
		// search exercise
		Session session2 = factory.getCurrentSession();
		String hql2 = "from Exercise where detail like concat('%',:username,'%') ";
		Query query2 = session2.createQuery(hql2);
		query2.setParameter("username", keyword);
		List<Exercise> list2 = query2.list();
		model.addAttribute("exercise", list2);
		
		return "search";
	}
	
	
}
