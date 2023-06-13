package jp.dcnet.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.ec.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> { 
	@Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
    UserEntity userLogin(@Param("username") String username, @Param("password") String password);
	
	UserEntity findByUsername(String username);
}
