package com.bop.pos;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ItemListActivity extends ActivityGroup{
	
    private final static int FP = ViewGroup.LayoutParams.FILL_PARENT;
    private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	
    private Resources res;
    private LinearLayout baseLayout;
    private ListView list;
    private final String[] photolist
    = { "sample021"
      , "sample022"
      , "sample023"
      , "sample024"
      , "sample025"
      , "sample026"
      , "sample027"
      , "sample028"
      , "sample029"
      , "sample030"
      , "sample031"
      , "sample032"
      , "sample033"
      , "sample034"
      , "sample035"
      , "sample036"
      , "sample037"};
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);

		 setBaseLayout();
		 setBackButton();
		 setListView();
	}
	
	private void setBaseLayout(){
		 res = this.getResources();
	     baseLayout = new LinearLayout(this);
	     baseLayout.setOrientation(LinearLayout.VERTICAL);
	     baseLayout.setGravity(Gravity.TOP+Gravity.CENTER_HORIZONTAL);
	     setContentView(baseLayout);
	}
	
	private void setBackButton(){
		
	     Button backButton = new Button(this);
	     backButton.setText("back");
	     backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				goToPreviousViewInTabView();
			}
		});
	    baseLayout.addView(backButton);
	    backButton.setWidth(100);
	}
	
	private int getBackButtonWidth(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE); 
        Display display = wm.getDefaultDisplay();
        int displayWidth = display.getWidth();
        return displayWidth/10;
	}
	
	private void setListView(){
	     list = new ListView(this);
	     list.setBackgroundColor(Color.WHITE);
	     baseLayout.addView(list, new LinearLayout.LayoutParams(FP, FP));
	     
	     list.setAdapter(new ListAdapter(this)); 
	     
	     list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	 @Override            
	    	 public void onItemClick(AdapterView<?> parent
	    			 , View view, int position, long id) {
	    		 //goToNextViewInTabView();
	         }	 
	     }); 
	}
	
	
	private void goToPreviousViewInTabView(){
		 baseLayout.removeAllViews();
		 Intent intent = new Intent(ItemListActivity.this,MenuTabActivity.class);
		 Window nextActivity = getLocalActivityManager().startActivity("MenuTabActivity",intent);
		 baseLayout.addView(nextActivity.getDecorView());
	}
 
	
    public class ListAdapter extends BaseAdapter {
        //private Context contextInAdapter;
        private LayoutInflater inflater;
        
        public ListAdapter(Context context) {
            //contextInAdapter = context;
            inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        }
        
        @Override
        public int getCount() {
            return photolist.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           
            if(convertView == null){  
                convertView = inflater.inflate(R.layout.row, null);  
            }
            
            ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(getResourceID(photolist[position]));
            
            TextView textView = (TextView) convertView.findViewById(R.id.filename);
            textView.setPadding(10, 0, 0, 0);
            textView.setText(photolist[position]);
            
            return convertView; 
        }
    }
    
    private int getResourceID(String fileName) {
        int resID = res.getIdentifier(fileName
            , "drawable", "com.bop.pos");
        return resID;
    }


}
