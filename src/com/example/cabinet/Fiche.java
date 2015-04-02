package com.example.cabinet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.cabinet.R;




import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Fiche extends Activity {

	Button save;
	public List<enregistrement> list;
	EditText nom, prenom, observation, phone;
	RadioGroup sexe;

	public RadioButton radioGenderButton;
	public DatePicker datepicker;
	public String val_nom, val_prenom, val_sexe, val_phone, val_observation;
	int jj, mm, yy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fiche);
		save = (Button) findViewById(R.id.Enregistre);
		nom = (EditText) findViewById(R.id.nom);
		prenom = (EditText) findViewById(R.id.prenom);
		phone = (EditText) findViewById(R.id.telephone);
		observation = (EditText) findViewById(R.id.Observation);
		sexe = (RadioGroup) findViewById(R.id.Sexe);
		datepicker = (DatePicker) findViewById(R.id.DateDeNaissance);
	 	 final MySQLiteHelper   datasource = new MySQLiteHelper(Fiche.this);
	 

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				val_nom = nom.getText().toString();
				val_prenom = prenom.getText().toString();
				val_phone = phone.getText().toString();
				val_observation =observation.getText().toString();
				int selectedId = sexe.getCheckedRadioButtonId();
				radioGenderButton = (RadioButton) findViewById(selectedId);
				val_sexe = radioGenderButton.getText().toString();
				jj = datepicker.getDayOfMonth();
				mm = datepicker.getMonth();
				yy = datepicker.getYear();
				String getnom = datasource.get_idprenom();
				final String[] NOM = getnom.split(":");

				String getprenom = datasource.get_idnom();
				final String[] PRENOM = getprenom.split(":");
				List<String> Listnom = new ArrayList<String>(Arrays.asList(NOM));
				List<String> Listprenom = new ArrayList<String>(Arrays.asList(PRENOM));
				if (val_nom.equals("") || val_prenom.equals("")
						|| val_phone.equals("") || val_sexe.equals("")) {
					nom.setError(getResources().getString(R.string.error));
					prenom.setError(getResources().getString(R.string.error));
					phone.setError(getResources().getString(R.string.error));

				} else {
					if (Listnom.contains(val_nom)
							&& Listprenom.contains(val_prenom)) {
						Toast.makeText(Fiche.this, "Cette Fiche existe! Changer votre nom et prenom ",
								Toast.LENGTH_SHORT).show();
				
					} else {	datasource.addFiche(new enregistrement(val_nom, val_prenom,
							val_sexe, val_phone, val_observation, jj, mm, yy));
					Toast.makeText(Fiche.this, "Fiche créé avec sucéés",
							Toast.LENGTH_SHORT).show();}
					

				};
				
			}

		});

	
}
}
