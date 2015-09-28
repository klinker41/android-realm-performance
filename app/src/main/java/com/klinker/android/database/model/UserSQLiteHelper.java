/*
 * Copyright (C) 2015 Jacob Klinker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klinker.android.database.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper for opening and interacting with a SQLite database
 */
public class UserSQLiteHelper extends SQLiteOpenHelper {

    /**
     * The database name.
     */
    public static final String TABLE_USER = "user";

    /**
     * The first name column.
     */
    public static final String COLUMN_FIRST_NAME = "first_name";

    /**
     * The last name column.
     */
    public static final String COLUMN_LAST_NAME = "last_name";

    /**
     * The age column.
     */
    public static final String COLUMN_AGE = "age";

    public static final String COLUMN_ID = "id";

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_USER
            + "(" + COLUMN_FIRST_NAME + " text not null, "
            + COLUMN_LAST_NAME + " text not null, "
            + COLUMN_AGE + " integer not null,"
            + COLUMN_ID + " integer primary key autoincrement);";

    /**
     * Construct a new database helper.
     * @param context the current application context.
     */
    public UserSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when a new database needs to be created.
     * @param db the database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    /**
     * Called when a database should be upgraded.
     * @param db the database.
     * @param oldVersion the old database version.
     * @param newVersion the new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

}
