package com.itwill.jsp1.listener;

import java.util.Enumeration;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class ListenerExample
 *
 */

//WAS가 시작될 때 리스너 객체를 생성하고 관리할 수 있도록 설정
//	(1) web.xml 파일에서 <listener> 태그로 설정.
//	(2) @WebListener 애너테이션으로 설정.
//	(주의) 한 개의 리스너 클래스는 web.xml 또는 애너테이션 중 하나의 방법만 사용 가능.
public class ListenerExample implements ServletRequestListener, ServletRequestAttributeListener{
	//ServletRequestListener: 요청(request) 객체가 생성/소멸 이벤트를 처리
	//ServletRequestAttributeListener: 요청 객체에서 attrivute가 추가/삭제/변경 이벤트를 처리.
	

    /**
     * Default constructor. 
     */
    public ListenerExample() {
        System.out.println("ListenerExample 생성자 호출");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre)  { 
         System.out.println("요청이 초기화됨.");
         ServletRequest req = sre.getServletRequest();
         if(req instanceof HttpServletRequest) { //type checking
        	 HttpServletRequest httpReq=(HttpServletRequest) req; //casting
        	 String uri=httpReq.getRequestURI();
        	 System.out.println("Request uri="+uri);
        	 System.out.println("request url="+httpReq.getRequestURL());
         }
        
         
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre)  { 
         System.out.println("요청이 소멸됨.");
    }
	
    /**
     * @see ServletRequestAttributedListener#attributeAdded()
     */
    //ServletRequestAttributedListener 인터페이스의 default 메소드를 재정의
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    	//상위 타입에서 구현된 메서드를 호출.
    	ServletRequestAttributeListener.super.attributeAdded(srae);
    	
    	//열거형 -> next, hasNext 등의 메소드를 가지고 있다.
    	Enumeration<String> attrNames = srae.getServletRequest().getAttributeNames();
    	while(attrNames.hasMoreElements()) {
    		System.out.println("request attr.name: "+attrNames.nextElement());
    	}
    }
    
    
}
