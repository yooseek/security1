package com.cos.security1.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id  // primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;  //시퀀스, auto_increment
	private String username;   //아이디
	private String password;
	private String email;
	private String role; //Enum을 쓰는게 좋다. // Admin,User,Manager

	private String provider;	// 어디서 정보 제공 받았는지 ex) google
	private String providerId;	// 제공받은 곳의 아이디가 먼지 ex) google 고유번호
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;

	@Builder
	public User(int id, String username, String password, String email, String role, String provider, String providerId, Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
}
