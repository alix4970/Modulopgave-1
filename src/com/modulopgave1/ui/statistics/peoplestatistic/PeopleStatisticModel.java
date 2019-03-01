package com.modulopgave1.ui.statistics.peoplestatistic;

import com.modulopgave1.model.PeopleStatistic;

import java.util.ArrayList;
import java.util.List;

public class PeopleStatisticModel {
    private List<PeopleStatistic> movements = new ArrayList<PeopleStatistic>();
    private PeopleStatistic searchCriteria = new PeopleStatistic();

    public List<PeopleStatistic> getMovements() { return movements; }
    public void setMovements(List<PeopleStatistic> movements) { this.movements = movements; }

    public PeopleStatistic getSearchCriteria() { return searchCriteria; }
    public void setSearchCriteria(PeopleStatistic searchCriteria) { this.searchCriteria = searchCriteria; }
}
