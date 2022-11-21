package com.sglink.company.dto;

import com.sglink.constant.Process;
import com.sglink.entity.Company;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CompanyResponseDto {
	private String comId;
	private String comUniname;
	private String comRepname;
	private String comTel;
	private String comSectors;
	private String comProduct;
	private Process process;
	
	
	public CompanyResponseDto(Company company) {
		this.comId = company.getComId();
		this.comUniname = company.getComUniname();
		this.comRepname = company.getComRepname();
		this.comTel = company.getComTel();
		this.comSectors = company.getComSectors();
		this.comProduct = company.getComProduct();
		this.process = company.getProcess();
	}

}