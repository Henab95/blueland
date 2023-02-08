package com.blueland.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.blueland.model.Staff;
import com.blueland.repository.StaffRepository;


@Service
public class StaffServiceImpl implements StaffService{

	
	private StaffRepository staffRepository;
	
	public StaffServiceImpl(StaffRepository staffRepository) {
		super();
		this.staffRepository = staffRepository;
	}

	@Override
	public List<Staff> getAllStaffs() {
		
		return staffRepository.findAll();
	}

	@Override
	public Staff saveStaff(Staff staff) {
		
		return staffRepository.save(staff);
	}

	@Override
	public Staff getStaffById(Long id) {
		return staffRepository.findById(id).get();
	}

	@Override
	public Staff updateStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public void deleteStaffById(Long id) {
	staffRepository.deleteById(id);
		
	}

	@Override
	public Page<Staff> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.staffRepository.findAll(pageable);
	}
	
	
	
	
}
