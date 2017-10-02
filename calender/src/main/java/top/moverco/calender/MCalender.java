package top.moverco.calender;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Moverco.
 */

public class MCalender extends LinearLayout {
    public MCalender(Context context) {
        super(context);
    }

    public MCalender(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MCalender(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initControl(Context context){
        bindControl();
    }
}
