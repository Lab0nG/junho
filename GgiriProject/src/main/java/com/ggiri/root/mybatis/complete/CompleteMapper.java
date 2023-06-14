package com.ggiri.root.mybatis.complete;

import java.util.List;

import com.ggiri.root.complete.dto.CompleteDTO;

public interface CompleteMapper {

	public List<CompleteDTO> completeList();
	
	public CompleteDTO comView(int completeNum);
	
	public void insertCom(CompleteDTO dto);
	
	public void comHit(int completeNum);
	
	public void modify(CompleteDTO dto);
	
	public void delete(int completeNum);
	
	//관리자 페이지
	public List<CompleteDTO> adminCompleteList();
	
}
