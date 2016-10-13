package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domains.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
