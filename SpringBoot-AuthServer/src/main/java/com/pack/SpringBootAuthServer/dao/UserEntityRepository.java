package com.pack.SpringBootAuthServer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pack.SpringBootAuthServer.model.UserEntity;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Integer>{

	@Query(value="SELECT * FROM USER WHERE EMAIL_ID=?1",nativeQuery=true)
	public List<UserEntity> getListUserEntities(String emailId);
	
	@Query(value="SELECT DISTINCT P.PERMISSION_NAME FROM PERMISSION P INNER JOIN ASSIGN_PERMISSION_TO_ROLE P_R ON P.ID=P_R.PERMISSION_ID INNER JOIN ROLE R ON R.ID=P_R.ROLE_ID INNER JOIN ASSIGN_USER_TO_ROLE U_R ON U_R.ROLE_ID=R.ID INNER JOIN USER U ON U.ID=U_R.USER_ID WHERE U.EMAIL_ID=?1",nativeQuery=true)
	public List<String> getListOfUserPermission(String emailId);
}
