package com.blueland.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blueland.model.Member;
import com.blueland.model.Staff;
import com.blueland.model.User;
import com.blueland.service.MemberService;
import com.blueland.service.StaffService;
import com.blueland.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	

		MemberService memberService;

		public UserController(MemberService memberService) {
			this.memberService = memberService;
		}
		
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String homePage(){
        return "user/dashboard";
    }

    @RequestMapping(value = {"/services"}, method = RequestMethod.GET)
    public String services(){
        return "user/services";
    }
    
    

	
	@RequestMapping(value = {"/members"}, method = RequestMethod.GET)
	public String listMembers(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@RequestMapping(value = {"/members/new"}, method = RequestMethod.GET)
	public String createMemberForm(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "user/create_member";
	}
	
	 @RequestMapping(value = {"/members"}, method = RequestMethod.POST)
	public String saveMember(@ModelAttribute("member") Member member) {
		memberService.saveMember(member);
		return "redirect:/members";
	}
	
	 @RequestMapping(value = {"/members/edit/{id}"}, method = RequestMethod.GET)
	public String editMemberForm(@PathVariable Long id, Model model) {
		model.addAttribute("member", memberService.getMemberById(id));
		return "user/edit_member";
	}
	
	 @RequestMapping(value = {"/members/{id}"}, method = RequestMethod.POST)
	public String updateMember(@PathVariable Long id, 
			@ModelAttribute("member") Member member,
			Model model) {
		
		//get members from database by id
		Member existingMember = memberService.getMemberById(id);
		existingMember.setId(id);
		existingMember.setFirstName(member.getFirstName());
		existingMember.setLastName(member.getLastName());
		existingMember.setEmail(member.getEmail());
		existingMember.setAdmissionNumber(member.getAdmissionNumber());
		existingMember.setLevel(member.getLevel());
		existingMember.setPosition(member.getPosition());
		existingMember.setDepartment(member.getDepartment());
		existingMember.setFeeBal(member.getFeeBal());
		existingMember.setTotal(member.getTotal());
		existingMember.setPercentage(member.getPercentage());
		
		
		//save updated student object 
		memberService.updateMember(existingMember);
		return "redirect:/members";
	}
	
	// handler method to handle delete member request

	 @RequestMapping(value = {"/members/{id}"}, method = RequestMethod.GET)
		public String deleteMember(@PathVariable Long id) {
		memberService.deleteMemberById(id);
		return "redirect:/members";
	}
	
	 @RequestMapping(value = {"/page/{pageNo}"}, method = RequestMethod.GET)
	 public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
			 @RequestParam("sortField") String sortField,
			 @RequestParam("sortDir") String sortDir,
			 Model model) {
		 int pageSize = 8;
		 
		 Page<Member> page = memberService.findPaginated(pageNo, pageSize, sortField, sortDir);
		 List<Member> listMembers = page.getContent();
		 
	
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", page.getTotalPages());
		 model.addAttribute("totalItems", page.getTotalElements());
		 
		 model.addAttribute("sortField", sortField);
		 model.addAttribute("sortDirection", sortDir);
		 model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "des" : "asc");
		 
		 
		 model.addAttribute("listMembers", listMembers);
		 return "user/members";	 
		 
	 } 
	 
}
