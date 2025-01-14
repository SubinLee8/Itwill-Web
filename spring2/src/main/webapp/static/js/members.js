/**
 * username, email 중복 체크
 * signup.jsp에 연결
 */

document.addEventListener('DOMContentLoaded', () => {
    //username 중복 체크 결과를 저장할 변수
    //true: 회원가입 가능, false 이면 전송 버튼 비활성화
    let isUsernameChecked = false;

    //password를 입력했는지 여부를 저장할 변수. false이면 [작성완료] 버튼은 비활성화.
    let isPasswordChekcked = false;

    //email 중복 체크 결과를 저장할 변수.
    let isEmailChecked = false;


    const inputUsername = document.querySelector('input#username');
    const checkUsernameResult = document.querySelector('div#checkUsernameResult');
    const inputPassword = document.querySelector('input#password');
    const inputEmail = document.querySelector('input#email');
    const checkEmailResult = document.querySelector('div#checkEmailResult');
    const btnSignUp = document.querySelector('button#btnSignUp');
    const checkPasswordResult = document.querySelector('div#checkPasswordResult');

    //inputUsername 요소에 'change' 이벤트 리스너를 설정
    inputUsername.addEventListener('change', checkUsername);
    inputPassword.addEventListener('change',checkPassword);
    inputEmail.addEventListener('change',checkEmail);
    btnSignUp.addEventListener('click',createUser);

    /* ---------------------------------함수선언 --------------------------------------- */
    
    
    function checkEmail() {
        const email = inputEmail.value;
        if (email === '') {
            checkEmailResult.innerHTML = '이메일은 필수 입력 항목입니다';
            checkEmailResult.classList.add('text-danger');
            checkEmailResult.classList.remove('text-success');
            
            isEmailChecked = false;
            changeButtonState( );
            return;
        }   
            //이메일은 @가 들어가므로 encoding이 필요하다
            const uri=`./checkemail?email=${encodeURIComponent(email)}`;
            axios.get(uri).then(handleCheckEmailResp).catch((error)=>{console.log(error);})
    }
    
    function handleCheckEmailResp({data}){
        if(data==='Y'){
            checkEmailResult.innerHTML='사용가능한 이메일입니다';
            checkEmailResult.classList.add('text-success');
            checkEmailResult.classList.remove('text-danger');
            isEmailChecked=true;
            
        }else{
            checkEmailResult.innerHTML='이미 존재하는 이메일입니다';
            checkEmailResult.classList.add('text-danger');
            checkEmailResult.classList.remove('text-success');
            isEmailChecked=false;
            
        }
        changeButtonState();
    }

    function checkPassword() {
        if (inputPassword.value === '') {
            checkPasswordResult.innerHTML = '비밀번호는 필수입력 항목입니다';
            checkPasswordResult.classList.add('text-danger');
            checkPasswordResult.classList.remove('text-success');
            isPasswordChekcked=false;    
            
         }else{
            checkPasswordResult.innerHTML='사용 가능한 비밀번호';
            checkPasswordResult.classList.add('text-success');
            checkPasswordResult.classList.remove('text-danger'); 
            isPasswordChekcked=true;
           
         }
         changeButtonState();
    }


    function changeButtonState() {
        if (isUsernameChecked && isPasswordChekcked && isEmailChecked) {
            console.log('버튼 활성화');
            //버튼 비활성화 - class 속성에 'disabled' 제거
            btnSignUp.classList.remove('disabled');

        } else {
            console.log('버튼 비활성화');
            //버튼 비활성화 - class 속성에 'disabled'추가
            btnSignUp.classList.add('disabled');
        }
    }

    function checkUsername() {
        const username = inputUsername.value;
        if (username === '') {
            checkUsernameResult.innerHTML = '사용자 아이디는 필수 입력 항목입니다.';
            checkUsernameResult.classList.add('text-danger');
            checkUsernameResult.classList.remove('text-success');

            isUsernameChecked = false;
            changeButtonState();
            return;
        }

        // 아이디 중복 체크 REST API(요청 URI)
        const uri = `./checkusername?username=${username}`;
        axios
            .get(uri)
            .then(handleCheckUsernameResp)
            .catch((error) => console.log(error));
    }

    function handleCheckUsernameResp({ data }) {
        // const {data} = response; //-> 구조분해 할당(destructuring assignment)
        if (data === 'Y') {
            checkUsernameResult.innerHTML = '멋진 아이디입니다.';
            checkUsernameResult.classList.add('text-success');
            checkUsernameResult.classList.remove('text-danger');
            isUsernameChecked = true;
            
        } else {
            checkUsernameResult.innerHTML = '이미 존재하는 아이디입니다.';
            checkUsernameResult.classList.add('text-danger');
            checkUsernameResult.classList.remove('text-success');
            isUsernameChecked = false;
            
        }
        changeButtonState();
    }

})