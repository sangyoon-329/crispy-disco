/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	/*1. 서버쪽으로 비동기 요청 전송
	2. 동적인 파일 목록을 순수 데이터로 수신
	3. option 태그 생성
	4. select 태그의 innerHTML로 설정.*/

	const select = document.querySelector('[name="video"]');
	select.addEventListener('change', (e)=>{
		let selected = e.target.value;
		resultArea.innerHTML = `
				<video src="../../03/media?${select.name}=${selected}" 
				autoplay controls></video>`	
	})
	
	const resolve = resp =>{
		if(resp.ok){
			return resp.json();
		}else{
			throw new Error(`응답 에러, 상태코드 ${resp.status}`);
		}
	}
	
	const resolveJson = (fileList)=>{
		let options = fileList.map( fn =>`<option>${fn}</option>`).join("\n");
		// 중괄호가 있을 때 function interface를 사용하면 return을 시켜줘야 함.
		// 람다식으로 중괄호를 지우면 return 필요 없음.
		select.innerHTML = options;
	}
	const reject = console.error;
	
	fetch("../../04/movieListData")
		.then(resolve)
		.then(resolveJson)
		.catch(reject)
		.finally(()=>console.log("비동기 요청 종료"));
})

