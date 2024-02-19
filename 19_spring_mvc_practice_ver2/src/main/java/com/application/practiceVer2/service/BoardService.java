package com.application.practiceVer2.service;

import java.util.List;

import com.application.practiceVer2.dto.BoardDTO;

public interface BoardService {

	 public void InsertBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);

	public boolean getAuthorized(BoardDTO boardDTO);

	public void updateBoard(BoardDTO boardDTO);

	

}
