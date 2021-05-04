package com.example.zakupy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.zakupy.utils.InputDialogMode;
import com.example.zakupy.utils.PreferencesUtils;
import com.example.zakupy.utils.SortMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.zakupy.utils.PreferencesUtils.saveList;

public class MainActivity extends AppCompatActivity implements DialogResultsListener {
    ListView listView;
    ArrayList<Item> items;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();

        items = PreferencesUtils.readSavedList(this);
        listView = findViewById(R.id.listView);
        itemsAdapter = new ItemsAdapter(this, R.layout.item, items);
        listView.setAdapter(itemsAdapter);

        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(v -> openCreateItemDialog());
    }

    @Override
    protected void onPause() {
        saveList(this, items);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    public void openCreateItemDialog() {
        ItemInputDialog inputDialog = new ItemInputDialog();
        inputDialog.setMode(InputDialogMode.CREATE);
        inputDialog.show(getSupportFragmentManager(), "inputDialog");
    }

    private void setUpToolbar() {
        Toolbar myToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(myToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowTitleEnabled(false);
    }

    public void deleteItems(MenuItem menuItem) {
        List<Item> collect = items.stream()
                .filter(Item::getChecked)
                .collect(Collectors.toList());
        items.removeAll(collect);
        itemsAdapter.notifyDataSetChanged();
    }

    public void sortItems(MenuItem menuItem) {
        SortModeDialog sortModeDialog = new SortModeDialog();
        sortModeDialog.show(getSupportFragmentManager(), "sortOptionDialog");
    }

    @Override
    public void passCreatedItem(String inputText) {
        itemsAdapter.add(new Item(inputText));
    }

    @Override
    public void passSortMode(SortMode sortMode) {
        switch (sortMode) {
            case ALPHABETICALLY:
                itemsAdapter.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
                break;
            case CHECKED_UNCHECKED:
                items.sort(Comparator.comparing((Item item) -> !item.getChecked())
                        .thenComparing((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())));
                break;
            case UNCHECKED_CHECKED:
                items.sort(Comparator.comparing(Item::getChecked)
                        .thenComparing((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())));
                break;
        }
        itemsAdapter.notifyDataSetChanged();
    }
}