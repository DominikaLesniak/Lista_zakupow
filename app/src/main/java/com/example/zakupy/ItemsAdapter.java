package com.example.zakupy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.zakupy.utils.InputDialogMode;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends ArrayAdapter<Item> {
    Context context;
    int layoutResourceId;
    List<Item> items;

    public ItemsAdapter(Context context, int resource, ArrayList<Item> items) {
        super(context, resource, items);

        this.context = context;
        this.layoutResourceId = resource;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        ItemHolder holder;

        if (itemView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            itemView = inflater.inflate(layoutResourceId, parent, false);

            holder = new ItemHolder();
            holder.itemName = (TextView) itemView.findViewById(R.id.itemName);
            holder.itemCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            itemView.setTag(holder);
        } else {
            holder = (ItemHolder) itemView.getTag();
        }

        Item item = items.get(position);
        holder.itemName.setOnClickListener(view -> openEditItemDialog(item));
        holder.itemName.setText(item.getName());
        holder.itemCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> item.setChecked(isChecked));
        holder.itemCheckBox.setChecked(item.getChecked());

        return itemView;
    }

    private void openEditItemDialog(Item item) {
        ItemInputDialog inputDialog = new ItemInputDialog();
        inputDialog.setMode(InputDialogMode.EDIT);
        inputDialog.setEditedItem(item);
        inputDialog.show(((FragmentActivity)context).getSupportFragmentManager(), "editDialog");
    }

    static class ItemHolder {
        TextView itemName;
        CheckBox itemCheckBox;
    }
}
