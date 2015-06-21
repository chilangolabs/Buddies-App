package com.chilangolabs.buddis.OwnElements;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by gorro on 20/06/15.
 */
public class TextBuddis extends TextView {

    Typeface tf;

    public TextBuddis(Context context) {
        super(context);
        initStyle();
    }

    public TextBuddis(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStyle();
    }

    public TextBuddis(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle();
    }

    private void initStyle() {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        this.setTypeface(tf);
    }

}
