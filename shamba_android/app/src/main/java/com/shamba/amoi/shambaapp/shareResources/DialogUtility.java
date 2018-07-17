package com.shamba.amoi.shambaapp.shareResources;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.widget.TextView;



/**
 * Created by amoi on 02/01/2018.
 */

public class DialogUtility  {

    public String dialogue_title;
    String negative_title;
    String postive_title;
    String neutral_title;


    Activity activity;


    public DialogUtility(Activity activity, String dialogue_title, String negative_title,
                         String postive_title) {
        this.dialogue_title = dialogue_title;
        this.activity = activity;
        this.negative_title = negative_title;
        this.postive_title = postive_title;
    }

    public DialogUtility(Activity activity, String dialogue_title, String negative_title,
                         String postive_title,String neutral_title) {
        this.dialogue_title = dialogue_title;
        this.activity = activity;
        this.negative_title = negative_title;
        this.postive_title = postive_title;
        this.neutral_title = neutral_title;
    }

    public void setSimpleDialogOnRecyclerListItem(View list_view, TextView ...  textViews) {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                    builder.setTitle(dialogue_title);

                    builder.setNegativeButton(Html.fromHtml(negative_title),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onSelectNegativeDialogueOption();
                                }
                            });

                    builder.setPositiveButton(Html.fromHtml(postive_title)
                            , new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onSelectPostiveDialogueOption();
                                }
                            });

                    builder.setNeutralButton(Html.fromHtml(neutral_title)
                            , new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onSelectNeutralDialogueOption();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                } catch (Exception ex)

                {
                    ex.printStackTrace();
                }
            }
        };

        list_view.setOnClickListener(listener);
        for(int i=0;i<textViews.length;++i)
            textViews[i].setOnClickListener(listener);
    }


    public void setSimpleDialogOnRecyclerListItem2(View list_view, TextView textView1, TextView textView2) {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                    builder.setTitle(dialogue_title);

                    builder.setNegativeButton(Html.fromHtml(negative_title),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onSelectNegativeDialogueOption();
                                }
                            });

                    builder.setPositiveButton(Html.fromHtml(postive_title)
                            , new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onSelectPostiveDialogueOption();
                                }
                            });

                    builder.setNeutralButton(Html.fromHtml(neutral_title)
                            , new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onSelectNeutralDialogueOption();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } catch (Exception ex)

                {
                    ex.printStackTrace();
                }
            }
        };

        list_view.setOnClickListener(listener);
        textView1.setOnClickListener(listener);
        textView2.setOnClickListener(listener);
    }

    public void onSelectPostiveDialogueOption() {
    }

    public  void  onSelectNegativeDialogueOption(){
    }

    public  void  onSelectNeutralDialogueOption(){
    }
}


