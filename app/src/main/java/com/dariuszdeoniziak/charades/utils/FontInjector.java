package com.dariuszdeoniziak.charades.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.views.Font;

import java.lang.reflect.Field;

public class FontInjector {

    public static void inject(Object object) {
        Field[] fields;
        if ((fields = object.getClass().getDeclaredFields()).length > 0) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Font font;
                if ((font = field.getAnnotation(Font.class)) != null) {
                    field.setAccessible(true);
                    try {
                        TextView textView = (TextView) field.get(object);
                        if (font.text() != 0)
                            textView.setText(font.text());
                        if (!font.path().isEmpty()) {
                            Context context = (Context) object;
                            Typeface tf = Typeface.createFromAsset(context.getAssets(), font.path());
                            textView.setTypeface(tf);
                        }
                    } catch (IllegalAccessException | ClassCastException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
