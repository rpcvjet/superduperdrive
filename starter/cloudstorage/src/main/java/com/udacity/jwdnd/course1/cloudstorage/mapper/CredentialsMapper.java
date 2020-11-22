package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credentials> getUserCredentials(Integer userid);

    //get credentialbyid
    @Select("SELECT * FROM CREDENTIALS where credentialid = #{credentialid}")
    Integer findCredentialById(Integer credentials);

    @Insert("INSERT INTO CREDENTIALS(url, password, username, key, userid) VALUES( #{url}, #{password}, #{username}, #{key},  #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer addCredential(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET url = #{url}, password = #{password}, username = #{password}, key={key} WHERE credentialid = #{credentialid} ")
    void updateCredential(Credentials credentials);
}

