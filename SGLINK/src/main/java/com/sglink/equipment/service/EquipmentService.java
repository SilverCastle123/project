package com.sglink.equipment.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sglink.common.constant.Process;
import com.sglink.board.dto.NoticeBoardResponseDto;
import com.sglink.entity.Board;
import com.sglink.entity.Equipment;
import com.sglink.equipment.dto.EquipmentRequestDto;
import com.sglink.equipment.dto.EquipmentResponseDto;
import com.sglink.repository.EquipmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {
	private final EquipmentRepository equipmentRepository;
	
	public Equipment findByEquiId(String equiId) {
		return equipmentRepository.findOneByEquiId(equiId);
	}
	

	@Transactional
	public String save(EquipmentRequestDto equipmentRequestDto) {
		return equipmentRepository.save(equipmentRequestDto.toEntity()).getEquiId();
	}
	
	@Transactional(readOnly = true)
	public HashMap <String, Object> findAll(Integer page, Integer size){
		HashMap<String, Object> resultMap= new HashMap<String, Object>();	
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Equipment> list= equipmentRepository.findByProcess(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")),Process.APPROVE);
		resultMap.put("list", list.stream().map(EquipmentResponseDto::new).collect(Collectors.toList()));
		System.out.println(list.stream().map(EquipmentResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());	
		return resultMap;	
	}
	public Optional<Equipment> viewfindById(String id) {
		return equipmentRepository.findById(id);
	}
	public EquipmentResponseDto findById(String id) {
		return new EquipmentResponseDto(equipmentRepository.findById(id).get());
	}
	
}
