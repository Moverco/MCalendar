package top.moverco.mcalender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import top.moverco.calender.MCalendar;
import top.moverco.calender.MCalendarItemClickListener;

public class MainActivity extends AppCompatActivity implements MCalendarItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MCalendar calendar = (MCalendar) findViewById(R.id.calendar);
        calendar.mMCalendarItemClickListener = this;

    }

    @Override
    public void onItemLongPress(Date day) {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        Toast.makeText(this,dateFormat.format(day),Toast.LENGTH_SHORT).show();
    }
}
