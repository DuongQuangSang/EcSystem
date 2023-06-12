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

    /**
     * ユーザー名とパスワードでログインします。
     *
     * @param username ユーザー名
     * @param password パスワード
     * @return ログインしたユーザーのUserDTO
     */
    public UserDTO login(String username, String password) {
        UserEntity userEntity = repo.userLogin(username, password);
        if (userEntity == null) {
        	 throw new BadRequestException("Invalid username or password");
        }
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    /**
     * パスワードを更新します。
     *
     * @param userId      ユーザーID
     * @param password    現在のパスワード
     * @param shinpassword 新しいパスワード
     */
    public void updatePassword(Long userId, String password, String shinpassword) {
        UserEntity userEntity = repo.findById(userId).orElse(null);
        if (userEntity != null) {
            if (getPasswordById(userId).equals(password)) {
                userEntity.setPassword(shinpassword);
                repo.save(userEntity);
//	        		UserDTO userDTO = modelMapper.map(userEntity,UserDTO.class);
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * ユーザー情報を更新します。
     *
     * @param userDTO 更新するユーザーのUserDTO
     */
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

    /**
     * 指定したユーザーIDのユーザー情報を取得します。
     *
     * @param userId ユーザーID
     * @return ユーザーのUserDTO
     */
    public UserDTO getUserById(Long userId) {
        UserEntity UserEntity = repo.findById(userId).orElse(null);
        if (UserEntity != null) {
            return modelMapper.map(UserEntity, UserDTO.class);
        }
        return null;
    }

    /**
     * 指定したユーザー名のユーザー情報を取得します。
     *
     * @param userName ユーザー名
     * @return ユーザーのUserDTO
     */
    public UserDTO getUserByUserName(String userName) {
        UserEntity UserEntity = repo.findByUsername(userName);
        if (UserEntity != null) {
            return modelMapper.map(UserEntity, UserDTO.class);
        }
        return null;
    }

    /**
     * 指定したユーザー名が存在するかどうかを確認します。
     *
     * @param userName ユーザー名
     * @return 存在する場合はtrue、存在しない場合はfalse
     */
    public boolean isUserNameExist(String userName) {
        UserEntity UserEntity = repo.findByUsername(userName);
        if (UserEntity != null) {
            return false;
        }
        return true;
    }

    /**
     * 指定したユーザーIDのパスワードを取得します。
     *
     * @param userId ユーザーID
     * @return パスワード
     */
    public String getPasswordById(Long userId) {
        UserEntity userEntity = repo.findById(userId).orElse(null);
        if (userId != null) {
            return userEntity.getPassword();
        } else {
            return null;
        }
    }

    /**
     * 新しいユーザーを作成します。
     *
     * @param userDTO 作成するユーザーのUserDTO
     */
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        repo.save(userEntity);
    }
}
