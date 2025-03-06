package com.itwill.springboot4.web;

import java.util.SortedMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.dto.CommentRegisterDto;
import com.itwill.springboot4.dto.CommentUpdateDto;
import com.itwill.springboot4.service.CommentService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller

@RequestMapping("/api/comment")
public class CommentController {
	private final CommentService commentService;
	@PreAuthorize("hasRole('USER')") 
	@GetMapping("/all/{postId}")
	public ResponseEntity<PagedModel<Comment>> getCommentList(@PathVariable Long postId, 
			@RequestParam(defaultValue = "0") int p){
		//최종 수정 시간의 내림차순으로 정렬된, 한 페이지에 출력할 댓글 목록을 가져옴.
		Page<Comment> comments=commentService.readByPostId(postId, Sort.by("modifiedTime").descending(), p);
		return ResponseEntity.ok(new PagedModel<>(comments));
	}
	@PreAuthorize("hasRole('USER')") 
	@PostMapping
	public ResponseEntity<Comment> postComment(@RequestBody CommentRegisterDto dto){
		Comment entity=commentService.create(dto);
		return ResponseEntity.ok(entity);
	}
	@PreAuthorize("hasRole('USER')") 
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteComment(@PathVariable Long id){
		commentService.delete(id);
		return ResponseEntity.ok(id);
	}
	@PreAuthorize("hasRole('USER')") 
	@PutMapping("/{id}")
	public ResponseEntity<Long> updateComment(@PathVariable Long id, @RequestBody CommentUpdateDto dto) {
		commentService.update(dto);
		return ResponseEntity.ok(id);
	}
}
