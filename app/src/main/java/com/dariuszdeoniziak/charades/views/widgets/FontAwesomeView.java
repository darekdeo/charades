package com.dariuszdeoniziak.charades.views.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class FontAwesomeView extends AppCompatTextView {

    public FontAwesomeView(Context context) {
        this(context, null);
    }

    public FontAwesomeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontAwesomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface tf = FontAwesomeCache.get(context);
        setTypeface(tf);
    }

    private static class FontAwesomeCache {

        private static Typeface fontCache;

        private static Typeface get(Context context) {
            if (fontCache == null) {
                fontCache = Typeface.createFromAsset(
                        context.getApplicationContext().getAssets(),
                        "fontawesome-webfont.ttf"
                );
            }
            return fontCache;
        }
    }
}
