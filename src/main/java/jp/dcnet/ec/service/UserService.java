package jp.dcnet.ec.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.ec.model.UserEntity;
import jp.dcnet.ec.obj.UserDTO;
import jp.dcnet.ec.repository.UserRepository;

@Service
public class UserService {
	 @Autowired
	    private UserRepository repo;
	    
	    @Autowired
	    private ModelMapper modelMapper;
	    // Entity => ProductDTO
	    
	    public UserDTO login(String username, String password) {
	    	UserEntity userEntity = repo.userLogin(username, password);
	        UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
	        return userDTO;
	    }
	    
	    public void updatePassword(Long userId, String password, String shinpassword) {
	    	UserEntity userEntity = repo.findById(userId).orElse(null);
	        if (userEntity != null) {
	        	if(getPasswordById(userId).equals(password)) {
	        		userEntity.setPassword(shinpassword);
	        		repo.save(userEntity);
	        		UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
	        	}
	        } else {
	        	throw new UserNotFoundException("User not found");
	        }
	    }
	    
	    public void updateUser(UserDTO userDTO) {
	    	UserEntity userEntity = repo.findByUsername(userDTO.getUsername());
	        if (userEntity != null) {
	        		userEntity.setUsername(userDTO.getUsername());
	        		userEntity.setPassword(userDTO.getPassword());
	        		userEntity.setEmail(userDTO.getEmail());
	        		userEntity.setFirstname(userDTO.getFirstname());
	        		userEntity.setLastname(userDTO.getLastname());
	        		userEntity.setAddress(userDTO.getAddress());
	        		userEntity.setPhonenumber(userDTO.getPhonenumber());
	        		repo.save(userEntity);
//	        		UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
	        } else {
	        	throw new UserNotFoundException("User not found");
	        }
	    }
	    
	    public UserDTO getUserById(Long userId) {
	    	UserEntity UserEntity = repo.findById(userId).orElse(null);
	        if (UserEntity != null) {
	            return modelMapper.map(UserEntity, UserDTO.class);
	        }
	        return null;
	    }
	    
	    public UserDTO getUserByUserName(String userName) {
	    	UserEntity UserEntity = repo.findByUsername(userName);
	        if (UserEntity != null) {
	            return modelMapper.map(UserEntity, UserDTO.class);
	        }
	        return null;
	    }
	    
	    public String getPasswordById(Long userId) {
	    	UserEntity userEntity = repo.findById(userId).orElse(null);
	        if (userId != null) {
	            return userEntity.getPassword();
	        } else {
	            return null; 
	        }
	    }
}


