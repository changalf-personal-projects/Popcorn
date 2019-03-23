package com.example.android.popcorn.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.android.popcorn.R;

public class SortByDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder alertDialogBuilder = new Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        alertDialogBuilder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

        return alertDialogBuilder.create();
    }

}
