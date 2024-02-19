package com.application.practiceVer2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.practiceVer2.dao.BoardDAO;
import com.application.practiceVer2.dto.BoardDTO;
import com.application.practiceVer2.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

     @Autowired
     private BoardService boardService;
     
     @GetMapping("/createBoard")
     public String createBoard() {
    	 return "board/createBoard";
     }
     
     @PostMapping("/createBoard")
     @ResponseBody
     public String createBoard(@ModelAttribute BoardDTO boardDTO) {
    	 
    	
    	 boardService.InsertBoard(boardDTO);
    	 
    	String jsScript="""
    			<script>
    			alert('게시글이 작성되었습니다');
    			location.href='/board/boardList';
    			</script>
    			""";
     
    	 return jsScript;
     }
     
     @GetMapping("/boardList")
     public String boardList(Model model) {
    	 
    	 model.addAttribute("boardList", boardService.getBoardList());
    	 
    	 return "board/boardList";
     }
     
     @GetMapping("/boardDetail")
     public String boardDetail(Model model,  @RequestParam("boardId") long boardId) {
    	 model.addAttribute("boardDTO",boardService.getBoardDetail(boardId));
    	 
    	 return "board/boardDetail";
     }
     
     @GetMapping("/authentication")
     public String authentication( Model model ,@RequestParam("boardId") long boardId ,@RequestParam("menu") String menu) {
    	 
    	 model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
    	 model.addAttribute("menu", menu);
    	 
    	 return "board/authentication";
     }
     @PostMapping("/authentication")
     @ResponseBody
     public String authentication(@ModelAttribute BoardDTO boardDTO, @RequestParam("menu") String menu) {
    	 
    	 
    	 String jsScript="";
    	 if(boardService.getAuthorized(boardDTO)) {
    		 //인증
    		 if(menu.equals("update")) {
    			 jsScript = "<select>";
    			 jsScript += "location.href='/board/updateBoard="+boardDTO.getBoardId()+"';";
    			 jsScript += "</script>";
    		 }else if (menu.equals("delete")) {
    			 jsScript = "<select>";
    			 jsScript += "location.href='/board/deleteBoard="+boardDTO.getBoardId()+"';";
    			 jsScript += "</script>";
			}
    	 }
    	 else {
    		 //인증 안됨
				jsScript = """
						<script>
						alert('패스워드를 재확인 해주세요!');
						history.go(-1);
						</script>
						""";

			}
			return jsScript;
    	
     }
     
     @GetMapping("/updateBoard")
     public String updateBoard(Model model, @RequestParam("boardId") long BoardId) {
    	
    //boardService.getBoardDetail(BoardId);
    	 model.addAttribute("boardDTO", boardService.getBoardDetail(BoardId));
    	 return "board/updateBoard";
     }
     
     @PostMapping("/updateBoard")
     public String updateBoard(@ModelAttribute BoardDTO boardDTO) {
    	 
    	 boardService.updateBoard(boardDTO);
    	 
    	 String jsScript="""
    	 		<script>
    			alert('게시글이 수정되었습니다');
    			location.href='/board/boardList';
    			</script>
    	 		""";
    	 return jsScript;
     }
     
     @GetMapping("/deleteBoard")
     public String deleteBoard( Model model, @RequestParam("boardId") long boardId ) {
    	model.addAttribute("boardId", boardId);
    	 //model.addAttribute(menu, menu)
    	 return "board/deleteBoard";
     }
     @PostMapping("/deleteBoard")
     public String deleteBoard(@RequestParam long boardId) {
    	 // 여기서부터 업데이트
    	 return"";
     }
     
     
   
     
     
     
     
     
     
}
