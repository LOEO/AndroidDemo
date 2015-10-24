package com.leo.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by LT on 2015-06-03.
 */
public class Header extends LinearLayout {
    private Column[] columns;

    public Header(Context context) {
        super(context);
    }

    public Header(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Header(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Column[] getColumns() {
        return columns;
    }

    public void setColumns(Column[] columns) {
        this.columns = columns;
    }
}
