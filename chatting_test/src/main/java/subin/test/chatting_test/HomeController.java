package subin.test.chatting_test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;






@Controller
public class HomeController {

	@GetMapping("/")
	public String home(){
		System.out.println("홈컨트롤러");
		return "screen"; //뷰(jsp파일)의 이름
	}
	
	
}
