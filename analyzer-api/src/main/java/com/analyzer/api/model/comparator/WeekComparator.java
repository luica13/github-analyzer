package com.analyzer.api.model.comparator;

import java.util.Comparator;
import java.util.Date;

import com.analyzer.api.util.TimelineUtils;

public class WeekComparator implements Comparator<Date> {

	@Override
	public int compare(Date o1, Date o2) {
		int result = TimelineUtils.getWeekOfYear(o1) - TimelineUtils.getWeekOfYear(o2);
		if (result == 0) {
			result = o1.compareTo(o2);
		}
		return result;
	}

}
