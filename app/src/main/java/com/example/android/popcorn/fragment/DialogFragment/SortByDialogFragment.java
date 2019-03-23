package com.example.android.popcorn.fragment.DialogFragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.example.android.popcorn.R;

public class SortByDialogFragment extends DialogFragment implements OnClickListener {

    private static final String LOG_TAG = SortByDialogFragment.class.getSimpleName();

    private final int NO_CHECKED_ITEM = -1;

    private OnSortByChoiceClickListener mChoiceClickListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder alertDialogBuilder = new Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        alertDialogBuilder.setTitle(R.string.dialog_title).setSingleChoiceItems(
                R.array.sort_by_choices, NO_CHECKED_ITEM, this);

        return alertDialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        mChoiceClickListener.onClick(this, i);
        dialogInterface.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mChoiceClickListener = (OnSortByChoiceClickListener) context;
        } catch (ClassCastException e) {
            Log.e(LOG_TAG, "MainActivity must implement dialog listener.");
        }
    }

}
