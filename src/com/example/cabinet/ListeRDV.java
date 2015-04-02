package com.example.cabinet;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListeRDV extends ListActivity {

@Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
       String info = (String) getIntent().getSerializableExtra("sendinfo");
   	List<String> myList = new ArrayList<String>(Arrays.asList(info
			.split(":")));
   	myList.remove(0) ;

	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, myList);

	setListAdapter(adapter);   
        
     
   

} }