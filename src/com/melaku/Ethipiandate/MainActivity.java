package com.melaku.Ethipiandate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.TextView;


public class MainActivity extends Activity {

	DatePicker datePicker1;
	int oneDay = 24*3600;
	int oneYear = 365*oneDay;
	int fourYears= 4*oneYear+oneDay;
	TextView editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datePicker1= (DatePicker) findViewById(R.id.datePicker1);
		editText = (TextView) findViewById(R.id.AmharicDateh);
		GregorianCalendar now = new GregorianCalendar();
		updateEthiopianDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
		datePicker1.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH) ,now.get(Calendar.DAY_OF_MONTH) , new DatePicker.OnDateChangedListener(){
			public void onDateChanged(DatePicker view, int year, int month, int day){
				updateEthiopianDate(year,month,day);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void updateEthiopianDate(int yr,int dt,int mon){

		long difference= (long) Math.floor(new GregorianCalendar(yr,dt,mon).getTimeInMillis() /1000)-(long) Math.floor(new GregorianCalendar(1971,8,12).getTimeInMillis() /1000);//MONTH WORKS 0 TO 11
		int fourYearsPassed= (int) Math.floor(difference/fourYears);
		int remainingYears=(int) Math.floor((difference-fourYearsPassed*fourYears)/oneYear);
		int remainingMonths= (int)Math.floor((difference-fourYearsPassed*fourYears-remainingYears*oneYear)/(30*oneDay));
		int remainingDays=(int)Math.floor((difference-fourYearsPassed*fourYears-remainingYears*oneYear-remainingMonths*30*oneDay)/oneDay);
		int day=remainingDays+1;
		int month=remainingMonths+1;
		int year=remainingYears+4*fourYearsPassed+1964;
		if(difference<0){
			year=remainingYears+4*fourYearsPassed+1964-1;
			month=remainingMonths+12;
			day=remainingDays+30;
		}
		String dayw="   ";
		switch(new GregorianCalendar(yr,dt,mon).get(Calendar.DAY_OF_WEEK)){
		case Calendar.SUNDAY:
			dayw="Sun";
			break;
		case Calendar.MONDAY:
			dayw="Mon";
			break;
		case Calendar.TUESDAY:
			dayw="Tue";
			break;
		case Calendar.WEDNESDAY:
			dayw="Wed";
			break;
		case Calendar.THURSDAY:
			dayw="Thu";
			break;
		case Calendar.FRIDAY:
			dayw="Fri";
			break;
		case Calendar.SATURDAY:
			dayw="Sat";
			break;
				}
		editText.setText(day+", "+month+", "+year+", "+dayw);
	}

}
