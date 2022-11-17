package com.sglink.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sglink.dto.NoticeBoardRequestDto;
import com.sglink.entity.FileBoard;
import com.sglink.service.FileBoardService;
import com.sglink.service.MemberService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class FileBoardController {
	
	private final FileBoardService fileboardService;
	private final MemberService memberService;
	
	@GetMapping ("reference/#")
	public String getBoardListPage(Model model, @RequestParam(required = false, defaultValue="0")Integer page, @RequestParam(required = false, defaultValue= "10")Integer size)throws Exception{
		try {
			model.addAttribute("resultMap",fileboardService.findAll(page, size));
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
 
	return "/board/reference/#";
	
}

@GetMapping("/reference/view")
public String getBoardViewPage(@RequestParam("id")Long id ,Model model, NoticeBoardRequestDto boardRequestDto,Principal principal) throws Exception{
	try {
		if (boardRequestDto.getId() != null) {
			//게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
			FileBoard fileBoard = fileboardService.viewfindById(id).get();
			//게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
			String registerId = fileBoard.getMember().getUserId();
			//로그인시 로그인한 회원의 아이디를 가져옴
			String loginUserId = principal.getName();
			model.addAttribute("loginUserId", loginUserId);
			model.addAttribute("registerId", registerId);
			model.addAttribute("info", fileboardService.findById(boardRequestDto.getId()));
		}
	}catch(Exception e) {
		throw new Exception(e.getMessage());
	}
	
	return "/board/notice/view";
	
}

@PostMapping("/reference/write/action")
public String boardWriteAction(Model model, FileBoardRequestDto fileboardRequestDto) throws Exception{
	try {
		
		
		Long result = fileboardService.save(fileboardRequestDto);
		
		if (result<0) {
			throw new Exception("#Exception boardWriteAction!");
		}
	}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/notice/list";
	
	}

@PostMapping("/notice/view/action")
public String getBoardViewAction(Model model, NoticeBoardRequestDto boardRequestDto) throws Exception{

	try {
		int result = fileboardService.updateBoard(boardRequestDto);
		
		if (result<1) {
			throw new Exception("#Exception boardViewAction!");
		}
	}catch(Exception e) {
		throw new Exception(e.getMessage());
	}
	
	return "redirect:/boards/notice/list";
}

@GetMapping("/notice/view/delete")
public String BoardViewDeleteAction(Model model, @RequestParam()Long id) throws Exception{

	try {
		fileboardService.deleteById(id);
	}catch(Exception e) {
		throw new Exception(e.getMessage());
	}
	
	return "redirect:/boards/notice/list";
}

@GetMapping("/notice/delete")
public String BoardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception{
	
	try {
		boardService.deleteAll(deleteId);
	}catch(Exception e) {
		throw new Exception(e.getMessage());
	}
	return "redirect:/boards/notice/list";
}


}
