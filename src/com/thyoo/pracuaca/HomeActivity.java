package com.thyoo.pracuaca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity {
	
	ListView ListViewSaya;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		ListViewSaya = (ListView)findViewById(R.id.listViewProf);
		String[] namaProf = getResources().getStringArray(R.array.daftar_prof);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_layout, R.id.profinsi, namaProf);
		ListViewSaya.setAdapter(ad);
				
		ListViewSaya.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String nama = parent.getItemAtPosition(position).toString();

				if(nama.equals("DKI Jakarta")){
					Intent i = new Intent(getApplicationContext(),Detail2Activity.class);
					i.putExtra("namaProf", nama);
					startActivity(i);
				}
				else {
					Intent j = new Intent(getApplicationContext(),DetailActivity.class);
					j.putExtra("namaProf", nama);
					startActivity(j);
				}
			}
		});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
