/**
 * /post/modify.js 에 연결
 */

document.addEventListener('DOMContentLoaded', () => {
    //삭제 버튼을 찾는다.
    const deleteBtn = document.querySelector("button#deleteButton");
    deleteBtn.addEventListener('click', deletePost);
    const updateBtn = document.querySelector("button#updateButton");
    updateBtn.addEventListener('click', updatePost);

    /* ----------------------------------------콜백함수--------------------------------- */

    function deletePost() {
        const check = confirm("정말 삭제할까요?");
        if (check) {
            const postId = document.querySelector("#id").value;
            location.href = `/post/delete?id=${postId}`;
        }
    }

    function updatePost() {
        const updateForm=document.querySelector("form");
        const title = document.querySelector("input#title").value.trim();
        const content = document.querySelector("textarea#content").value.trim();
        //trim(): 문자열객체에서 호출, 공백을 없앰 "   abcv def   " => "abcv def"
        if (title == '' || content == '') {
            alert('내용입력');
            return;
        }
        const check = confirm("글을 수정할까요?");
        if (check) {
            updateForm.submit();
        }
    }

})