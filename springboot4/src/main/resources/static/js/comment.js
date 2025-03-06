/**
 * /post/details.jps
 */

document.addEventListener('DOMContentLoaded', () => {
    //현재 댓글 페이지 번호 -> 댓글 더보기 버튼에서 이용하기 위해서 
    let currentPageNo = 0;
    let totalPageNo = 0;

    // div#collapseComments HTML 요소를 bootstrap Collapse 객체로 생성
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', { toggle: false });

    // button#btnToggle 버튼을 찾고, 클릭 이벤트 이벤트 리스너를 설정
    const btnToggle = document.querySelector('button#btnToggle');
    btnToggle.addEventListener('click', () => {
        bsCollapse.toggle(); // Collapse 객체를 보기/숨기기 토글.

        const dataToggle = btnToggle.getAttribute('data-toggle');
        if (dataToggle === 'collapse') {
            btnToggle.innerHTML = '댓글 숨기기';
            btnToggle.setAttribute('data-toggle', 'unfold');
            getAllComments();
        } else {
            btnToggle.innerHTML = '댓글 보기';
            btnToggle.setAttribute('data-toggle', 'collapse');
        }
    });

    const btnMore = document.querySelector('button#btnMore');
    btnMore.addEventListener('click', () => getAllComments(currentPageNo + 1));

    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    btnRegisterComment.addEventListener('click', registerComment);

    /*-------------------------------------------------------------------------------------------- */
    // 댓글 삭제 요청 처리 함수
    async function deleteComment(event) {
        //        console.log(event.target);
        const check = confirm('정말 삭제할까요?');
        if (!check) {
            return;
        }

        const id = event.target.getAttribute('data-id');
        const uri = `/api/comment/${id}`;
        try {
            const response = await axios.delete(uri);
            console.log(`deleted comment id = ${response.data}`);
            alert('댓글이 삭제됐습니다.');
            getAllComments(0);
        } catch (error) {
            console.log(error);
        }

    }


    async function updateComment(event) {
        const id = event.target.getAttribute('data-id');
        const textarea = document.querySelector(`textarea.commentText[data-id="${id}"]`);

        const text = textarea.value;

        if (text.trim() === '') {
            alert('댓글 내용은 반드시 입력해야 합니다.');
            return;
        }

        const check = confirm('수정된 내용으로 변경할까요?');
        if (!check) {
            return;
        }

        try {
            const response = await axios.put(`/api/comment/${id}`, { id, text });
            console.log(response);
            alert('댓글이 수정되었습니다.');
            getAllComments(0);
        }
        catch (error) {
            console.log(error);
        }
    }



    // 댓글 등록 함수
    async function registerComment() {
        // 댓글이 등록될 포스트 아이디
        const postId = document.querySelector('input#id').value;

        // 댓글 내용
        const text = document.querySelector('textarea#commentText').value;

        // 댓글 작성자
        const writer = document.querySelector('input#commentWriter').value;

        if (text.trim() === '') {
            alert('댓글 내용은 반드시 입력해야 합니다.');
            return;
        }

        // Ajax 요청에서 Request Body에 포함시켜서 전송할 데이터
        const reqBody = { postId, text, writer };

        // Ajax 요청을 보내고, 응답/에러 처리
        try {
            const { data } = await axios.post('/api/comment', reqBody);
            console.log(data);

            // 댓글 입력 textarea의 내용을 지움.
            document.querySelector('textarea#commentText').value = '';

            // 댓글 목록을 다시 그림.
            getAllComments(0);

        } catch (error) {
            console.log(error);
        }

    }


    async function getAllComments(pageNo = 0) {
        //디폴트: 페이지 번호 0
        const postId = document.querySelector('input#id').value;
        const url = `/api/comment/all/${postId}?p=${pageNo}`;
        try {
            //비동기 함수 호출
            const { data } = await axios.get(url); //응답이 올 때까지 기다린다. 
            console.log(data);
            currentPageNo = data.page.number;
            totalPageNo = data.page.totalPages;
            if (currentPageNo == totalPageNo - 1 || data.page.totalElements == 0) {
                btnMore.classList.add('d-none');
            }
            makeCommentElements(data);
        } catch (error) {
            //에러
            console.log(error);
        }

    }

    function makeCommentElements({ content, page }) {
        //로그인 사용자아이디 (네비게이션바에서 찾는다)
        const authUser = document.querySelector('span#authenticatedUser').innerText;
        console.log(authUser);

        //댓글 목록을 추가할 div요소
        const divComments = document.querySelector('div#divComments');

        //div에 삽입할 html 문자열
        let htmlStr = '';
        for (const comment of content) {
            htmlStr += `
            <div class="mt-2 card card-body">
              <div class="mt-2">
                <span class="fw-bold">${comment.writer}</span>
                <span class="text-secondary">${comment.modifiedTime}</span>
             </div>
            <div class="mt-2">
               <div class="mt-2">
                 <textarea class="commentText form-control" data-id="${comment.id}">${comment.text}</textarea>
               </div>`;

            if (authUser == comment.writer) {
                htmlStr += ` <div class="mt-2">
                                <button class="btnDelete btn btn-outline-danger" data-id="${comment.id}">삭제</button>
                                <button class="btnUpdate btn btn-outline-primary" data-id="${comment.id}">수정</button>
                              </div>`;
            }
            htmlStr += `
            </div>
            </div>
            `;
        }
        if (page.number == 0) {
            //페이지가 0인 댓글목록을 가져왔을 때
            divComments.innerHTML = htmlStr;
        } else {
            //현재 페이지가 0이 아닌 댓글목록을 가져왔을 때
            divComments.innerHTML += htmlStr;
        }

        // 댓글 [삭제], [수정] 버튼을 찾고, 클릭 이벤트 리스너를 설정.
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        /*
        for (const btn of btnDeletes) {
            btn.addEventListener('click', deleteComment);
        }
        */
        btnDeletes.forEach((btn) => btn.addEventListener('click', deleteComment));

        const btnUpdates = document.querySelectorAll('button.btnUpdate');
        btnUpdates.forEach((btn) => btn.addEventListener('click', updateComment));
    }











});