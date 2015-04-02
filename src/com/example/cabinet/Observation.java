package com.example.cabinet;


import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.cabinet.DisplayMessage;







import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Observation extends ListActivity implements OnItemClickListener , OnItemLongClickListener {
	
	private MySQLiteHelper datasource;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		datasource = new MySQLiteHelper(this);
		datasource.getWritableDatabase();

		String nom = datasource.get_nom();

 	List<String> myList = new ArrayList<String>(Arrays.asList(nom
				.split(":")));
myList.remove(myList.size() - 1) ;
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, myList);
		setListAdapter(adapter);

	final	ListView listView = getListView();

		adapter.notifyDataSetChanged()  ;
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
		
		
		
	
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position,
			long arg3) {

		String prenom = datasource.get_idprenom() ; 
		String nom = datasource.get_idnom() ; 
String [] NOM = nom.split(":")   ;
String []  PRENOM = prenom.split(":")   ;
enregistrement get   = datasource.getFiche(PRENOM[position],NOM[position]) ;
Intent intent = new Intent(getApplicationContext(), DisplayMessage.class) ;
intent.putExtra("sendinfo",get) ;
startActivity(intent); }

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		
		String prenom = datasource.get_idprenom() ; 
		String nom = datasource.get_idnom() ; 
String [] NOM = nom.split(":")   ;
String []  PRENOM = prenom.split(":")   ;
enregistrement get   = datasource.getFiche(PRENOM[position],NOM[position]) ;
Toast.makeText(Observation.this, "LA Fiche est effaceé avec succes", Toast.LENGTH_LONG).show(); 

datasource.deleteFiche(get);
finish();
startActivity(getIntent());


	return true;
	}
}