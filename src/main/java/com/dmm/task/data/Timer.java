package com.dmm.task.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.MultiValueMap;

import com.dmm.task.data.entity.Tasks;

import lombok.Data;

@Data
public class Timer {
	
	private LocalDate toDay = LocalDate.now();
	private LocalDate firstDay = toDay.withDayOfMonth(1);
	private LocalDate lastDay = firstDay.plusDays(toDay.lengthOfMonth() - 1);
	
	public Timer(LocalDate date) {
		this.toDay = date;
		this.firstDay = toDay.withDayOfMonth(1);
		this.lastDay = firstDay.plusDays(toDay.lengthOfMonth() - 1);
	}
	
	//その月のカレンダーの最初になる日
	public LocalDate callenderTopDay() {
		DayOfWeek whatTopDay = this.firstDay.getDayOfWeek();
		LocalDate gone = this.firstDay.minusDays(whatTopDay.getValue());
		return gone;
	}
	
	//その月のカレンダーの最後になる日
	public LocalDate callenderEndDay() {
		DayOfWeek whatToDay = this.lastDay.getDayOfWeek();
		LocalDate will = this.lastDay.plusDays(whatToDay.getValue() - 1);
		return will;
	}
	
	public void setUpTasks(MultiValueMap<LocalDate, Tasks> tasks, List<Tasks> tasklist) {
		LocalDate gone = callenderTopDay();
		LocalDate will = callenderEndDay().plusDays(1);
		LocalDate insert = gone;
		
		while(true) {
			
			if(insert.equals(will)) {
				break;
			}
			
			tasks.add(insert, null);
			
			for(Tasks task :tasklist) {
				if(task.getDate().equals(insert.atStartOfDay())) {
					tasks.add(insert, task);
				}
			}
			
			insert = insert.plusDays(1);
		}
	}
	
	
	//リスト詰め詰めループども。どうにかならんかったんか…( ﾟДﾟ)
	public List<LocalDate> week1() {
		List<LocalDate> weekDay = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			LocalDate insertDay = callenderTopDay().plusDays(i);
			weekDay.add(insertDay);
		}
		return weekDay;
	}
	
	public List<LocalDate> week2() {
		List<LocalDate> weekDay = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int roop2 = (7 * 1) + i;
			LocalDate insertDay = callenderTopDay().plusDays(roop2);
			weekDay.add(insertDay);
		}
		return weekDay;
	}
	
	public List<LocalDate> week3() {
		List<LocalDate> weekDay = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int roop3 = (7 * 2) + i;
			LocalDate insertDay = callenderTopDay().plusDays(roop3);
			weekDay.add(insertDay);
		}
		return weekDay;
	}
	
	public List<LocalDate> week4() {
		List<LocalDate> weekDay = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int roop4 = (7 * 3) + i;
			LocalDate insertDay = callenderTopDay().plusDays(roop4);
			weekDay.add(insertDay);
		}
		return weekDay;
	}
	
	public List<LocalDate> week5() {
		List<LocalDate> weekDay = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int roop5 = (7 * 4) + i;
			LocalDate insertDay = callenderTopDay().plusDays(roop5);
			weekDay.add(insertDay);
		}
		return weekDay;
	}
	
	public List<LocalDate> week6() {
		List<LocalDate> weekDay = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int roop6 = (7 * 5) + i;
			LocalDate insertDay = callenderTopDay().plusDays(roop6);
			weekDay.add(insertDay);
		}
		return weekDay;
	}

}
