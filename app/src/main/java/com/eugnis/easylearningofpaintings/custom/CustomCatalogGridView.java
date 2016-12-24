package com.eugnis.easylearningofpaintings.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Eugnis on 25.12.2016.
 */

public class CustomCatalogGridView extends GridView {

    public CustomCatalogGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCatalogGridView(Context context) {
        super(context);
    }

    public CustomCatalogGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}