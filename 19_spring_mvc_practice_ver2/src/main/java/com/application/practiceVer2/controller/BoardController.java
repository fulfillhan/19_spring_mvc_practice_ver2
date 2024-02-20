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

import com.application.practiceVer2.dto.BoardDTO;
import com.application.practiceVer2.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

     @Autowired
     private BoardService boardService;
     
  // Error resolving template [board/createBoard], 
 	//template might not exist or might not be accessible by any of the configured Template Resolvers] with root cause
     
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
    			 jsScript = "<script>";
    			 jsScript += "location.href='/board/updateBoard?boardId="+boardDTO.getBoardId()+"';";
    			 jsScript += "</script>";
    		 }else if (menu.equals("delete")) {
    			 jsScript = "<script>";
    			 jsScript += "location.href='/board/deleteBoard?boardId="+boardDTO.getBoardId()+"';";
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
     public String updateBoard(Model model, @RequestParam("boardId") long boardId) {
    	
    //boardService.getBoardDetail(BoardId);
    	 model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
    	 return "board/updateBoard";
     }
     
     @PostMapping("/updateBoard")
     @ResponseBody
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

     //오류 발생: Name for argument of type [long] not specified, and parameter name information not available via reflection. 
     //Ensure that the compiler uses the '-parameters' flag.
     //long 타입의 이름이 구체화 되지 않았다. 
     //해결: @RequestParam long boardId -> @RequestParam("boardId") long boardId
     
		@PostMapping("/deleteBoard")
		@ResponseBody
		public String deleteBoard(@RequestParam("boardId") long boardId) {
			boardService.deleteBoard(boardId);

			String jsScript = """
					<script>
					 alert('삭제되었습니다..');
					 location.href='/board/boardList';
					</script>
								""";
			return jsScript;
		}
     
     
   
     
     
     
     
     
     
}
