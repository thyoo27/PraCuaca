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

public class DetailActivity extends ListActivity {

	TextView namaTxt;
	String URL = "";
	static final String KEY_ITEM = "Row";
	static final String KEY_ID = "Kota";
	static final String KEY_KOTA = "Kota";
	static final String KEY_CUACA = "Cuaca";
	static final String KEY_SUHUMIN = "SuhuMin";
	static final String KEY_SUHUMAX = "SuhuMax";
	static final String KEY_KELEMBABANMIN = "KelembapanMin";
	static final String KEY_KELEMBABANMAX = "KelembapanMax";
	static final String KEY_KECANGIN = "KecepatanAngin";
	static final String KEY_ARAHANGIN = "ArahAngin";
	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		namaTxt = (TextView)findViewById(R.id.profinsi);
		namaTxt.setText(getNamaProf());
		setUrlLink();
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
	
	/*
	 * set url link
	 */
	public void setUrlLink(){
		String prof = getNamaProf();
		if(prof.equals("Nangroe Aceh Darusalam")){
			URL="http://data.bmkg.go.id/propinsi_01_1.xml";
		}
		else if(prof.equals("Sumatera Utara")){
			URL="http://data.bmkg.go.id/propinsi_02_1.xml";
		}
		else if(prof.equals("Sumatera Barat")){
			URL="http://data.bmkg.go.id/propinsi_03_1.xml";
		}
		else if(prof.equals("Jambi")){
			URL="http://data.bmkg.go.id/propinsi_04_1.xml";
		}
		else if(prof.equals("Bengkulu")){
			URL="http://data.bmkg.go.id/propinsi_05_1.xml";
		}
		else if(prof.equals("Riau")){
			URL="http://data.bmkg.go.id/propinsi_06_1.xml";
		}
		else if(prof.equals("Kepulauan Riau")){
			URL="http://data.bmkg.go.id/propinsi_07_1.xml";
		}
		else if(prof.equals("Sumatera Selatan")){
			URL="http://data.bmkg.go.id/propinsi_08_1.xml";
		}
		else if(prof.equals("Bangka Belitung")){
			URL="http://data.bmkg.go.id/propinsi_09_1.xml";
		}
		else if(prof.equals("Lampung")){
			URL="http://data.bmkg.go.id/propinsi_10_1.xml";
		}
		else if(prof.equals("Banten")){
			URL="http://data.bmkg.go.id/propinsi_11_1.xml";
		}
		else if(prof.equals("Jawa Barat")){
			URL="http://data.bmkg.go.id/propinsi_13_1.xml";
		}
		else if(prof.equals("Jawa Tengah")){
			URL="http://data.bmkg.go.id/propinsi_14_1.xml";
		}
		else if(prof.equals("D.I. Yogyakarta")){
			URL="http://data.bmkg.go.id/propinsi_15_1.xml";
		}
		else if(prof.equals("Jawa Timur")){
			URL="http://data.bmkg.go.id/propinsi_16_1.xml";
		}
		else if(prof.equals("Bali")){
			URL="http://data.bmkg.go.id/propinsi_17_1.xml";
		}
		else if(prof.equals("Nusa Tenggara Barat")){
			URL="http://data.bmkg.go.id/propinsi_18_1.xml";
		}
		else if(prof.equals("Nusa Tenggara Timur")){
			URL="http://data.bmkg.go.id/propinsi_19_1.xml";
		}
		else if(prof.equals("Kalimantan Barat")){
			URL="http://data.bmkg.go.id/propinsi_20_1.xml";
		}
		else if(prof.equals("Kalimantan Tengah")){
			URL="http://data.bmkg.go.id/propinsi_21_1.xml";
		}
		else if(prof.equals("Kalimantan Selatan")){
			URL="http://data.bmkg.go.id/propinsi_22_1.xml";
		}
		else if(prof.equals("Kalimatan Timur")){
			URL="http://data.bmkg.go.id/propinsi_23_1.xml";
		}
		else if(prof.equals("Gorontalo")){
			URL="http://data.bmkg.go.id/propinsi_24_1.xml";
		}
		else if(prof.equals("Sulawesi Utara")){
			URL="http://data.bmkg.go.id/propinsi_25_1.xml";
		}
		else if(prof.equals("Sulawesi Tengah")){
			URL="http://data.bmkg.go.id/propinsi_26_1.xml";
		}
		else if(prof.equals("Sulawesi Tenggara")){
			URL="http://data.bmkg.go.id/propinsi_27_1.xml";
		}
		else if(prof.equals("Sulawesi Selatan")){
			URL="http://data.bmkg.go.id/propinsi_28_1.xml";
		}
		else if(prof.equals("Sulawesi Barat")){
			URL="http://data.bmkg.go.id/propinsi_29_1.xml";
		}
		else if(prof.equals("Maluku")){
			URL="http://data.bmkg.go.id/propinsi_30_1.xml";
		}
		else if(prof.equals("Maluku Utara")){
			URL="http://data.bmkg.go.id/propinsi_31_1.xml";
		}
		else if(prof.equals("Papua Barat")){
			URL="http://data.bmkg.go.id/propinsi_32_1.xml";
		}
		else if(prof.equals("Papua")){
			URL="http://data.bmkg.go.id/propinsi_33_1.xml";
		}
	}
	
	
	class getData extends AsyncTask<String, Void, String>{

		 @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DetailActivity.this);
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
	    			map.put(KEY_KOTA, parser.getValue(e, KEY_KOTA));
	    			map.put(KEY_CUACA, "Cuaca : "+parser.getValue(e, KEY_CUACA));
	    			map.put(KEY_SUHUMIN, "Suhu : "+parser.getValue(e, KEY_SUHUMIN)+" - "+parser.getValue(e, KEY_SUHUMAX));
	    			map.put(KEY_KELEMBABANMIN, "Kelembapan : "+parser.getValue(e, KEY_KELEMBABANMIN)+" - "+parser.getValue(e, KEY_KELEMBABANMAX));
	    			map.put(KEY_KECANGIN, "Kec. Angin : "+parser.getValue(e, KEY_KECANGIN));
	    			map.put(KEY_ARAHANGIN, "Arah Angin : "+parser.getValue(e, KEY_ARAHANGIN));
	    			menuItems.add(map);
	    		}
			}catch (Exception e){
				Intent j = new Intent(getApplicationContext(),ErrorActivity.class);
				startActivity(j);
			}
			return null;
		}
		
		protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
        			
            		ListAdapter adapter = new CustomAdapter(DetailActivity.this, menuItems,
            				R.layout.list_kab_layout,
            				new String[] { KEY_KOTA, KEY_CUACA, KEY_SUHUMIN,  KEY_KELEMBABANMIN,  KEY_KECANGIN, KEY_ARAHANGIN }, 
            				new int[] {R.id.kota, R.id.cuaca, R.id.suhu, R.id.kelembapan,R.id.kecangin,R.id.arahangin });

            		setListAdapter(adapter);
                }
            });
 
        }
 
		
	}
	
}
