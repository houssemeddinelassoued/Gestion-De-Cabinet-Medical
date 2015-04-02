package com.example.cabinet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper2 extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DB2";

	public MySQLiteHelper2(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_Consultation_TABLE = "CREATE TABLE rendez_vous ( "
				+ "val_nom TEXT, " + "val_prenom TEXT, " + "jj TEXT , "
				+ " mm TEXT , " + "yy TEXT ," + "hr TEXT , " + " mn TEXT )";

		db.execSQL(CREATE_Consultation_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS rendez_vous");

		this.onCreate(db);
	}

	private static final String TABLE_Rendez_vous = "rendez_vous";
	private static final String KEY_val_nom = "val_nom";
	private static final String KEY_val_prenom = "val_prenom";
	private static final String KEY_jj = "jj";
	private static final String KEY_mm = "mm";
	private static final String KEY_yy = "yy";
	private static final String KEY_hr = "hr";
	private static final String KEY_mn = "mn";

	private static final String[] COLUMNS = { KEY_val_nom, KEY_val_prenom,
			KEY_jj, KEY_mm, KEY_yy, KEY_hr, KEY_mn };

	public void addRendezVous(enregistrement2 Enregistrement2) {

		Log.d("addRendezVous", Enregistrement2.toString());

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_val_nom, Enregistrement2.getVal_nom());
		values.put(KEY_val_prenom, Enregistrement2.getVal_prenom());
		values.put(KEY_jj, Enregistrement2.getJj());
		values.put(KEY_mm, Enregistrement2.getMm());
		values.put(KEY_yy, Enregistrement2.getYy());
		values.put(KEY_hr, Enregistrement2.getHr());
		values.put(KEY_mn, Enregistrement2.getMn());
		db.insert(TABLE_Rendez_vous, null, values);
		db.close();
	}

	public String getRdv(String jj, String mm, String yy  ) {

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();

		String where = "jj = ? 	and  mm = ? and yy = ? ";
		String[] args = { jj, mm, yy };
		String RDV = "";
		// 2. build query
		Cursor cursor = db.query(TABLE_Rendez_vous, COLUMNS, where, args, null,
				null, null

		// e. group by
		// f. having
				); // h. limit

		// 3. if we got results get the first one
		if (cursor.moveToFirst()) {
			do {

				RDV = RDV + ":" + cursor.getString(0) + " "
						+ cursor.getString(1) + "  à " + cursor.getString(5) + " heures "
						+ cursor.getString(6) + " minutes ";

			} while (cursor.moveToNext());
		}

		// log
		Log.d("getRdv(" + jj + mm + yy + ")", RDV.toString());

		return RDV;
	}

	public String temps(String jj, String mm, String yy ,String hr ,String mn) {

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();

		String where = "jj = ? 	and  mm = ? and yy = ? and hr = ? and mn = ? ";
		String[] args = {jj,mm,yy,hr ,mn};
		String TEST = "";
		// 2. build query
		Cursor cursor = db.query(TABLE_Rendez_vous, COLUMNS, where, args, null,
				null, null

		// e. group by
		// f. having
				); // h. limit

		// 3. if we got results get the first one
		if (cursor.moveToFirst()) {
			do {

				TEST = TEST + ":" +cursor.getString(2) + cursor.getString(3)+ cursor.getString(4)+ cursor.getString(5) + cursor.getString(6);

			} while (cursor.moveToNext());
		}

		Log.d("getRdv(" + jj + mm + yy + hr + mn  +")", TEST.toString());

			return TEST;

} }