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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.sql.SQLException;

/**
 * The data source for interacting with the sqlite database
 */
public class UserDataSource {

    private SQLiteDatabase database;
    private UserSQLiteHelper dbHelper;

    /**
     * Constructs a new data source.
     * @param context the current application context.
     */
    public UserDataSource(Context context) {
        dbHelper = new UserSQLiteHelper(context);
    }

    /**
     * Opens the database.
     */
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Closes the database.
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Creates a user and inserts it into the database.
     */
    public void createUser() {
        ContentValues values = new ContentValues(3);
        values.put(UserSQLiteHelper.COLUMN_FIRST_NAME, "Jake");
        values.put(UserSQLiteHelper.COLUMN_LAST_NAME, "Klinker");
        values.put(UserSQLiteHelper.COLUMN_AGE, 21);
        database.insert(UserSQLiteHelper.TABLE_USER, null, values);
    }

    /**
     * Updates all users with a new name in the database
     */
    public void updateUser(int id) {
        ContentValues values = new ContentValues(3);
        values.put(UserSQLiteHelper.COLUMN_FIRST_NAME, "Luke");
        database.update(UserSQLiteHelper.TABLE_USER, values, "id=?", new String[]{String.valueOf(id)});
    }

    /**
     * Creates a user and inserts it into the database with raw sql.
     */
    public void createUserRaw() {
        database.beginTransaction();

        try {
            database.execSQL("INSERT INTO " + UserSQLiteHelper.TABLE_USER +
                    " (" + UserSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    UserSQLiteHelper.COLUMN_LAST_NAME + ", " + UserSQLiteHelper.COLUMN_AGE + ") " +
                    "VALUES (\"Jake\", \"Klinker\", 21);");
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    /**
     * Updates all users in the database with a new name with raw sql.
     */
    public void updateUserRaw(int id) {
        database.beginTransaction();

        try {
            database.execSQL("UPDATE " + UserSQLiteHelper.TABLE_USER + " SET first_name = 'Luke' WHERE id = " + id);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    /**
     * Creates a bulk insert for users.
     * @param number the number of users to create.
     */
    public void createUserBulk(int number) {
        database.beginTransaction();

        for (int i = 0; i < number; i++) {
            database.execSQL("INSERT INTO " + UserSQLiteHelper.TABLE_USER +
                    " (" + UserSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    UserSQLiteHelper.COLUMN_LAST_NAME + ", " + UserSQLiteHelper.COLUMN_AGE + ") " +
                    "VALUES (\"Jake\", \"Klinker\", 21);");
        }
        
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    /**
     * Creates a bulk updates for all users.
     */
    public void updateUsersBulk() {
        database.beginTransaction();

        try {
            database.execSQL("UPDATE " + UserSQLiteHelper.TABLE_USER + " SET first_name = 'Luke'");
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    /**
     * Creates a bulk updates for all users.
     */
    public void updateUsersBulkIndividual(int number) {
        database.beginTransaction();

        for (int i = 0; i < number; i++) {
            database.execSQL("UPDATE " + UserSQLiteHelper.TABLE_USER + " SET first_name = 'Luke' WHERE id = " + i);
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    /**
     * Find all columns in the database.
     * @return a cursor with all data in the user table.
     */
    public Cursor findAll() {
        return database.query(UserSQLiteHelper.TABLE_USER, UserSQLiteHelper.ALL_COLUMNS,
                null, null, null, null, null);
    }

    /**
     * Deletes the table completely.
     */
    public void deleteDatabase() {
        dbHelper.onUpgrade(database, 1, 2);
    }

}
