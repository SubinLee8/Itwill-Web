/**
 * post/modify.jsp 파일에 포함.
 * 포스트 업데이트 & 삭제
 */

document.addEventListener('DOMContentLoaded',()=>{
    const modifyForm=document.querySelector("form#modifyForm");
    const id=document.querySelector("input#id");
    const title=document.querySelector("input#title");
    const content=document.querySelector("textarea#content");
    const btnDelete=document.querySelector("button#btnDelete");
    const btnUpdate=document.querySelector("button#btnUpdate");
    
    btnDelete.addEventListener('click',()=>{
       const result= confirm("정말 삭제하시겠습니까?");
       if(result){
        location.href=`delete?id=${id.value}`;
       }
    })
    
    btnUpdate.addEventListener('click',()=>{
        //제목과 내용이 비어있는 지 확인.
        if (title.value===''||content.value===''){
            alert("제목과 내용은 반드시 입력해야 합니다.");
            return;
        }
        
        const result=confirm("업데이트 하시겠습니까?");
        if (result){
            //폼 제출 방식, 요청주소 설정하고 양식 데이터를 보냄.
            modifyForm.method="post";
            modifyForm.action='update';
            modifyForm.submit();
        }
    })
    
})