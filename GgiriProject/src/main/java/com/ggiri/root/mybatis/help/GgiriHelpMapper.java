package com.ggiri.root.mybatis.help;

import com.ggiri.root.help.dto.GgiriHelpDTO;
import java.util.List;

import org.springframework.ui.Model;

public interface GgiriHelpMapper {
	
	public int helpWrite(GgiriHelpDTO dto);
	
	public List<GgiriHelpDTO> HelpList();
	
	public GgiriHelpDTO getId(String userid);
	
	public GgiriHelpDTO helpView(int helpNo);
	
	public void modify(GgiriHelpDTO dto);
	
	public void helpDelete(int helpNo);
}