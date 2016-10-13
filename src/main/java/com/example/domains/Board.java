package com.example.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TBL_BOARD")
@Data
public class Board {
	
	public static final int MAX_LENGTH_TITLE = 100;
	public static final int MAX_LENGTH_CONTENT = 500;
	
	public Board(String title) {
		this.title = title;
	}

	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = MAX_LENGTH_TITLE)
	private String title;
	
	@Lob
	@Column(nullable = false, length = MAX_LENGTH_CONTENT)
	private String content;

}