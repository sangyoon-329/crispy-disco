/**
 * 
 */
function FetchApi(baseUrl = "") {
	this.fetchJson = async () => {
		let resp = await fetch(`${baseUrl}/form`, {
			headers: {
				"accept": "application/json"
			}
		})
		if (resp.ok) {

			return resp.json();
		} else {
			throw new Error(resp.statusText);
		}
	}
	this.fetchHtml = async (url) => {
		let resp = await fetch(url);
		if (resp.ok) {

			return resp.text();
		} else {
			throw new Error(resp.statusText);
		}
	}
}


document.addEventListener('DOMContentLoaded', async () => {
	let fetchApi = new FetchApi("../../mbti");
	const select = document.querySelector('select[name="mbtiType"]');
	const form = select.form;

	let mbtiProps = await fetchApi.fetchJson();
	//	if (resp.ok) {
	// for 문에서 "in" 연산자는 ?? 반복의 대상이 객체이거나 배열일때
	// for 문에서 "of" 키워드는?? 반복의 대상이 배열일 때
	// Object 는 객체를 배열 비스무리한 것으로 바꿔주는 함수

	let array = Array.from(Object.entries(mbtiProps))
	let options = array.map(([n, v]) => `<option value=${n}>${v}</option>`).join('\n');

	select.innerHTML = options;



	form.addEventListener('submit', async (e) => {
		e.preventDefault();
		let params = new URLSearchParams(new FormData(form));
		params.set("layout", "none");

		let url = `${form.action}?${params}`
		let content = await fetchApi.fetchHtml(url);

		resultArea.replaceChildren("");
		resultArea.innerHTML = content;

	})

	select.addEventListener('change', (e) => {
		e.target.form.requestSubmit();
	})
})

