package top.moverco.calender;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Moverco.
 */

public class MCalendar extends LinearLayout {

    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtMonth;
    private GridView grid;
    private Calendar mCalendar;

    public MCalendar(Context context) {
        super(context);
    }

    public MCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public MCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context);
    }
    private void initControl(Context context){
        bindControl(context);
        bindControlEvent();
    }

    private void bindControlEvent() {
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.add(C);
            }
        });
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void bindControl(Context context) {
        LayoutInflater infalter = LayoutInflater.from(context);
        infalter.inflate(R.layout.calendar_view,this,false);

        btnPrev = (ImageView) findViewById(R.id.calender_prev_button);
        btnNext = (ImageView) findViewById(R.id.calender_next_button);
        txtMonth = (TextView) findViewById(R.id.calender_header_text);
        grid = (GridView) findViewById(R.id.calender_grid);

    }
}
