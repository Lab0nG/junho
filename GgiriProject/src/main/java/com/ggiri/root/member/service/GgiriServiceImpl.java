package com.ggiri.root.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggiri.root.member.dto.GgiriMemberDTO;
import com.ggiri.root.mybatis.member.GgiriMemberMapper;

@Service
public class GgiriServiceImpl implements GgiriService{

	@Autowired
	private GgiriMemberMapper gmm;
	
	@Override
	public int register(GgiriMemberDTO member) {
		try {
			
			return gmm.register(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int idCheck(String id) {
		return gmm.idCheck(id);
	}
	
	@Override
	public int loginCheck(HttpServletRequest request) {
		GgiriMemberDTO dto = gmm.loginCheck(request.getParameter("id"));
		if(dto != null) {
			if(request.getParameter("pwd").equals(dto.getPwd())) {
				return 0;
			}
		}
		return 1;
	}
	
	@Override
	public int joinPhone(String userTel) {
		return gmm.joinPhone(userTel);
	}
	
	@Override
	public GgiriMemberDTO findEmail(String email) {
		return gmm.findEmail(email);
	}
	
}