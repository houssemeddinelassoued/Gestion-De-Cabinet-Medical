package com.example.cabinet;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "Patients";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create table
		String CREATE_Patient_TABLE = "CREATE TABLE enregistrement ( "
				+ "val_nom TEXT, " + "val_prenom TEXT, " + "val_sexe TEXT, "
				+ "val_phone TEXT, " + "val_observation TEXT, " + "jj TEXT , "
				+ " mm TEXT , " + "yy TEXT  )";

		// create books table
		db.execSQL(CREATE_Patient_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older patient table if existed
		db.execSQL("DROP TABLE IF EXISTS books");

		// create fresh patient table
		this.onCreate(db);
	}

	// Patient table name
	private static final String TABLE_Patient = "enregistrement";

	// Books Table Columns names
	private static final String KEY_val_nom = "val_nom";
	private static final String KEY_val_prenom = "val_prenom";
	private static final String KEY_val_sexe = "val_sexe";
	private static final String KEY_val_phone = "val_phone ";
	private static final String KEY_val_observation = "val_observation ";
	private static final String KEY_jj = "jj";
	private static final String KEY_mm = "mm";
	private static final String KEY_yy = "yy";

	private static final String[] COLUMNS = { KEY_val_nom, KEY_val_prenom,
			KEY_val_sexe, KEY_val_phone, KEY_val_observation, KEY_jj, KEY_mm,
			KEY_yy };

	public void addFiche(enregistrement Enregistrement) {
		// for logging
		Log.d("addFiche", Enregistrement.toString());

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_val_nom, Enregistrement.getVal_nom());
		values.put(KEY_val_prenom, Enregistrement.getVal_prenom());
		values.put(KEY_val_sexe, Enregistrement.getVal_sexe());
		values.put(KEY_val_phone, Enregistrement.getVal_phone());
		values.put(KEY_val_observation, Enregistrement.getVal_observation());
		values.put(KEY_jj, Enregistrement.getJj());
		values.put(KEY_mm, Enregistrement.getMm());
		values.put(KEY_yy, Enregistrement.getYy());

		// 3. insert
		db.insert(TABLE_Patient, null, values);

		// 4. close
		db.close();
	}

	public enregistrement getFiche(String val_nom, String val_prenom) {

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		String where = "val_nom = ? 	and  val_prenom = ? ";
		String[] args = { val_nom, val_prenom };
		// 2. build query
		Cursor cursor = db.query(TABLE_Patient, // a. table
				COLUMNS, // b. column names
				where, args, null, null, null);

		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();

		// 4. build book object
		enregistrement Enregistrement = new enregistrement();

		Enregistrement.setVal_nom(cursor.getString(0));
		Enregistrement.setVal_prenom(cursor.getString(1));
		Enregistrement.setVal_sexe(cursor.getString(2));
		Enregistrement.setVal_phone(cursor.getString(3));
		Enregistrement.setVal_observation(cursor.getString(4));
		Enregistrement.setJj(Integer.parseInt(cursor.getString(5)));
		Enregistrement.setMm(Integer.parseInt(cursor.getString(6)));
		Enregistrement.setYy(Integer.parseInt(cursor.getString(7)));

		// log
		Log.d("getFiche(" + val_nom + val_prenom + ")",
				Enregistrement.toString());

		// 5. return book
		return Enregistrement;
	}

	public int updateFiche(enregistrement Enregistrement) {

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();

		values.put("val_nom", Enregistrement.getVal_nom());
		values.put("val_prenom", Enregistrement.getVal_prenom());
		values.put("val_sexe", Enregistrement.getVal_sexe());
		values.put("val_phone", Enregistrement.getVal_phone());
		values.put("val_observation", Enregistrement.getVal_observation());
		values.put("jj", Enregistrement.getJj());
		values.put("mm", Enregistrement.getMm());
		values.put("yy", Enregistrement.getYy());

		// 3. updating row
		int i = db.update(TABLE_Patient, // table
				values, // column/value
				KEY_val_nom + " = ?", // selections
				new String[] { String.valueOf(Enregistrement.getVal_nom()) });

		// 4. close
		db.close();

		return i;

	}

	public void deleteFiche(enregistrement Enregistrement) {

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		String nom = Enregistrement.getVal_nom();
		String prenom = Enregistrement.getVal_prenom();
		db.delete(TABLE_Patient, KEY_val_nom + " = ? AND " + KEY_val_prenom
				+ " = ?", new String[] { nom, prenom + "" });

		// args

		// 3. close
		db.close();

		// log
		Log.d("deleteBook", Enregistrement.toString());

	}

	public String get_nom() {

		String nom = "", prenom = "";

		// 1. build the query
		String query = "SELECT  * FROM  " + TABLE_Patient;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list

		if (cursor.moveToFirst()) {
			do {

				nom = cursor.getString(0) + " " + cursor.getString(1) + ":"
						+ nom + " " + prenom;

			} while (cursor.moveToNext());
		}

		return nom;
	}

	public String get_idprenom() {

		String prenom = "";

		// 1. build the query
		String query = "SELECT  * FROM  " + TABLE_Patient;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list

		if (cursor.moveToFirst()) {
			do {

				prenom = cursor.getString(0) + ":" + prenom;

			} while (cursor.moveToNext());

		}

		return prenom;
	}

	// /////

	public String get_idnom() {

		String nom = "";

		// 1. build the query
		String query = "SELECT  * FROM  " + TABLE_Patient;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list

		if (cursor.moveToFirst()) {
			do {

				nom = cursor.getString(1) + ":" + nom;

			} while (cursor.moveToNext());

		}

		return nom;
	}

}
