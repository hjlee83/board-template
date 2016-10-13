package com.example.repositories;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domains.Board;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board("제목1", "내용1");
		boardRepository.save(board);
		boardRepository.flush();
	}

	@Test
    public void testContexts() throws Exception {
        assertThat(boardRepository, is(notNullValue()));
    }
	
	@Test
	public void _1_createBoard() {

		Board newBoard = new Board("제목2", "내용2");

		boardRepository.save(newBoard);
		boardRepository.flush();
		assertThat(boardRepository.count(), is(2L));

	}

	@Test
	public void _2_readBoard() {

		Board findBoard = boardRepository.findOne(board.getId());
       assertThat(board.getId(), is(findBoard.getId()));
	}

	@Test
	public void updateBoard() {
		board.setTitle("변경된 제목");
		boardRepository.saveAndFlush(board);

		Board updateBoard = boardRepository.findOne(board.getId());
		assertThat(board.getTitle(), is(updateBoard.getTitle()));
	}

	@Test
	public void deleteBoard() {
		
		boardRepository.delete(board);
		boardRepository.flush();
 
		Board deleteBoard = boardRepository.findOne(board.getId());
		assertThat(deleteBoard, is(nullValue()));
	}
}
