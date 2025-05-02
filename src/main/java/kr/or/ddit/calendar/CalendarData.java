package kr.or.ddit.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(of = {"locale", "targetMonth"}) 
public class CalendarData {
	
	public CalendarData(Locale locale, YearMonth targetMonth ,ZoneId zone) {
		super();
		this.locale = locale;
		this.targetMonth = targetMonth;
		
		WeekFields wf = WeekFields.of(locale);
		fdo = wf.getFirstDayOfWeek();
		
		firstDate = targetMonth.atDay(1);
		
		endDate = targetMonth.atEndOfMonth();
		
		int offset = firstDate.get(wf.dayOfWeek())-1;
		turnDate = firstDate.minusDays(offset);
		
		textStyle = TextStyle.FULL;
		
		today = LocalDate.now(zone);
	}
	
	private Locale locale; // 달력을 표현할 언어
	private YearMonth targetMonth; // 달력의 년도와 월
	@ToString.Exclude
	private DayOfWeek fdo; // 해당 언어에서 첫번째 요일
	private LocalDate firstDate; // 해당 월의 1일
	private LocalDate endDate; // 해당 월의 마지막날
	private LocalDate turnDate; // 달력에 출력할 첫번째 날
	private TextStyle textStyle;
	
	private LocalDate today;
}
