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
	    
	    public void updatePassword(String username, String newPassword) {
	    	UserEntity userEntity = repo.findByUsername(username);
	        if (userEntity != null) {
	        	userEntity.setPassword(newPassword);
	        	repo.save(userEntity);
	        	UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
	        } else {
	            // Handle user not found case
	        }
	    }
}
