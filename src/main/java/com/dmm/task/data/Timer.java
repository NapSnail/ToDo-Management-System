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

	// その月のカレンダーの最初になる日
	public LocalDate callenderTopDay() {
		LocalDate gone;
		DayOfWeek whatTopDay = this.firstDay.getDayOfWeek();
		if (whatTopDay == DayOfWeek.SUNDAY) {
			gone = this.firstDay;
			return gone;
		} else {
			gone = this.firstDay.minusDays(whatTopDay.getValue());
			return gone;
		}
	}

	// その月のカレンダーの最後になる日
	public LocalDate callenderEndDay() {
		LocalDate will;
		DayOfWeek whatEndDay = this.lastDay.getDayOfWeek();
		if(whatEndDay == DayOfWeek.SATURDAY) {
			will = this.lastDay;
			return will;
		}else if(whatEndDay == DayOfWeek.SUNDAY) {
			will = this.lastDay.plusDays(6);
			return will;
		}else {
			will = this.lastDay.plusDays(6 - whatEndDay.getValue());
			return will;
		}
	}

	// タスクを日付にセットする
	public void setUpTasks(MultiValueMap<LocalDate, Tasks> tasks, List<Tasks> tasklist) {
		LocalDate gone = callenderTopDay();
		LocalDate will = callenderEndDay().plusDays(1);
		LocalDate insert = gone;

		while (true) {

			if (insert.equals(will)) {
				break;
			}

			tasks.add(insert, null);

			for (Tasks task : tasklist) {
				if (task.getDate().equals(insert.atStartOfDay())) {
					tasks.add(insert, task);
				}
			}

			insert = insert.plusDays(1);
		}
	}

	// matrix用リストを作成する
	public void listSet(List<List<LocalDate>> matrix){
		LocalDate gone = callenderTopDay();
		LocalDate will = callenderEndDay();
		LocalDate insert = gone;

		List<LocalDate> week1 = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			insert = callenderTopDay().plusDays(i);
			week1.add(insert);
		}
		matrix.add(week1);

		List<LocalDate> week2 = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			insert = insert.plusDays(1);
			week2.add(insert);
		}
		matrix.add(week2);

		List<LocalDate> week3 = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			insert = insert.plusDays(1);
			week3.add(insert);
		}
		matrix.add(week3);

		List<LocalDate> week4 = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			insert = insert.plusDays(1);
			week4.add(insert);
		}
		matrix.add(week4);

		if (insert.isBefore(will)) {
			List<LocalDate> week5 = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				insert = insert.plusDays(1);
				week5.add(insert);
			}
			matrix.add(week5);

			if (insert.isBefore(will)) {
				List<LocalDate> week6 = new ArrayList<>();
				for (int i = 0; i < 7; i++) {
					insert = insert.plusDays(1);
					week6.add(insert);
				}
				matrix.add(week6);
			}
		}
		
	};
}
