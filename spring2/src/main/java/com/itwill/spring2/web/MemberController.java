package com.itwill.spring2.web;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.itwill.spring2.dto.MemberSignInDto;
import com.itwill.spring2.dto.MemberSignUpDto;
import com.itwill.spring2.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class MemberController {
	//final 필드와 생성자를 사용한 의존성 주입.
	private final MemberService memberService;
	
	@GetMapping("/signup")
	public void signUp() {
		log.debug("signUp()");
	}
	
	//username 중복 체크
	//중복되지 않은 username이면 "Y", 중복된 유저네임이면 "N"
	@ResponseBody //@Controller의 컨트롤러 아래에서 @ResponseBody를 가진 메소드는 Rest 컨트롤러로 사용된다. 리턴값은 클라이언트에게 응답으로 간다.
	@GetMapping("/checkusername")
	public ResponseEntity<String> checkUsername(@RequestParam String username){
		log.debug("checkUsername(username={})",username);
		Boolean result=memberService.checkUsername(username);
		if(result) {
			return ResponseEntity.ok("N");
		}
		else return ResponseEntity.ok("Y");
	}
	
	//email 중복 체크
	@ResponseBody
	@GetMapping("/checkemail")
	public ResponseEntity<String> checkemail(@RequestParam String email){
		log.debug("checkUseremail(useremail={})",email);
		Boolean result=memberService.checkEmail(email);
		if(result) {
			return ResponseEntity.ok("N");
		}
		else return ResponseEntity.ok("Y");
	}
	
	@PostMapping("/signup")
	public String signUp(MemberSignUpDto dto){
		log.debug("createUser(user={})",dto);
		int result=memberService.createUser(dto);
		return "redirect:/user/signin";
	}
	
	@GetMapping("/signin")
	public void signIn() {
		log.debug("signIn()");
	}
	
	@PostMapping("/signin")
	public String signIn(MemberSignInDto dto, HttpSession session, @RequestParam(name="target", defaultValue ="") String target) throws UnsupportedEncodingException {
		log.debug("signInUser(userDto={})",dto);
		String targetPage=null;
		
		
		if(memberService.checkLogIn(dto)!=null) {
			//로그인 성공
			log.debug("로그인 성공!!!!!!!!!!!!!!");
			session.setAttribute("signedInUser", dto.getUsername());
			
			//로그인 이후 이동할 페이지
			//질의 문자열에 target이 없으면 홈페이지, target이 있으면 target페이지로 이동.
			targetPage=target.equals("") ? "/" : target;
			log.debug("targetPage={}",targetPage);
			
		}else {
			//로그인 실패
			targetPage="/user/signin?result=f&target="+URLEncoder.encode(target,"UTF-8");
		}
		
		return "redirect:"+targetPage;
	}
	
	
	@GetMapping("/signout")
	public String signOut(HttpSession session) {
		session.removeAttribute("signedInUser");
		return "redirect:/user/signin";
	}
}
