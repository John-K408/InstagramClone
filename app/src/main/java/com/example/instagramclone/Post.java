package com.example.instagramclone;

import android.media.Image;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED = "createdAt";
    public static final String PROFILE_IMAGE = "ProfileImage";
    public  int TIME_CREATED_MIL;
    public Post(){
        Date date = getDate(KEY_CREATED);

    }

    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

   public void setDescription(String description){
        put(KEY_DESCRIPTION,description);
   }

   public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
   }

   public void setImage(ParseFile parseFile) {
       put(KEY_IMAGE, parseFile);
   }
   public ParseFile getProfileImage(){
       return  getUser().getParseFile(PROFILE_IMAGE);
   }
   public void setProfileImage(ParseFile parseFile){
        put(PROFILE_IMAGE,parseFile);
   }

   public ParseUser getUser(){
        return getParseUser(KEY_USER);
   }

   public void setUser(ParseUser parseUser){
        put(KEY_USER,parseUser);
   }

}
