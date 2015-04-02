package com.example.cabinet;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity{
	
	Button bouton1 , bouton2 , bouton3 , bouton4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bouton1 = (Button) findViewById(R.id.bouton1) ;
        bouton2 = (Button) findViewById(R.id.bouton2) ;
        bouton3 = (Button) findViewById(R.id.bouton3) ;
        bouton4 = (Button) findViewById(R.id.bouton4) ;
        
        bouton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Fiche.class) ;
				startActivity(intent) ;	
			}
			
		}) ;
        
        bouton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Calandrier.class);
				startActivity(intent) ;	
			}
		});
        
        bouton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Prendre_rendez_vous.class) ;
				startActivity(intent);	
			}
		});
        
        bouton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Observation.class) ;
				startActivity(intent) ;
			}
		});
        
        
        
        
        }
        
    }
    
        
        

    


    
    

    

