package ptitcode.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptitcode.entity.Exercise;


@Transactional
@Controller
public class ExerciseController {
	
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("/exercise")
	public String showExercise1(ModelMap model){	
		// goi den file exercise
		
		// lấy bài tập 
		Session session= factory.getCurrentSession();
		String hql="FROM Exercise";
		Query query= session.createQuery(hql);
		List<Exercise> list=query.list();
		model.addAttribute("exercise",list);	
		return "exercise";
	}
	
	
	@RequestMapping("/exercise/Details")
	public String showDetails(ModelMap model, HttpServletRequest request){
		Session session = factory.getCurrentSession();
		String id=request.getParameter("id");
		String hql="FROM Exercise Where exerciseID=:id";
		Query query=session.createQuery(hql);
		query.setString("id",id);
		List <Exercise> list= query.list();
		model.addAttribute("exercise",list);
		// xem chi tiet bai tap
		return "exercise/details";
	}
	
	// add new exercise GET
	@RequestMapping(value="/exercise/add-exercise", method=RequestMethod.GET)
	public String addExercise(ModelMap model){
		model.addAttribute("exercise", new Exercise());
		return "admin/new_exercise";
	}
	
	// add new exercise POST // insert
	@RequestMapping(value="/exercise/add-exercise", method=RequestMethod.POST)
	public String insertExercise(ModelMap model, @ModelAttribute("exercise")Exercise exercise){
		Session session = factory.openSession();
		Transaction t= session.beginTransaction();
		try{
			session.save(exercise);
			t.commit();
			model.addAttribute("message","success");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message","fail");
		}
		finally {
			session.close();
		}
		return "admin/new_exercise";
	}
	
	
	
	
	
	
	
}
