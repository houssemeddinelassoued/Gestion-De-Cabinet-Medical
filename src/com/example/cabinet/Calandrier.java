package com.example.cabinet;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class Calandrier extends ListActivity {
	CalendarView calendar ;
	private MySQLiteHelper2 datasource2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calandrier);
		
	calendar = (CalendarView) findViewById(R.id.calendarView1) ;
	datasource2 = new MySQLiteHelper2(this);
	datasource2.getWritableDatabase();
	calendar.setOnDateChangeListener(new OnDateChangeListener() {
		
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			String j= String.valueOf(dayOfMonth) ;
			String m= String.valueOf(month) ;
			String y= String.valueOf(year) ;
			
			String RDV  =  datasource2.getRdv(j,m, y); 
			if (RDV.equals(""))
			{			Toast.makeText(Calandrier.this, "Aucun RDV  dans ce jour  " , Toast.LENGTH_LONG).show() ; }
			else

			{
				Intent intent = new Intent(getApplicationContext(), ListeRDV.class) ;
				intent.putExtra("sendinfo",RDV) ;
                startActivity(intent);
					 
				}
		 
		}
	});
	
	
    }
	
	
}