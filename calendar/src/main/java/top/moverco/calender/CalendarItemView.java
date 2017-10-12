package top.moverco.calender;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Moverco.
 */

public class CalendarItemView extends android.support.v7.widget.AppCompatTextView {
    public CalendarItemView(Context context) {
        super(context);
    }

    public CalendarItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
