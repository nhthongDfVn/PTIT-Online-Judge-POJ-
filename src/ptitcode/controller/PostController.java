package ptitcode.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptitcode.entity.Exercise;
import ptitcode.entity.Post;
import ptitcode.entity.Solution;

@Transactional
@Controller
public class PostController {
	@Autowired
	SessionFactory factory;
	
	
	public int getAllPage(){
		int numPost=3;
		int numPage=0;
		Session session = factory.getCurrentSession();
		String hql = "select count(*) from Post";
		Query query = session.createQuery(hql);
		Long count =(long)query.uniqueResult();
		if (count%numPost==0) numPage=(int) (count/numPost);
		else numPage=(int) (count/numPost+1);
		return numPage;
	}
	
	public List<Post> getPage(int i){
		Session session = factory.getCurrentSession();
		String hql = "FROM Post ORDER BY postID DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult(i);
		query.setMaxResults(3);
		List<Post> list = query.list();
		return list;
	}
	
	
	
	@RequestMapping("/index")
	public String showIndex(ModelMap model){	
		// lấy bài viết mới
		int numPage=getAllPage();
		List<Post> list = getPage(0);
		model.addAttribute("post", list);
		model.addAttribute("allpage",numPage);
		model.addAttribute("current",1);
		return "index";
	}
	
	@RequestMapping(value="/index",params="page")
	public String showIndexPage(ModelMap model, HttpServletRequest request){	
		// lấy bài viết mới
		int numPost=3;
		int numPage=getAllPage();
		int page=Integer.valueOf(request.getParameter("page"));
		
		List<Post> list = getPage(numPost*(page-1));
		model.addAttribute("post", list);
		model.addAttribute("allpage",numPage);
		model.addAttribute("current",page);
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
	public String insertExercise(ModelMap model, @ModelAttribute("post") Post post,BindingResult error) {
		if (post.getTitle().trim().length()==0){
			error.rejectValue("title","post","Vui lòng nhập tiêu đề bài viết");
		}
		else if (post.getBody().trim().length()==0) {
			error.rejectValue("body","post","Vui lòng nhập nội dung bài viết bài viết");
		}
		if (!error.hasErrors()){
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
		}
		return "post/new_post";
	}
	
	
	
	
	// view post
	@RequestMapping(value="/post/view/{id}")
	public String addSolution(ModelMap model,@PathVariable("id") int id){
		
		Session session = factory.getCurrentSession();
		Post post = (Post) session.get(Post.class,id);
		model.addAttribute("post", post);
		return "post/view_post";
	}
	
	    // edit Post 
		@RequestMapping(value = "/post/update/{id}",method = RequestMethod.GET)
		public String update(ModelMap model, @PathVariable("id") int id) {
			Session session = factory.getCurrentSession();
			Post post = (Post) session.get(Post.class,id);
			model.addAttribute("post", post);
			return "post/edit_post";	
		}
		// edit post: POST request

	@RequestMapping(value = "/post/update/{id}", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("post") Post post, @PathVariable("id") int id,
			BindingResult error) {
		if (post.getTitle().trim().length() == 0) {
			error.rejectValue("title", "post", "Vui lòng nhập tiêu đề bài viết");
		} else if (post.getBody().trim().length() == 0) {
			error.rejectValue("body", "post", "Vui lòng nhập nội dung bài viết bài viết");
		}
		if (!error.hasErrors()) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			try {
				session.update(post);
				t.commit();
				model.addAttribute("message", "success");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "fail");
				System.out.println(e.getMessage());
			} finally {
				Post post1 = (Post) session.get(Post.class, id);
				model.addAttribute("post", post1);
				session.close();
			}
		}
		return "post/edit_post";
	}

}
