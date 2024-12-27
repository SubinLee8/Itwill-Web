/**
 * 포스트 업데이트, 삭제 기능.
 */

document.addEventListener('DOMContentLoaded',()=>{
    //1. form#modifiyForm 요소를 찾음.
    const modifyForm=document.querySelector('form#modifyForm');
    
    //2. input#id 요소를 찾음.
    const inputId=document.querySelector('input#id');
    
    //3. input#title 요소를 찾음.
    const inputTitle=document.querySelector('input#title');
    
    //4. textarea#content 요소를 찾음.
    const content=document.querySelector('textarea#content');
    
    //5. 삭제,업데이트 버튼을 찾음.
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    //6. 삭제 버튼에 클릭 이벤트 리스너를 설정.
    btnDelete.addEventListener('click',(e)=>{
        const result=confirm('정말 삭제할까요?'); //확인=true, 취소=false 리턴.
        
        if(result){ //사용자가 [확인]을 선택했을 때
            //새로운 요청 주소로 요청을 보냄.
            location.href=`delete?id=${inputId.value}`;
            //원래 주소의 modify?id=${id} 주소에서 delete?id=${id}로 바꿔준다.
        }
    });
        
    btnUpdate.addEventListener('click',(e)=>{
        //제목, 내용에 입력된 값을 읽고 비어있는지를 체크한 후 비어있으면 alert함수 호출, 함수 종료
        if(inputTitle.value==''||content.value==''){
            alert('제목과 내용은 반드시 입력해야 합니다');
            return;
        }
        const result=confirm('업데이트 할까요?');
        if(confirm){
            modifyForm.method='post'; //요청 방식을 post로 설정.
            modifyForm.action='update'; //요청 주소를 localhost:8080/jsp2/post/update
            modifyForm.submit(); //양식 데이터 제출
        }
    });
    
           
   
    
    
});