package com.modulopgave1.ui.statistics.migrations;

import com.modulopgave1.model.Migration;

import java.util.ArrayList;
import java.util.List;

public class MigrationsModel {
    private List<Migration> migrations = new ArrayList<Migration>();
    private Migration searchCriteria = new Migration();

    public List<Migration> getMigrations() { return migrations; }
    public void setMigrations(List<Migration> migrations) { this.migrations = migrations; }

    public Migration getSearchCriteria() { return searchCriteria; }
    public void setSearchCriteria(Migration searchCriteria) { this.searchCriteria = searchCriteria; }
}
