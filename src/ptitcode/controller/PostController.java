package ptitcode.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptitcode.entity.Post;

@Transactional
@Controller
public class PostController {
	@Autowired
	SessionFactory factory;
	
	
	
	
	@RequestMapping("/index")
	public String showIndex(ModelMap model){	
		// lấy bài viết mới
		Session session = factory.getCurrentSession();
		String hql = "FROM Post";
		Query query = session.createQuery(hql);
		List<Post> list = query.list();
		model.addAttribute("post", list);
		return "index";
	}
	
	
	
	
	
	
	// add new exercise GET // 
	@RequestMapping(value="/post/add-post", method=RequestMethod.GET)
	public String addExercise(ModelMap model){
		model.addAttribute("post", new Post());
		return "post/new_post";
	}
	
	
	// add new exercise POST // insert
	@RequestMapping(value = "/post/add-post", method = RequestMethod.POST)
	public String insertExercise(ModelMap model, @ModelAttribute("post") Post post) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			post.setDate(new Date());
			session.save(post);
			t.commit();
			model.addAttribute("message", "success");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "fail");
		} finally {
			session.close();
			model.addAttribute("post", new Post());
		}
		return "post/new_post";
	}
}
