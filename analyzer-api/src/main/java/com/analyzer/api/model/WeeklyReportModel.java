package com.analyzer.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.analyzer.api.model.comparator.WeekComparator;
import com.analyzer.api.util.TimelineUtils;

public class WeeklyReportModel {

	Map<Date, List<ContributorData>> report;

	public WeeklyReportModel() {
		super();
		report = new TreeMap<>(new WeekComparator());
	}

	public Map<Date, List<ContributorData>> getReport() {
		return report;
	}

	public void setReport(Map<Date, List<ContributorData>> report) {
		this.report = report;
	}

	public void fill(List<CommitModel> commits) {

		int woy = -1;
		for (CommitModel data : commits) {

			Date date = data.getDate();
			int currentWoY = TimelineUtils.getWeekOfYear(date);
			Date firstWeekDay = TimelineUtils.getFirstDayOfWeek(date);

			if (woy != currentWoY) {
				woy = currentWoY;
				report.put(firstWeekDay, new ArrayList<>());
			}
			addContributorData(firstWeekDay, data.getEmail());
		}
	}

	public void addContributorData(Date date, String email) {
		List<ContributorData> data = report.get(date);
		Optional<ContributorData> current = data.stream().filter(e -> e.getUserName().equals(email)).findFirst();
		if (current.isPresent()) {
			ContributorData currentData = current.get();
			current.get().setCommitsCounts(currentData.getCommitsCounts() + 1);
		} else {
			data.add(new ContributorData(1, email));
		}
	}

}
