
package com.ggiri.root.complete.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ggiri.root.complete.dto.CompleteDTO;
import com.ggiri.root.complete.service.CompleteService;
import com.ggiri.root.member.dto.GgiriMemberDTO;
import com.ggiri.root.member.service.GgiriService;
import com.ggiri.root.session.login.GgiriMemberSession;

@Controller
@RequestMapping("ggiriComplete")
public class CompleteController implements GgiriMemberSession {

	@Autowired
	private CompleteService cs;
	
	@Autowired
	private GgiriService gs;
	
	@GetMapping("completeList")
	public String completeList(Model model) {
		cs.completeList(model);
		return "ggiriComplete/completeList";
	}
	
	@RequestMapping("completeWrite")
	public String comWrite(HttpSession session, Model model) {
		if(session.getAttribute(LOGIN) != null) {
			String id = (String)session.getAttribute(LOGIN);
			gs.ggiriMemberInfo(id, model);
			return "ggiriComplete/completeWrite";
		} else if(session.getAttribute("kakaoMember") != null){
			GgiriMemberDTO dto = (GgiriMemberDTO)session.getAttribute("kakaoMember");
			gs.ggiriSnsInfo(dto.getId(), model);
			return "ggiriComplete/completeWrite";
		} else if(session.getAttribute("naverMember") != null){
			GgiriMemberDTO dto = (GgiriMemberDTO)session.getAttribute("naverMember");
			gs.ggiriSnsInfo(dto.getId(), model);
			return "ggiriComplete/completeWrite";
		} else if(session.getAttribute("googleMember") != null){
			GgiriMemberDTO dto = (GgiriMemberDTO)session.getAttribute("googleMember");
			gs.ggiriSnsInfo(dto.getId(), model);
			return "ggiriComplete/completeWrite";
		}
		return "ggiriComplete/completeWrite";
	}
	
	@GetMapping("completeView")
	public String comView(@RequestParam("completeNum") int completeNum, Model model) {
		cs.comView(completeNum, model);
		return "ggiriComplete/completeView";
	}
	
	
	@PostMapping("comSave")
	public String comSave(CompleteDTO dto) {
		//System.out.println("date : " + dto.getComdate());
		int result = cs.insertCom(dto);
		if(result == 1) {
			return "redirect:completeSuccess";
		}
		return "redirect:completeFail";
	}
	
	@GetMapping("completeSuccess")
    public String completeSuccess() {
    	return "ggiriComplete/completeSuccess";
    }
    
    @GetMapping("completeFail")
    public String completeFail() {
    	return "ggiriComplete/completeFail";
    }
	
	
	@GetMapping("completeModify")
	public String modify(@RequestParam("completeNum") int completeNum, Model model) {
		cs.comView(completeNum, model);
		return "ggiriComplete/completeModify";
	}
	
	@PostMapping("modify")
	public String modify(CompleteDTO dto) {
		cs.modify(dto);
		return "ggiriComplete/comSuccess";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("completeNum") int completeNum) {
		cs.delete(completeNum);
		return "redirect:completeList";
	}
	
	
}
