package ptitcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptitcode.entity.User;
import ptitcode.repository.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userDao;

	public boolean isUsers(String username, String password) {
		User user = userDao.findByUserName(username);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public UserRepository getUserDao() {
		return userDao;
	}

	public void setUserDao(UserRepository userDao) {
		this.userDao = userDao;
	}
}