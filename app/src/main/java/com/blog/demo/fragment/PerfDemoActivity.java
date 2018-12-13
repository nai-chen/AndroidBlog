package com.blog.demo.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Debug;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

public class PerfDemoActivity extends Activity {
	private List<StockPriceItem> _stockPrices = new ArrayList<StockPriceItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		Debug.startMethodTracing("tracedemo");
		long startTime = System.currentTimeMillis();
		ReadCsv();
		long readCsvEndTime = System.currentTimeMillis();

		SortPriceFromHighestToLowest("High");
		long sortEndTime = System.currentTimeMillis();

		Debug.stopMethodTracing();
		
		final ListView listview = (ListView) findViewById(R.id.id_listview);
		ArrayAdapter<StockPriceItem> adapter = new ArrayAdapter<StockPriceItem>(this,
				android.R.layout.simple_list_item_1, _stockPrices);
		listview.setAdapter(adapter);
		long endTime = System.currentTimeMillis();

		LogUtil.log("PerfDeom", "ReadCsv函数的使用时间： " + (readCsvEndTime - startTime));
		LogUtil.log("PerfDeom", "Sort函数的使用时间： " + (sortEndTime - readCsvEndTime));
		LogUtil.log("PerfDeom", "onCreate函数整体使用时间： " + (endTime - startTime));
	}

	private void SortPriceFromHighestToLowest(String column) {
		if (column.compareTo("High") == 0 ) {
			// 使用冒泡法
			for ( int i = 0; i < _stockPrices.size(); ++i ) {
				for ( int j = i; j <  _stockPrices.size(); ++j ) {
					if ( _stockPrices.get(i).getHigh() < _stockPrices.get(j).getHigh() ) {
						StockPriceItem temp = _stockPrices.get(i);
						_stockPrices.set(i, _stockPrices.get(j));
						_stockPrices.set(j, temp);
					}
				}
			}
		}
	}
	
	private void ReadCsv() {
		try {
		    BufferedReader reader = new BufferedReader(
		        new InputStreamReader(getAssets().open("stockprice.csv"), "UTF-8")); 
		    
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    String line = reader.readLine();
		    Boolean isFirstLine = true;		    
		    
		    while (line != null) {
		    	if ( isFirstLine ) {
		    		isFirstLine = false;
		    	} else {
			       String[] parts = line.split(",");
			       StockPriceItem item = new StockPriceItem();
			       
//			       try {
					   /** v1
				       item.Date = format.parse(parts[0]);
				       item.Open = Float.parseFloat(parts[1]);
				       item.High = Float.parseFloat(parts[2]);
				       item.Low = Float.parseFloat(parts[3]);
				       item.Close = Float.parseFloat(parts[4]);
				       item.Volume = Long.parseLong(parts[5]);
				       item.AdjClose = Float.parseFloat(parts[6]); */
					   item.setDate(parts[0]);
					   item.setOpen(parts[1]);
					   item.setHigh(parts[2]);
					   item.setLow(parts[3]);
					   item.setClose(parts[4]);
					   item.setVolume(parts[5]);
					   item.setAdjClose(parts[6]);
				       _stockPrices.add(item);
//			       } catch ( ParseException ei ) {
					   // 忽略有错误日期的那一行
//			       }
		    	}
		    	
		       line = reader.readLine(); 
		    }

		    reader.close();
		} catch (IOException e) {
		}
	}

	public static class StockPriceItem {
		/** v1
		public java.util.Date Date;
		public float Open;
		public float High;
		public float Low;
		public float Close;
		public long Volume;
		public float AdjClose;

		@Override public String toString() {
			return String.format("[%s]: Open - %.2f, High - %.2f, Low - %.2f, Close - %.2f.",
					Date, Open, High, Low, Close);
		} */

		private String _volume;
		private String _date;
		private String _open;
		private String _high;
		private String _low;
		private String _close;
		private String _adjClose;

		private float _fHigh = -1.0f;

		public void setVolume(String volume) { this._volume = volume; }
		public void setDate(String date) { this._date = date; }
		public void setOpen(String open) { this._open = open; }
		public void setHigh(String high) { this._high = high; }
		public void setLow(String low) { this._low = low; }
		public void setClose(String close) { this._close = close; }
		public void setAdjClose(String adjClose) { this._adjClose = adjClose; }

		public float getHigh() {
			if (_fHigh < 0f) {
				_fHigh = Float.parseFloat(_high);
			}
			return  _fHigh;
		}
	}
}
