databaseChangeLog = {

	changeSet(author: "spullen (generated)", id: "1417203315850-1") {
		createTable(tableName: "activity") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "activity_pkey")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "activity_type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "NUMERIC(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "TIMESTAMP WITH TIME ZONE") {
				constraints(nullable: "false")
			}

			column(name: "duration", type: "NUMERIC(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "metric", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "notes", type: "VARCHAR(2000)")

			column(name: "start", type: "TIMESTAMP WITH TIME ZONE") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-2") {
		createTable(tableName: "goal") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "goal_pkey")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "accomplished", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "activity_type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "current_amount", type: "NUMERIC(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "date_accomplished", type: "TIMESTAMP WITH TIME ZONE")

			column(name: "date_created", type: "TIMESTAMP WITH TIME ZONE") {
				constraints(nullable: "false")
			}

			column(name: "metric", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "target_amount", type: "NUMERIC(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "target_date", type: "TIMESTAMP WITH TIME ZONE") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "VARCHAR(150)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-3") {
		createTable(tableName: "goal_activity") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "goal_activity_pkey")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "activity_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "goal_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-4") {
		createTable(tableName: "registration_code") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_code_pkey")
			}

			column(name: "date_created", type: "TIMESTAMP WITH TIME ZONE") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-5") {
		createTable(tableName: "role") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "role_pkey")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-6") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-7") {
		createTable(tableName: "users") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "users_pkey")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bool") {
				constraints(nullable: "false")
			}

			column(name: "preferred_distance_units", type: "VARCHAR(2)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-8") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_role_pkey", tableName: "user_role")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-9") {
		addUniqueConstraint(columnNames: "goal_id, activity_id", constraintName: "unique_activity_id", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "goal_activity")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-10") {
		addUniqueConstraint(columnNames: "authority", constraintName: "uk_irsamgnera6angm0prq1kemt2", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "role")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-11") {
		addUniqueConstraint(columnNames: "email", constraintName: "uk_6dotkott2kjsp8vw4d0m25fb7", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "users")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-12") {
		addUniqueConstraint(columnNames: "username", constraintName: "uk_r43af9ap4edm43mmtq01oddj6", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "users")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-19") {
		createIndex(indexName: "activitytypeindex", tableName: "goal", unique: "false") {
			column(name: "activity_type")
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-20") {
		createIndex(indexName: "metrictypeindex", tableName: "goal", unique: "false") {
			column(name: "metric")
		}
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-21") {
		createSequence(schemaName: "public", sequenceName: "hibernate_sequence")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "activity", baseTableSchemaName: "public", constraintName: "fk_diwfgyhcm1yt2d0o6jr9j3hrm", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "users", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-14") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "goal", baseTableSchemaName: "public", constraintName: "fk_7b7j83l6dquot72lsg25y8323", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "users", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-15") {
		addForeignKeyConstraint(baseColumnNames: "activity_id", baseTableName: "goal_activity", baseTableSchemaName: "public", constraintName: "fk_rgru80vtxg18jyywejqvxls4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "activity", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-16") {
		addForeignKeyConstraint(baseColumnNames: "goal_id", baseTableName: "goal_activity", baseTableSchemaName: "public", constraintName: "fk_f8tfjgmrq6msxjlxicjp2ktpr", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "goal", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-17") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", baseTableSchemaName: "public", constraintName: "fk_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
	}

	changeSet(author: "spullen (generated)", id: "1417203315850-18") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", baseTableSchemaName: "public", constraintName: "fk_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "users", referencedTableSchemaName: "public", referencesUniqueColumn: "false")
	}
}
