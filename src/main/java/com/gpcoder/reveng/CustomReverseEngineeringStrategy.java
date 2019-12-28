package com.gpcoder.reveng;

import java.util.Arrays;
import java.util.List;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class CustomReverseEngineeringStrategy extends DelegatingReverseEngineeringStrategy {

	private static final List<String> EXCLUDE_COLUMNS = Arrays.asList(
		"created_by", 
		"created_at", 
		"modified_at", 
		"modified_by" 
	);

	public CustomReverseEngineeringStrategy(ReverseEngineeringStrategy delegate) {
		super(delegate);
	}

	@Override
	public boolean excludeTable(TableIdentifier table) {
		if (table.getName().startsWith("sys_")) {
			return true;
		}
		return super.excludeTable(table);
	}

	@Override
	public boolean excludeColumn(TableIdentifier identifier, String columnName) {
		return EXCLUDE_COLUMNS.contains(columnName);
	}

	@Override
	public String tableToClassName(TableIdentifier tableIdentifier) {
		final String defaultClassName = super.tableToClassName(tableIdentifier);
		return defaultClassName.replace("Gp", "");
	}
}
