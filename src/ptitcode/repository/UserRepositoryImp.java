package ptitcode.repository;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptitcode.entity.User;

@Repository
public class UserRepositoryImp implements UserRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	  @Override
	  public User findByUserName(String username) {
	    Session session=null;
	    try{
	    	session = sessionFactory.openSession();
	    	List<User> users= new ArrayList<User>();
	    	 users = session.createQuery("from User where username=?")
	                    .setParameter(0, username).list();
	    	 if (users.size()>0){
	    		 return users.get(0);
	    	 }
	    }
	    catch(HibernateException e){
	    	e.printStackTrace();
	    }
	    return null;
	  }
	  
	  public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	  
	  public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
}
