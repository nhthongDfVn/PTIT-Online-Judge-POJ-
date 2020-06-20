package ptitcode.controller;

import java.security.SecureRandom;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptitcode.entity.MD5;
import ptitcode.entity.User;
import ptitcode.entity.UserInfo;

@Transactional
@Controller


public class MailerController {
	@Autowired
	JavaMailSender mailer;
	
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	
	@RequestMapping(value="/recover", method=RequestMethod.GET)
	public String reco(){
		return "profile/recovery";
	}
	
	
	
	@RequestMapping(value = "/recover", method = RequestMethod.POST)
	public String send(ModelMap model,@RequestParam("email")String to) {
		String rep=EmailIsExist(to);
		String random=getRandomString(10);
		if (!rep.equals("")) {
			try {
				MimeMessage mail = mailer.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail);
				String from = "ptitonlinejudge@gmail.com";
				String subject = "Reset password";
				String body = "Your account has been reset. Your default password is:";
				body = body +" "+ random;
				body = body + " . Use this password to login. <br> From PTIT Online Judge";

				// String to="thongnguyen3600@gmail.com";

				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setReplyTo(from, from);
				helper.setSubject(subject);
				helper.setText(body, true);
				mailer.send(mail);
				model.addAttribute("message", "success");
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("message", e.getMessage());
				System.out.println(e.getMessage());
			}
			// save password 
			saveRandomPassword(rep,random);
			
		}
		
		model.addAttribute("message", "success");
		
		
		
		return "profile/recovery";
	}
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public String getRandomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	
	
	public void saveRandomPassword(String username, String newpass){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User user= new User();
		user.setUsername(username);
		MD5 v= new MD5();
		user.setPassword(v.getMd5(newpass));
		
		try {
			session.update(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}
	
	public String EmailIsExist(String email){
    	UserInfo user= null;
    	String re="";
    	Session session = factory.getCurrentSession();
		String hql = "FROM UserInfo  WHERE email=:email";
		Query query = session.createQuery(hql);
		query.setString("email",email);
		List<UserInfo> list = query.list();
		 if (list.size()>0) user= list.get(0);
		if (user!=null){
			re= user.getUsername();
		}
		return re;		
    }
	
	

}
