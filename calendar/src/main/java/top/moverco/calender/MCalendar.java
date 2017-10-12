package top.moverco.calender;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Moverco.
 */

public class MCalendar extends LinearLayout {

    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView textDate;
    private GridView grid;
    private Calendar curDate = Calendar.getInstance();
    private String displayFormat;

    private final static int CALENDAR_PREV = -1;
    private final static int CALENDAR_NEXT = 1;

    public MCalendarItemClickListener mMCalendarItemClickListener;

    public MCalendar(Context context) {
        super(context);
    }

    public MCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public MCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    private void initControl(Context context, @Nullable AttributeSet attrs) {
        bindControl(context);
        bindControlEvent();

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MCalendar);
        try {
            String format = typedArray.getString(R.styleable.MCalendar_dateFormat);
            displayFormat = format;
            if (displayFormat == null) {
                displayFormat = "MMM yyyy";
            }
        } finally {
            typedArray.recycle();
        }
        renderCalendar();
    }

    private void bindControlEvent() {
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCalendarMonth(curDate, CALENDAR_PREV);
                renderCalendar();
            }
        });
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCalendarMonth(curDate, CALENDAR_NEXT);
                renderCalendar();
            }
        });

    }

    private void changeCalendarMonth(Calendar calendar, int change) {
        calendar.add(Calendar.MONTH, change);
    }

    private void changeCalendarDayOfMonth(Calendar calendar, int change) {
        calendar.add(Calendar.DAY_OF_MONTH, change);
    }

    private void renderCalendar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(displayFormat);
        textDate.setText(simpleDateFormat.format(curDate.getTime()));

        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) curDate.clone();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        changeCalendarMonth(calendar, -prevDays);
        int maxCellsCount = 6 * 7;
        while (cells.size() < maxCellsCount) {
            cells.add(calendar.getTime());
            changeCalendarDayOfMonth(calendar, CALENDAR_NEXT);
        }
        grid.setAdapter(new CalendarAdapter(getContext(), cells));
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (mMCalendarItemClickListener == null) {
                    return false;
                } else {
                    mMCalendarItemClickListener.onItemLongPress((Date) parent.getItemAtPosition(position));
                    return true;
                }
            }
        });
    }

    private void bindControl(Context context) {
        LayoutInflater infalter = LayoutInflater.from(context);
        infalter.inflate(R.layout.calendar_view, this);

        btnPrev = (ImageView) findViewById(R.id.calendar_prev_button);
        btnNext = (ImageView) findViewById(R.id.calendar_next_button);
        textDate = (TextView) findViewById(R.id.calendar_header_text);
        grid = (GridView) findViewById(R.id.calendar_grid);

    }

    class CalendarAdapter extends ArrayAdapter<Date> {

        LayoutInflater mInflater;
        Boolean isTheCurMonth = false;

        @LayoutRes
        private final int defaultCalendarItemView = R.layout.calendar_text_day;
        @LayoutRes
        private int calendarItemView = defaultCalendarItemView;

        public void setCalendarItemView(int calendarItemView) {
            this.calendarItemView = calendarItemView;
        }

        public CalendarAdapter(@NonNull Context context, ArrayList<Date> days) {
            super(context, R.layout.calendar_text_day, days);
            mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Date date = getItem(position);
            if (convertView == null) {
                convertView = mInflater.inflate(calendarItemView, parent, false);
            }
            int day = date.getDate();
            ((TextView) convertView).setText(String.valueOf(day));

            Date now = new Date();

            Calendar calendar = (Calendar) curDate.clone();
            calendar.set(Calendar.DAY_OF_MONTH, CALENDAR_NEXT);
            if (now.getMonth() == date.getMonth()) {
                isTheCurMonth = true;
                ((TextView) convertView).setTextColor(Color.parseColor("#000000"));
                if (now.getDate() == date.getDate() && now.getYear() == date.getYear()) {
                    ((TextView) convertView).setTextColor(Color.parseColor("#ff0000"));
                }
            } else {
                ((TextView) convertView).setTextColor(Color.parseColor("#666666"));
            }
            return convertView;
        }
    }
}
