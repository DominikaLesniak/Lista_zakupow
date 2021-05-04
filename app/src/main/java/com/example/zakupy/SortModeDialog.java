package com.example.zakupy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.zakupy.utils.SortMode;

public class SortModeDialog extends DialogFragment {
    public SortModeDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sort_dialog, container);
        TextView sort1 = (TextView) view.findViewById(R.id.sort1);
        TextView sort2 = (TextView) view.findViewById(R.id.sort2);
        TextView sort3 = (TextView) view.findViewById(R.id.sort3);
        sort1.setTag(SortMode.ALPHABETICALLY);
        sort2.setTag(SortMode.CHECKED_UNCHECKED);
        sort3.setTag(SortMode.UNCHECKED_CHECKED);

        final SortModeDialog sortModeDialog = this;
        View.OnClickListener onClickListener = v -> {
            DialogResultsListener dialogListener = (DialogResultsListener) getActivity();
            dialogListener.passSortMode((SortMode) v.getTag());
            sortModeDialog.dismiss();
        };
        sort1.setOnClickListener(onClickListener);
        sort2.setOnClickListener(onClickListener);
        sort3.setOnClickListener(onClickListener);

        return view;
    }
}
