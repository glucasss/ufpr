package com.android.oscarapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="token")
public class Token {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtoken")
	private int idToken;
	
	@Column(name="token")
	private int token;
	
	@Column(name="dttoken")
	private Date dtToken;
	
	@Column(name="login")
	private String login;
	
	@Column(name="token_status")
	private String tokenStatus;

}
