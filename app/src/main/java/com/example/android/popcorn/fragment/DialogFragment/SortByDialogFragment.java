package com.example.android.popcorn.fragment.DialogFragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;

import com.example.android.popcorn.R;

public class SortByDialogFragment extends DialogFragment {

    private static final String LOG_TAG = SortByDialogFragment.class.getSimpleName();

    private final int DEFAULT_CHECKED = 0;

    // Public constructor for other fragments to instantiate this class.
    public SortByDialogFragment() {

    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder alertDialogBuilder = new Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        alertDialogBuilder.setTitle(R.string.dialog_title).setSingleChoiceItems(
                R.array.sort_by_choices, DEFAULT_CHECKED, null);

        return alertDialogBuilder.create();
    }

}
