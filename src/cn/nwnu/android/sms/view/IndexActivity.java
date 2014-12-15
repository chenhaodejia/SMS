package cn.nwnu.android.sms.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nwnu.android.sms.R;
import cn.nwnu.android.sms.SMSEX;
import cn.nwnu.android.sms.database.SMSINFODao;

import cn.nwnu.android.sms.ReceiverType;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class IndexActivity extends Activity {
	
	private final String TAG = "IndexActivity";
	
	private Gallery mGallery;
	private ListView mListView;
	private List<Map<String, Object>> list = null;
	private int []galleryRes = null;
	private int []listViewRes = null;
	
	private Button set01;
	private Button set02;
	private Button set03;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
         
        setContentView(R.layout.index);
        
        initRes();
        findViews();
        setValues();
        setListeners();
        
        set01.setOnClickListener(new OnClickListener() {
     		
     		public void onClick(View v) {
     			// TODO Auto-generated method stub
     			Log.i("smstoast", "set smstoast");
     			if(set01.getText().equals(getResources().getString(R.string.start))){
    	 			stopService(new Intent("cn.nwnu.android.sms.MSG_SERVICE"));
    	 			Intent service = new Intent("cn.nwnu.android.sms.MSG_SERVICE");
    	 			Bundle mBundle = new Bundle();  
    		        mBundle.putSerializable("TYPE",ReceiverType.Standard);
    		        service.putExtras(mBundle);
    	 			startService(service);
    	 			set01.setText(R.string.end);
     			}else{
     				stopService(new Intent("cn.nwnu.android.sms.MSG_SERVICE"));
     				set01.setText(R.string.start);
     				set02.setText(R.string.start);
     				set03.setText(R.string.start);
     			}
     		}
            });
            
        
        set02.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(set02.getText().equals(getResources().getString(R.string.start))){
					Log.i("smstoast", "set smstoast");
		 			stopService(new Intent("cn.nwnu.android.sms.MSG_SERVICE"));
		 			Intent service = new Intent("cn.nwnu.android.sms.MSG_SERVICE");
		 			Bundle mBundle = new Bundle();  
			        mBundle.putSerializable("TYPE",ReceiverType.Quiet);
			        service.putExtras(mBundle);
		 			startService(service);
		 			set01.setText(R.string.end);
		 			set02.setText(R.string.end);
		 			set03.setText(R.string.start);
				}else{
					stopService(new Intent("cn.nwnu.android.sms.MSG_SERVICE"));
					set01.setText(R.string.end);
					set02.setText(R.string.start);
	 				Intent service = new Intent("cn.nwnu.android.sms.MSG_SERVICE");
		 			Bundle mBundle = new Bundle();  
			        mBundle.putSerializable("TYPE",ReceiverType.Standard);
			        service.putExtras(mBundle);
		 			startService(service);
				}
			}
		});
        
      
        set03.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(set03.getText().equals(getResources().getString(R.string.start))){
					Log.i("smstoast", "set smstoast");
		 			stopService(new Intent("cn.nwnu.android.sms.MSG_SERVICE"));
		 			Intent service = new Intent("cn.nwnu.android.sms.MSG_SERVICE");
		 			Bundle mBundle = new Bundle();  
			        mBundle.putSerializable("TYPE",ReceiverType.Simple);
			        service.putExtras(mBundle);
		 			startService(service);
		 			set01.setText(R.string.end);
		 			set02.setText(R.string.start);
		 			set03.setText(R.string.end);
				}else{
					stopService(new Intent("cn.nwnu.android.sms.MSG_SERVICE"));
					set01.setText(R.string.end);
					set03.setText(R.string.start);
	 				Intent service = new Intent("cn.nwnu.android.sms.MSG_SERVICE");
		 			Bundle mBundle = new Bundle();  
			        mBundle.putSerializable("TYPE",ReceiverType.Standard);
			        service.putExtras(mBundle);
		 			startService(service);
				}
			}
		});
                   
        
    }
    
    
    private void findViews() {
    	mGallery = (Gallery)this.findViewById(R.id.gallery);
//    	mListView = (ListView)this.findViewById(R.id.listview);
 
    	set01=(Button)findViewById(R.id.btnStart_1);
    	set02=(Button)findViewById(R.id.btnStart_2);
    	set03=(Button)findViewById(R.id.btnStart_3);
    }
    
    private void setValues() {
    	mGallery.setAdapter(new GalleryAdapter(this));
    	mGallery.setSelection(1);
   	
    }
    
    private void setListeners() {
   
    }
    
    private void initRes() {
    	galleryRes = new int[5];
    	galleryRes[0] = R.drawable.gallery1;
    	galleryRes[1] = R.drawable.gallery2;
    	galleryRes[2] = R.drawable.gallery3;
    	galleryRes[3] = R.drawable.gallery4;
    	galleryRes[4] = R.drawable.gallery5;
   
    }
   
    
    private class GalleryAdapter extends BaseAdapter {

    	private Context mContext;
    	
    	public GalleryAdapter(Context ctx) {
    		this.mContext = ctx;
    	}
		public int getCount() {
			// TODO Auto-generated method stub
			return galleryRes.length;
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if(convertView == null) {
				imageView = new ImageView(mContext);
				imageView.setAdjustViewBounds(true);
				imageView.setPadding(8, 1, 8, 1);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			}else {
				imageView = (ImageView)convertView;
			}
			
			imageView.setImageResource(galleryRes[position]);
			
			return imageView;
		}
    	
    }
    
     
	@Override
	public void onPause(){
		super.onPause();
		SMSEX ex = (SMSEX)this.getApplication();
		ex.setM(set01.getText().toString());
		ex.setT(set02.getText().toString());
		ex.setR(set03.getText().toString());
	}
	
	@Override
	public void onResume(){
		super.onResume();
		SMSEX ex = (SMSEX)this.getApplication();
		if(ex.getM() != null){
			set01.setText(ex.getM());
			set02.setText(ex.getT());
			set03.setText(ex.getR());
		}
	}
	 
   
}