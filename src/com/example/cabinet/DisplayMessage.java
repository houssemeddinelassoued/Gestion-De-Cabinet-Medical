package com.example.cabinet;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class DisplayMessage extends Activity {
	TextView msg;
	Button button , SMS;
	EditText obs , sms;
	String nov_obs;
	private MySQLiteHelper datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		datasource = new MySQLiteHelper(this);
		datasource.getWritableDatabase();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dispaly_message);
		msg = (TextView) findViewById(R.id.info);
		button = (Button) findViewById(R.id.modifier);
		SMS= (Button) findViewById(R.id.msg) ;
		sms = (EditText) findViewById(R.id.sms) ;
		final enregistrement info = (enregistrement) getIntent()
				.getSerializableExtra("sendinfo");
		obs = (EditText) findViewById(R.id.nouv_obs);
		msg.setText("Nom et Prenom :  " +info.getVal_nom()+" "  + info.getVal_prenom() + "\n" +
				"Sexe : " + info.getVal_sexe() + " \n" + "Date de Naissance :" + info.getJj() + "/"
				+ info.getMm() + "/" + info.getYy() + "\n" + "Télephone :"
				+ info.getVal_phone() + "\n" + "Observation :" 
				+ info.getVal_observation());

		
	
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nov_obs =info.getVal_observation() + "\n" +  obs.getText().toString();
				enregistrement Enregistrement = new enregistrement(info
						.getVal_nom(), info.getVal_prenom(),
						info.getVal_sexe(), info.getVal_phone(), nov_obs, info
								.getJj(), info.getMm(), info.getYy());
				datasource.updateFiche(Enregistrement);
				Toast.makeText(DisplayMessage.this, "Nouvelle Observation Enregistree avec Suceés", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(DisplayMessage.this,Observation.class);
				startActivity(intent) ;
			
				
			}
			
			
			
		}  );
		SMS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
			      String phoneNo = info.val_phone.toString();
			      String message = sms.getText().toString() ;

			      try {
			         SmsManager smsManager = SmsManager.getDefault();
			         smsManager.sendTextMessage(phoneNo, null, message, null, null);
			         Toast.makeText(getApplicationContext(), "SMS sent.",
			         Toast.LENGTH_LONG).show();
			      } catch (Exception e) {
			         Toast.makeText(getApplicationContext(),
			         "SMS faild, please try again.",
			         Toast.LENGTH_LONG).show();
			         e.printStackTrace();
			      }
			      Intent intent = new Intent(DisplayMessage.this,Observation.class);
					startActivity(intent) ;

				
			}
		});
		
	}
	
	

}