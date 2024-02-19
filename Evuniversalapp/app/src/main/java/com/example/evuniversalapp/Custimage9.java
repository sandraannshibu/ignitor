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

import com.squareup.picasso.Picasso;

public class Custimage9 extends ArrayAdapter<String>  {


	 private Activity context;       //for to get current activity context
	SharedPreferences sh;

	private String[] product_name;

	private String[] image;
	private String[] quantity;
	private String[] statu;
	private String[] total;
	private String[] date;



	 public Custimage9(Activity context, String[] pro, String[] img , String[] qty, String[] st, String[] tot, String[] dat ) {
	        //constructor of this class to get the values from main_activity_class

	        super(context, R.layout.cust_images, img);
	        this.context = context;

		    this.product_name = pro;

		 this.image = img;
			 this.quantity = qty;
		 this.statu = st;
		 this.total = tot;
		 this.date = dat;




	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	                 //override getView() method

	        LayoutInflater inflater = context.getLayoutInflater();
	        View listViewItem = inflater.inflate(R.layout.cust_images, null, true);
			//cust_list_view is xml file of layout created in step no.2

	        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
	        TextView t1=(TextView)listViewItem.findViewById(R.id.textView3);


			t1.setText("product name : "+product_name[position]+"\nquantity:"+quantity[position]+"\nstatus  : "+statu[position]+"\ntotal:"+total[position]+"\ndate:"+date[position]);
//			t2.setText(caption[position]);
	        sh=PreferenceManager.getDefaultSharedPreferences(getContext());
	        
	       String pth = "http://"+sh.getString("ip", "")+"/"+image[position];
	       pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();
	        
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