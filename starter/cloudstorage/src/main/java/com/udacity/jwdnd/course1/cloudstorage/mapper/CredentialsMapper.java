package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credentials> getUserCredentials(Integer userid);

    //get credentialbyid
    @Select("SELECT * FROM CREDENTIALS where credentialid = #{credentialid}")
    Integer findCredentialById(Integer credentials);

    @Insert("INSERT INTO CREDENTIALS(url, password, username, userid) VALUES( #{url}, #{password}, #{username}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer addCredential(Credentials credentials);
}
