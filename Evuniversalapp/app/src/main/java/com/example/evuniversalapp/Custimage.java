package com.example.evuniversalapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Custimage extends ArrayAdapter<String>  {

	 private Activity context;       //for to get current activity context
	SharedPreferences sh;

	private String[] product_name;
	private String[] firstname;
	private String[] images;
	private String[] rate;


	
	 public Custimage(Activity context, String[] pro, String[] fname, String[] images , String[] rat ) {
	        //constructor of this class to get the values from main_activity_class

	        super(context, R.layout.cust_images, images);
	        this.context = context;

		    this.product_name = pro;
		 	this.firstname = fname;
		 this.images = images;
			 this.rate = rat;



	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View listViewItem = inflater.inflate(R.layout.cust_images, null, true);
			//cust_list_view is xml file of layout created in step no.2

			ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
			TextView t1=(TextView)listViewItem.findViewById(R.id.textView3);

//			TextView t2=(TextView)listViewItem.findViewById(R.id.textView3);
			t1.setText("product_name : "+product_name[position]+"\nfirstname : "+firstname[position]+"\nrate:"+rate[position]);
//			t2.setText(caption[position]);
			sh=PreferenceManager.getDefaultSharedPreferences(getContext());

			String pth = "http://"+sh.getString("ip", "")+"/"+images[position];
			pth = pth.replace("~", "");
			Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

			Log.d("-------------", pth);
			Picasso.with(context)
					.load(pth)
					.placeholder(R.drawable.ic_launcher_background)
					.error(R.drawable.ic_launcher_background).into(im);

			return  listViewItem;
		}

	private TextView setText(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}