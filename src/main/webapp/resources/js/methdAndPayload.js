/**
 * 동기 요청 vs 비동기 요청 : lock 의 사용 여부만 다름.
 * 1. line : url, method
 * 2. header : header(n/v)...
 * 3. body : 클라이언트가 보낼 진짜 데이터(content-type 헤더에 따라 다른 형태)
 * 
 * fetch(url, {
 * 	 method : "post",
 * 	 headers : {
 * 		"accept" : "text/html",
 * 		"accept-language" : "ko-KR",
 * 		"content-type" : "application/x-www-form-urlencoded"
 * 	},
 * 	body : FormData(multipart/form-data), URLSearchParams(application/x-www-form-urlencoded)
 * 		 json 페이로드나 xml 페이로드를 전송할 때는 content-type 반드시 명시
 * })
 * 
 */
const defaultHeaders = {
	"accept": "text/html"
}
const options ={
	headers : defaultHeaders
}

function fnGet(form) {
	let url = form.action;
	let params = new URLSearchParams(new FormData(form));
	
	fetch(`${url}?${params}`, {
		method: "get",
		headers: defaultHeaders
	})
	.then(resp=>{
		return resp.text();
	}).then(html=>{
		console.log(html);
	})
}

async function fnPost(form) {
	let url = form.action;
	let params = new URLSearchParams(new FormData(form));
	
	let resp = await fetch(url, {
		method : "post",
		headers :{
			...defaultHeaders,
			"my-header" : "headerValue"	
		},
		body : params
	});
	let html = await resp.text();
	console.log(html);
}

function fnPut(form) {
	let url = form.action;
	let fd = new FormData(form); // FomrData : form의 모든 입력태그의 값을 이름과 값의(엔트리)로 관리하는 객체
	let obj = {p1:fd.get("p1"), p2:fd.get("p2")};
	fetch(url,{
		...options,
		headers : {
			...options.headers,
			"content-type" : "application/json"
		},
		method : "put",
		body : JSON.stringify(obj)
	})
}

async function fnDelete(form) {
	let url = form.action;
	
	let resp = await fetch(url, {
		...options,
		headers : {
			...options.headers,
			"accept" : "application/json"
		},
		method : "delete"
	});
	let {success, message:msg} = await resp.json(); 
	alert(msg);
}
document.addEventListener("DOMContentLoaded", () => {
	/*window['target-form']*/
	const targetForm = document.querySelector("#target-form")
	document.addEventListener('click', (e) => {
		let method = e.target.dataset.method;
		if (!method) return false;
		//alert(`이벤트 버블링 구조로 처리한 클릭, target : ${method}`);
		switch (method) {
			case "get":
				fnGet(targetForm);
				break;
			case "post":
				fnPost(targetForm);
				break;
			case "put":
				fnPut(targetForm);
				break;
			case "delete":
				fnDelete(targetForm);
				break;

			default:
		}
	});

	/*
	document.querySelectorAll("button[data-method]").forEach((b,i)=>{
		b.addEventListener('click', ()=>{
			b.dataset.method
			let method = b.dataset['method'];
			alert(method);
		});
	});
	*/
});