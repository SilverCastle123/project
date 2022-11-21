package com.sglink.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.sglink.company.dto.CompanyRequestDto;
import com.sglink.common.constant.Process;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Company extends BaseTimeEntity{
	@Id
	private String comId;
	@Column(unique = true)
	private String comUniname;
	private String comRepname;
	@Column(unique = true)
	private String comTel;
	private String comSectors;
	private String comProduct;
	@Enumerated(EnumType.STRING)
	private Process process;
	
	public static Company createCompay(CompanyRequestDto companyRequestDto) {
		Company company = new Company();
		company.setComId(companyRequestDto.getComId());
		company.setComUniname(companyRequestDto.getComUniname());
		company.setComRepname(companyRequestDto.getComRepname());
		company.setComTel(companyRequestDto.getComTel());
		company.setComSectors(companyRequestDto.getComSectors());
		company.setComProduct(companyRequestDto.getComProduct());
		company.setProcess(Process.UNAPPROVE);
		return company;
	}

}
