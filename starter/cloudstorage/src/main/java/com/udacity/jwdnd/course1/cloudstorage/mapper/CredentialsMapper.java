package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credentials> getAllCredentials();

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credentials> getUserCredentials(Integer userid);

    @Insert("INSERT INTO CREDENTIALS(url, password, username, credentialid) VALUES( #{url}, #{password}, #{username}, #{credentialid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer addCredential(Credentials credentials);
}
