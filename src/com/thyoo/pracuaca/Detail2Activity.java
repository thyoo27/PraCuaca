package com.thyoo.pracuaca;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;

public class Detail2Activity extends ListActivity {

		TextView namaTxt;
		static final String URL = "http://data.bmkg.go.id/cuaca_jabodetabek_1.xml";
		static final String KEY_ITEM = "Row";
		static final String KEY_ID = "Daerah";
		static final String KEY_DAERAH = "Daerah";
		static final String KEY_PAGI = "Pagi";
		static final String KEY_SIANG = "Siang";
		static final String KEY_MALAM = "Malam";
		private ProgressDialog pDialog;
		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		namaTxt = (TextView)findViewById(R.id.profinsi);
		namaTxt.setText(getNamaProf());
		new getData().execute();
	}

	/*
	 * ambil nama profinsi dari homeActivity
	 */
	public String getNamaProf(){
		
		Intent i = getIntent();
		Bundle b = i.getExtras();
		String nama = b.getString("namaProf");
		
		return nama;
	}
	
	class getData extends AsyncTask<String, Void, String>{

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Detail2Activity.this);
            pDialog.setMessage("Mengunduh Data..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try{
				XMLParser parser = new XMLParser();
	    		String xml = parser.getXmlFromUrl(URL);
	    		Document doc = parser.getDomElement(xml);
	
	    		NodeList nl = doc.getElementsByTagName(KEY_ITEM);
	    		
	    		for (int i = 0; i < nl.getLength(); i++) {
	
	            	HashMap<String, String> map = new HashMap<String, String>();
	    			
	    			Element e = (Element) nl.item(i);
	    			
	    			map.put(KEY_ID, parser.getValue(e, KEY_ID));
	    			map.put(KEY_DAERAH, parser.getValue(e, KEY_DAERAH));
	    			map.put(KEY_PAGI, "Pagi : "+parser.getValue(e, KEY_PAGI));
	    			map.put(KEY_SIANG, "Siang : "+parser.getValue(e, KEY_SIANG));
	    			map.put(KEY_MALAM, "Malam : "+parser.getValue(e, KEY_MALAM));
	    			menuItems.add(map);
	    		}
			} catch (Exception e){
				Intent j = new Intent(getApplicationContext(),ErrorActivity.class);
				startActivity(j);
			}
			return null;
		}
		
		protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
        			
            		ListAdapter adapter = new CustomAdapter(Detail2Activity.this, menuItems,
            				R.layout.list_kab2_layout,
            				new String[] { KEY_DAERAH, KEY_PAGI, KEY_SIANG,  KEY_MALAM}, 
            				new int[] {R.id.daerah, R.id.pagi, R.id.siang, R.id.malam});

            		setListAdapter(adapter);
                }
            });
		
		}
	}
	
}
