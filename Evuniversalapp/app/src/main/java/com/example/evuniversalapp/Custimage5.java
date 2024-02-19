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

public class Custimage5 extends ArrayAdapter<String>  {

	 private Activity context;       //for to get current activity context
	SharedPreferences sh;




	private String[] title;
	private String[] description;
	private String[] first_name;
	private String[] comments;

	private String[] image;



	 public Custimage5(Activity context, String[] title, String[] description, String[] first_name , String[] comments, String[] image ) {
	        //constructor of this class to get the values from main_activity_class

	        super(context, R.layout.cust_images, image);
	        this.context = context;

		    this.title = title;
		 	this.description = description;
		 this.first_name = first_name;
			 this.comments = comments;

		 this.image = image;



	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
			//override getView() method

			LayoutInflater inflater = context.getLayoutInflater();
			View listViewItem = inflater.inflate(R.layout.cust_images, null, true);
			//cust_list_view is xml file of layout created in step no.2

			ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
			TextView t1=(TextView)listViewItem.findViewById(R.id.textView3);

//			TextView t2=(TextView)listViewItem.findViewById(R.id.textView3);
			t1.setText("title : "+title[position]+"\ndescription : "+description[position]+"\nuser name:"+first_name[position]+"\ncomments:"+comments[position]);
//			t2.setText(caption[position]);
			sh=PreferenceManager.getDefaultSharedPreferences(getContext());

			String pth = "http://"+sh.getString("ip", "")+"/"+image[position];
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