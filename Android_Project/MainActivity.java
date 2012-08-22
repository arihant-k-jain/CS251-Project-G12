package com.example.bluetooth_project;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements  OnClickListener{

	Button b1;
	Button b2;
	Button b3;
	EditText et;
	final BluetoothAdapter blueadapt;
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    BroadcastReceiver receiver=new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String prev=blueadapt.EXTRA_PREVIOUS_STATE;
			String extra=blueadapt.EXTRA_STATE;
			int state = intent.getIntExtra(extra,-1);
			String toast="";
			switch(state)
			{
			case blueadapt.STATE_OFF:
				toast="Bluetooth is off";
				break;
			
			case blueadapt.STATE_ON:
				toast="Bluetooth is on";
				break;
				
				
			case blueadapt.STATE_TURNING_OFF:
				toast="Bluetooth is turning off";
				break;
				
			case blueadapt.STATE_TURNING_ON:
				toast="Bluetooth is turning on";
				break;
			
			
			}
			
		}
    	
    	
    };
    

void initialise()
{
	Button b1=(Button) findViewById(R.id.connect);
	Button b2=(Button) findViewById(R.id.disconnect);
	EditText et=(EditText) findViewById(R.id.mssg);
    Button b3=(Button) findViewById(R.id.check);
    b1.setVisibility(View.GONE);
    b2.setVisibility(View.GONE);
    et.setVisibility(View.GONE);
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId())
	{
	case R.id.check:
		blueadapt=BluetoothAdapter.getDefaultAdapter();
		b3.setVisibility(View.GONE);
		et.setVisibility(View.VISIBLE);
		
		if(blueadapt.isEnabled())
		{
			String name=blueadapt.getName();
			String address=blueadapt.getAddress();
			String put=name + " : " +address;
            et.setText(put);
            b2.setVisibility(View.VISIBLE);
		}
		
		else
			{et.setText("Bluetooth is not on");
			 b1.setVisibility(View.VISIBLE); 
			}
		
		break;
		
	case R.id.disconnect:
		
		break;
		
	case R.id.connect:
		String action=BluetoothAdapter.ACTION_STATE_CHANGED;
		String request=BluetoothAdapter.ACTION_REQUEST_ENABLE;
		IntentFilter x= new IntentFilter(action);
		startActivityForResult(new Intent(request),0);
		break;
	
	}
}
}

