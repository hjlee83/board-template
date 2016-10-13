package com.example.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domains.Board;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void createBoard() {
		
		Board board = new Board("제목1", "내용1");
	
		boardRepository.save(board);
		
		assertThat(boardRepository.count(), is(1L));
		
	}
	
	@Test
	public void readBoard() {
		
		createBoard();
		
		Board board = boardRepository.findOne(1L);
		
		log.info(board.toString());
		assertThat(board.getId(), is(1L));
		
	}
	
	@Test
	public void updateBoard() {
		
		createBoard();
		
		Board board = boardRepository.findOne(1L);
		board.setTitle("변경된 제목");
		
		boardRepository.save(board); // 영속성으로 인해서 createBoard에서 저장을 안한다.
		
		Board updatedBoard = boardRepository.findOne(1L); // 영속성으로 인해서 데이터를 가져오지 않는다.
		
		log.info(updatedBoard.toString());
		assertThat(updatedBoard.getId(), is(1L));
	}
	
	@Test
	public void deleteBoard() {
		
		createBoard();
		
		Board board = boardRepository.findOne(1L);
		boardRepository.delete(board);
		
		assertThat(boardRepository.count(), is(0L));
	}
}
