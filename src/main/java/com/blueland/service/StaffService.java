package com.blueland.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.blueland.model.Staff;



public interface StaffService {
	
	
	List<Staff> getAllStaffs();
	
	Staff saveStaff(Staff staff);
	
	Staff getStaffById(Long id);
	
	Staff updateStaff(Staff staff);
	
	void deleteStaffById(Long id);
	
	Page<Staff> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

