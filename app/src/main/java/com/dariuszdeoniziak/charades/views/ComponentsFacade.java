package com.dariuszdeoniziak.charades.views;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog;


public class ComponentsFacade {

    private final WeakReference<Context> contextRef;

    @Inject
    public ComponentsFacade(Context context) {
        contextRef = new WeakReference<>(context);
    }

    public void showToast(String text, int length) {
        Toast.makeText(contextRef.get(), text, length).show();
    }

    public void showDialog(DialogTemplate template) {
        AlertDialog.Builder builder = new AlertDialog.Builder(contextRef.get())
                .setTitle(template.title())
                .setMessage(template.message());

        builder.setPositiveButton(
                template.positiveButtonText(),
                (dialog, which) -> template.positiveCallback()
        );

        builder.setNegativeButton(
                template.negativeButtonText(),
                (dialog, which) -> template.negativeCallback()
        );

        builder.create().show();
    }

    public interface DialogTemplate {
        String title();

        String message();

        String positiveButtonText();

        default String negativeButtonText() {
            return "";
        }

        default void positiveCallback() {
        }

        default void negativeCallback() {
        }
    }
}
