package com.example.android.popcorn.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.android.popcorn.R;

public class SortByDialogFragment extends DialogFragment {

    private static final String LOG_TAG = SortByDialogFragment.class.getSimpleName();

    private final int NO_CHECKED_ITEM = -1;
    private int mSelectedChoice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder alertDialogBuilder = new Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        alertDialogBuilder.setTitle(R.string.dialog_title).setSingleChoiceItems(
                R.array.sort_by_choices, NO_CHECKED_ITEM, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Display the user choice on toolbar.
                        mSelectedChoice = i;
                        dialogInterface.dismiss();
                    }
                });

        return alertDialogBuilder.create();
    }

    public int getSelectedChoice() {
        return mSelectedChoice;
    }

}
