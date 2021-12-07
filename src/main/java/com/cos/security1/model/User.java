package com.cos.security1.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class User {
	@Id  // primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;  //시퀀스, auto_increment
	private String username;   //아이디
	private String password;
	private String email;
	private String role; //Enum을 쓰는게 좋다. // Admin,User,Manager
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;	
}
