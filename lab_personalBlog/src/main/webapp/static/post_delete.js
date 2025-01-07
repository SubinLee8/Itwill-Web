/**
 * 
 */

document.addEventListener('DOMContentLoaded',()=>{
    const btnDelete = document.querySelector('button#btnDelete');
    const inputId=document.querySelector('input#id');
    
    btnDelete.addEventListener('click',(e)=>{
        
        const result=confirm('정말 삭제할까요?');
        if(result){
            //location.href==`${postDeletePage}`;
            location.href=`delete?id=${inputId.value}`;
        }
    })
    
})