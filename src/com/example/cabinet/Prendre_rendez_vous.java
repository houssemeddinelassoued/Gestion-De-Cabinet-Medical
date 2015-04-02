package com.example.cabinet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Prendre_rendez_vous extends Activity {
	TextView nom, prenom;
	DatePicker datepicker2;
	TimePicker timepicker;
	Button save;
	private MySQLiteHelper datasource;

	String val_nom, val_prenom;
	int hr, mn, pm_am, jj, mm, yy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prendre_rendez_vous);
		nom = (TextView) findViewById(R.id.nom);
		prenom = (TextView) findViewById(R.id.prenom);
		datepicker2 = (DatePicker) findViewById(R.id.DateDuRendezVous);
		timepicker = (TimePicker) findViewById(R.id.HeureDuRendezVous);
		save = (Button) findViewById(R.id.Enregistrer);
		timepicker.setIs24HourView(true);

		datasource = new MySQLiteHelper(this);
		datasource.getWritableDatabase();

		final MySQLiteHelper2 db = new MySQLiteHelper2(Prendre_rendez_vous.this);

		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String getnom = datasource.get_idprenom();
				final String[] NOM = getnom.split(":");

				String getprenom = datasource.get_idnom();
				final String[] PRENOM = getprenom.split(":");
				val_nom = nom.getText().toString();
				val_prenom = prenom.getText().toString();
				jj = datepicker2.getDayOfMonth();
				mm = datepicker2.getMonth();
				yy = datepicker2.getYear();
				hr = timepicker.getCurrentHour();
				mn = timepicker.getCurrentMinute();

				
				
				List<String> Listnom = new ArrayList<String>(Arrays.asList(NOM));
				List<String> Listprenom = new ArrayList<String>(Arrays
						.asList(PRENOM));
				if ((mn<15) && (mn>0)){
					mn=15;
				}
				if ((mn>15) &&(mn<30))
				{
					mn=30 ;
				}
				if ((mn>30)&&(mn<45))
				{
					mn=45;
				}
				if (mn>45)
				{
					mn=00 ;
					hr=hr+1 ;
				}
				
				
				String j = String.valueOf(jj);
				String mo = String.valueOf(mm);
				String y = String.valueOf(yy);
				String h = String.valueOf(hr);
				String mi = String.valueOf(mn);
				String temps = db.temps(j, mo, y, h, mi);
				final String[] TEMPS = temps.split(":");
				String concat = j + mo + y + h + mi;
				
				List<String> Listtemps = new ArrayList<String>(Arrays
						.asList(TEMPS));
				
				if (val_nom.equals("") || val_prenom.equals("")) {
					nom.setError(getResources().getString(R.string.error));
					prenom.setError(getResources().getString(R.string.error));

				} else {

					if (Listnom.contains(val_nom)
							&& Listprenom.contains(val_prenom))
					{
						if ((hr > 18) || (hr < 8)) {
							Toast.makeText(Prendre_rendez_vous.this,
									"On ne travaille pas à ce temps là",
									Toast.LENGTH_LONG).show();

						} else {
							

							if (!Listtemps.contains(concat))

							{
								Toast.makeText(Prendre_rendez_vous.this,
										"RDV creé avec sucees",
										Toast.LENGTH_SHORT).show();
								db.addRendezVous(new enregistrement2(val_nom,
										val_prenom, jj, mm, yy, hr, mn));
							}

							else {
								Toast.makeText(
										Prendre_rendez_vous.this,
										"Ce temps est chargeé a uun autre patient , Changer !",
										Toast.LENGTH_LONG).show();
							}
						}

					} else

					{
						Toast.makeText(
								Prendre_rendez_vous.this,
								"Ce patient n'a pas une fiche ! vous devez la créer",
								Toast.LENGTH_SHORT).show();
					}

				}
				;
			}
		});

	}
}

