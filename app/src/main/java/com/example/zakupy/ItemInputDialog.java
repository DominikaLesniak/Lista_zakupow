package com.example.zakupy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.zakupy.utils.InputDialogMode;


public class ItemInputDialog extends AppCompatDialogFragment {
    private InputDialogMode mode;
    private Item editedItem;

    public ItemInputDialog() {
    }

    public ItemInputDialog(InputDialogMode mode) {
        this.mode = mode;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mode.actionName + " produkt");

        final EditText input = new EditText(getActivity());
        input.requestFocus();
        builder.setView(input);
        builder.setPositiveButton("Zapisz", (dialog, which) -> {
            String inputItem = input.getText().toString();
            if (InputDialogMode.CREATE.equals(mode)) {
                DialogResultsListener dialogListener = (DialogResultsListener) getActivity();
                dialogListener.passCreatedItem(inputItem);
            } else if (InputDialogMode.EDIT.equals(mode)) {
                editedItem.setName(inputItem);
            }
            this.dismiss();
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void setMode(InputDialogMode mode) {
        this.mode = mode;
    }

    public void setEditedItem(Item editedItem) {
        this.editedItem = editedItem;
    }
}
