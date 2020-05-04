package ptitcode.repository;
import ptitcode.entity.User;




public interface UserRepository {
	 public User findByUserName(String username);
	}
