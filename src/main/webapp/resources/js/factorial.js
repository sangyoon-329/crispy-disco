/**
 * 
 */
document.addEventListener("DOMContentLoaded", ()=>{
    // const facForm = document.querySelector('#fac-form')
    const facForm = window['fac-form'];
    facForm.addEventListener('submit', async(e)=>{
        e.preventDefault();
        let form = e.target;
        let url = form.action;
        let fd = new FormData(form);
        let queryString = new URLSearchParams(fd).toString();
        console.log("queryString :", queryString);
        let resp = await fetch(`${url}?${queryString}`)
        if(resp.ok){
            let {result} = await resp.json();
            resultArea.innerHTML = result;
        }else{

        }
    })
});