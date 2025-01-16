/**
 * 포스트 수정
 */

document.addEventListener('DOMContentLoaded', () => {

    const form = document.querySelector("form#modifyForm");
    const title = document.querySelector('input#title');
    const content = document.querySelector('textarea#content');
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    btnUpdate.addEventListener('click', (e) => {
        if ( title.value === '' || content.value === '') {
            alert('제목과 내용을 먼저 입력하세요');
            return;
        }
        
        const result = confirm('업데이트 할까요?');
        if (result) {
            modifyForm.method = 'post'; //요청 방식을 post로 설정.
            modifyForm.action = 'modify'; //요청 주소를 localhost:8080/jsp2/post/modify
            modifyForm.submit(); //양식 데이터 제출
           
        }
    });


})