package com.application.practiceVer2.dao;

import java.util.List;

import com.application.practiceVer2.dto.BoardDTO;

public interface BoardDAO {

	public void insertBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);

	public void updateReadCnt(long boardId);

	public String getEncodedPasswd(String passwd);

	public void updateBoard(BoardDTO boardDTO);
	
	

}
