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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptitcode.entity.Exercise;
import ptitcode.entity.Testcase;


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
	
	
	@RequestMapping("/exercise/view/{id}")
	public String views(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		model.addAttribute("exercise", exercise);
		return "exercise/details";
		
	}
	

	@RequestMapping("/exercise/testcase/{id}")
	public String viewTestcase(ModelMap model, @PathVariable("id") String id) {
		Session session= factory.getCurrentSession();
		String hql="FROM Testcase where exercise.exerciseID=:id";
		Query query= session.createQuery(hql);
		query.setString("id",id);
		List<Testcase> list=query.list();	
		model.addAttribute("testcase", list);
		return "exercise/testcase";
		
	}
	
	
	
	// add new testcase GET
		@RequestMapping(value="/exercise/add-testcase", method=RequestMethod.GET)
		public String addTestcase(ModelMap model){
			
			model.addAttribute("testcase", new Testcase());
			return "exercise/add_testcase";
		}
		@ModelAttribute("exercise")
		public List<Exercise> getExercise(){
			Session session= factory.getCurrentSession();
			String hql="FROM Exercise";
			Query query= session.createQuery(hql);
			List<Exercise> list=query.list();
			return list;	
		}
	
	// add new exercise POST // insert
		@RequestMapping(value="/exercise/add-testcase", method=RequestMethod.POST)
		public String addTestCase(ModelMap model, @ModelAttribute("testcase")Testcase testcase){
			System.out.println(testcase.getExercise().getExerciseID());
			System.out.println(testcase.getInput());
			System.out.println(testcase.getNumtest());
			System.out.println(testcase.getOutput());
			
			Session session = factory.openSession();
			Transaction t= session.beginTransaction();
			try{
				session.save(testcase);
				t.commit();
				model.addAttribute("message","success");
			}
			catch(Exception e){
				t.rollback();
				model.addAttribute("message","fail");
				System.out.println(e.getMessage());
			}
			finally {
				session.close();
			}
			return "exercise/add_testcase";
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
	
	
	// edit exercise // edit   // GET request
	@RequestMapping(value = "/exercise/update/{id}",method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		model.addAttribute("exercise", exercise);
		return "exercise/edit";	
	}
	// edit exercise // edit   // POST request
	
	@RequestMapping(value = "/exercise/update/{id}", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("exercise") Exercise exercise,  @PathVariable("id") int id) {	
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(exercise);
			t.commit();
			model.addAttribute("message","success");
		} catch (Exception e) {
			t.rollback();
			System.out.print(e.getMessage());
			model.addAttribute("message","fail");	
		} finally {
			Exercise exercise1 = (Exercise) session.get(Exercise.class,id);
			model.addAttribute("exercise", exercise1);
			session.close();			
		}
		return "exercise/edit";
	}
	
}
