package com.sglink.business.dto;

import java.util.List;

import com.sglink.entity.Business;
import com.sglink.entity.FileEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BusinessRequestDto {

	private String busiId;

	private String busiName;

	private String busiUniname;

	private String busiContent;
	
	private String busiRegister;
	
	private String busiTel;
	
	private List<FileEntity> busiImg;

	
	public Business toEntity() {
		return Business.builder()
				.busiId(busiId)
				.busiName(busiName)
				.busiUniname(busiUniname)
				.busiContent(busiContent)
				.busiRegister(busiRegister)
				.busiTel(busiTel)
				.busiImg(busiImg) 
				.build();
	}
	
}
