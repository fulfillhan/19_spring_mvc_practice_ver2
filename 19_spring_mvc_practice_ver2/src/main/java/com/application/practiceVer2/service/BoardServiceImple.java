package com.application.practiceVer2.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.practiceVer2.dao.BoardDAO;
import com.application.practiceVer2.dto.BoardDTO;

@Service
public class BoardServiceImple implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void InsertBoard(BoardDTO boardDTO) {
		
		//passwordEncoder.encode(boardDTO.getPasswd());
	 boardDTO.setPasswd(passwordEncoder.encode(boardDTO.getPasswd()));
	 boardDAO.insertBoard(boardDTO);
		
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		return boardDAO.getBoardList();
	}

	@Override
	public BoardDTO getBoardDetail(long boardId) {
		boardDAO.updateReadCnt(boardId);
		return boardDAO.getBoardDetail(boardId);
	}

	@Override
	public boolean getAuthorized(BoardDTO boardDTO) {
		boolean isCheck= false;
		
		// 패스워드만 가지고와서 비교하기
		String encodedPasswd= boardDAO.getEncodedPasswd(boardDTO.getBoardId());
		//passwordEncoder.matches(boardDTO.getPasswd(), encodedPasswd);
		
		if(passwordEncoder.matches(boardDTO.getPasswd(), encodedPasswd)) {
			isCheck=true;
		}
		return isCheck;
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		boardDAO.updateBoard(boardDTO);
		
	}

	@Override
	public void deleteBoard(long boardId) {
		boardDAO.deleteBoard(boardId);
	}

}
