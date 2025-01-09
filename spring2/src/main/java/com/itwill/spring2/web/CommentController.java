package com.itwill.spring2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.domain.Comment;
import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor //의존성주입
@RestController 
//-> 컨트롤러 메서드의 리턴 값이 뷰의 이 름이 아니라 클라이언트로 직접 응답되는 데이터.
@RequestMapping("/api/comment")
public class CommentController {
	
	//final필드와 (필수 아규먼트를 갖는) 생성자를 이용한 의존성 주입.
	private final CommentService commentService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CommentItemDto> getCommentById(@PathVariable Integer id) {
		log.debug("getCommentById(id={})",id);
		CommentItemDto comment = commentService.readById(id);
		
		return ResponseEntity.ofNullable(comment);
		//ResponseEntity<T>
		//서버가 클라이언트로 보내는 데이터와 응답코드를 함께 설정할 수 있는 객체.
		//comment 객체가 null인 것과 null이 아닌 것을 응답코드 세팅이 가능하다. .ok, .nullable로 !
	}
	
	@GetMapping("/all/{postId}")
	public ResponseEntity<List<CommentItemDto>> getAllCommentsByPostId(@PathVariable Integer postId){
		log.debug("getAllCommentsByPostId(postId={})",postId);
		
		List<CommentItemDto> list=commentService.readByPostId(postId);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<Integer> registerComment(@RequestBody CommentCreateDto dto) {
		log.debug("registerComment(dto={})",dto);
		
		int result=commentService.create(dto);
		
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteComment(@PathVariable Integer id){
		log.debug("deleteComment(id={})",id);
		
		int result=commentService.delete(id);
		
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Integer> updateComment(@PathVariable Integer id, @RequestBody CommentUpdateDto dto){
		log.debug("updateComment(id={},dto={})",id,dto);
		int result=commentService.update(dto);
		return ResponseEntity.ok(result);
	}
	
}
