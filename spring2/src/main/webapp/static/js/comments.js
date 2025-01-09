/**
 * 댓글 보기, 감추기, 댓글 CRUD 요청 AJAX
 * post/details.jsp 파일에 포함.
 */

document.addEventListener("DOMContentLoaded",()=> {
    //btnToggleComment 요소를 찾음.
    const btnToggleComment=document.querySelector("button#btnToggleComment");
    
    //div#collapseComments 요소를 부트스트랩의 collapse 객체로 생성.
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', { toggle: false });
    
    //btnToggleComment버튼에 클릭 이벤트 리스너 등록 
    btnToggleComment.addEventListener('click',()=>{
        bsCollapse.toggle();
        
        if(btnToggleComment.innerHTML==='댓글 보기'){
            btnToggleComment.innerHTML='댓글 감추기'
        }else {
            btnToggleComment.innerHTML='댓글 보기';
        }
        
        
        const btnRegisterComment=document.querySelector("button#btnRegisterComment");
        
        btnRegisterComment.addEventListener('click', registerComment)
    });
    
    
    /*--------------------------------------------------------------
    콜백 함수 선언 */
    
    function registerComment() {
        //input#id 요소의 값을 읽음, postId
        const postId=document.querySelector('input#id').value;
        
        //textarea#ctext 요소의 값을 읽음
        const ctext=document.querySelector('textarea#ctext').value;
        
        //input#username 요소의 값을 읽음
        const username=document.querySelector('input#username').value;
        
        //댓글 내용이 비어있는지 체크
        if(ctext==''){
            alert("댓글 내용을 반드시 입력하세요");
            return;
        }
        
        //Ajax 요청으로 보낼 데이터 객체
        //const data={ postId:postId, username:username, ctext:ctext };
        const data={ postId, username, ctext }; // <- 프로퍼티이름과 지역변수 이름이 같을 때만! 배열아님XXX 배열은 [ ]
        console.log(data);
        
        //서버로 POST 방식의 AJAX 요청을 보낸다.
        axios.post('../api/comment',data).then((response)=>{
            console.log(response);
            if(response.data===1){
                alert('댓글 1개 등록 성공');
                document.querySelector('textarea#ctext').value='';
            }
            
        }).catch((error)=>{
            console.log(error);
        }); //현재 주소인 /post/delete 현재 디렉토리 post에서 /api/comment로 바꾸기 위해서 ../를 넣는다.
        
    } 
})
