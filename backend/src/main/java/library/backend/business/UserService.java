package library.backend.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.backend.model.User;
import library.backend.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAll() {
		return userRepository.findAll();	
	}
	
	public User getByFirstName(String firstname) {
		return userRepository.getByFirstName(firstname);
	}
	
	
	
	
	
	
	
	/*
	 * @Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId),"Data listelendi");
	}

	 */
	
	
	
	
	
}
