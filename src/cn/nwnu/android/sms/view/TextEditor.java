
package cn.nwnu.android.sms.view;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RemoteViews.ActionException;

import cn.nwnu.android.sms.R;
import cn.nwnu.android.sms.database.SMSSampleDao;
import cn.nwnu.android.sms.database.SMSSampleModel;


public class TextEditor extends Activity implements OnClickListener {

    
    private EditText editText;
    private Button btnSend;
    private Button btnSplit;
    private String text;
    ListView list;
    
	SMSSampleModel smssample;
    SMSSampleDao smssampledao;
    ArrayList<HashMap<String, String>> mylist;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        Intent intent = getIntent();
        setContentView(R.layout.text_editor);
        list = (ListView) findViewById(R.id.msgsend_lvs);
        editText = (EditText) findViewById(R.id.editor);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSplit = (Button) findViewById(R.id.btn_split);
        btnSend.setOnClickListener(this);
        btnSplit.setOnClickListener(this);

//        text = intent.getStringExtra("text");  
//        editText.setText(text);
        
	    //生成动态数组，并且转载数据  
	    mylist = new ArrayList<HashMap<String, String>>(); 
	    smssample = new SMSSampleModel();
        smssampledao = new SMSSampleDao(this);
        Cursor cursor = smssampledao.select();
        
        while(cursor.moveToNext()){
        	HashMap<String, String> map = new HashMap<String, String>();  
        	map.put("Body", cursor.getString(cursor.getColumnIndex("Body")));
        	mylist.add(map);  
        }
        
        cursor.close();
        smssampledao.close();
        //生成适配器的Item和动态数组对应的元素  
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,mylist,//数据源   
        		R.layout.samplesms,//ListItem的XML实现  
        		//动态数组对应的子项          
        		new String[] {"Body"},   
		        //ImageItem的XML文件里面的TextView ID  
		        new int[] {R.id.samplesms_tv1} 
      );  
       
      //添加并且显示  
      list.setAdapter(listItemAdapter);  
      
      list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				editText.setText(mylist.get(arg2).get("Body").toString());
			}
        });
        
        
     
    }

  
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_send:
            doSend();
//            this.finish();
            break;
        case R.id.btn_split:
        	editText.setText("");
//            this.finish();
            break;
        }
    }


    private void doSend() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));
        String body = editText.getText().toString();
        intent.putExtra("sms_body", body == null ? "" : body);
        startActivity(intent);
    }


  }

   

