package com.sglink.company.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.entity.Company;
import com.sglink.entity.Member;
import com.sglink.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService{
	private final CompanyRepository companyRepository;
	

	public Company saveCompany(Company company) {
		validateDuplicateCompany(company);
		return companyRepository.save(company);
	}
	
	private void validateDuplicateCompany(Company company) {
		Company findCompany = companyRepository.findByComId(company.getComId());
		if (findCompany != null) {
			throw new IllegalStateException("이미 존재하는 기업고유값입니다."); // 이미 존재하는 기업의 경우 예외를 발생시킨다.
		}
	}
	
	public Company findByComId(String comId) {
		comUninameNullCheck(comId);
		return companyRepository.findByComId(comId);
	}
	
	private void comUninameNullCheck(String comId) {
		Company findCompany = companyRepository.findByComId(comId);
		
		if(findCompany== null) {
			throw new IllegalStateException("기업코드가 올바르지 않습니다");
		}
		
	}
}
