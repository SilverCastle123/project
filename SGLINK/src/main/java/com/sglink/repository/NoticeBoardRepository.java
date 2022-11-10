package com.sglink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.dto.NoticeBoardRequestDto;
import com.sglink.entity.NoticeBoard;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long>{
//	String UPDATE_BOARD = "UPDATE notice_board"+
//			"SET TITLE = :#{#boardRequestDto.title}, "+
//			"CONTENT = :#{#boardRequestDto.content}, "+
//			"UPDATE_TIME = SYSDATE() "+
//			"WHERE ID = :#{#boardRequestDto.id} ";
	
	String UPDATE_BOARD = "UPDATE notice_board "
			+ "SET TITLE = :#{#boardRequestDto.title}, "
			+ "CONTENT = :#{#boardRequestDto.content}, "
			+ "REGISTER_ID = :#{#boardRequestDto.registerId}, "
			+ "UPDATE_TIME = SYSDATE() "
			+ "WHERE ID = :#{#boardRequestDto.id}";
	
	String UPDATE_BOARD_READ_CNT_INC = "UPDATE notice_board "
			+ "SET READ_CNT = READ_CNT + 1 "
			+ "WHERE ID = : id";
	
	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD, nativeQuery = true)
	public int updateBoard(@Param("boardRequestDto") NoticeBoardRequestDto boardRequestDto);
	
	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD_READ_CNT_INC, nativeQuery = true)
	public int updateBoardReadCntInc(@Param("id") Long id);
}
