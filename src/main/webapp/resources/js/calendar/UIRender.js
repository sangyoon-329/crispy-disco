/**
 * 
 */
import { InputComponent, SelectComponent } from './InputComponents.js';

async function fetchMetaDatas() {
	let resp = await fetch("../../calendar/uiMetaDatas", {
		headers: {
			"accept": "application/json"
		}
	})
	return resp.json();
}

function createForm({ months, locales, zones }) {
	let form = document.createElement("form");
	let { element: yearInput, setValue: setYear } = InputComponent({ name: "year", type: "number" });
	let { element: monthInput, setValue: setMonth } = SelectComponent({ name: "month", metaData: months });
	let { element: localeInput, setValue: setLocale } = SelectComponent({ name: "locale", metaData: locales });
	let { element: zoneInput, setValue: setZone } = SelectComponent({ name: "zone", metaData: zones });

	/*
	let  btn = document.createElement("button");
	btn.type="submit";
	btn.innerHTML = "전송";
	*/
	form.append(yearInput, monthInput, localeInput, zoneInput);
	initForm({ setYear, setMonth, setLocale, setZone });
	return form;
}

function createCalendarArea() {
	let calArea = document.createElement("div");
	calArea.id = "cal-area";
	return calArea;
}

function initForm({ setYear, setMonth, setLocale, setZone }) {
	let today = new Date();
	setYear(today.getFullYear());
	setMonth(today.getMonth() + 1);

}

document.addEventListener("DOMContentLoaded", async () => {
	let metaDatas = await fetchMetaDatas();
	console.log(metaDatas);
	let form = createForm(metaDatas);
	let calArea = createCalendarArea();

	form.addEventListener("submit", async (e) => {
		e.preventDefault();
		let formData = new FormData(form);
		let params = new URLSearchParams(formData);
		let url = "../../calendar";

		let resp = await fetch(`${url}?${params}`, {
			headers: {
				"accpet": "text/html"
			}
		});
		let calHtml = await resp.text();
		calArea.innerHTML = calHtml;
	});

	form.addEventListener("change", () => {
		// 최초의 달력 받아오기, form 의 submit 이벤트 발생.
		form.requestSubmit();
	});

	document.body.append(form, calArea);

	// 최초의 달력 받아오기, form 의 submit 이벤트 발생.
	form.requestSubmit();
});




