package com.example.zakupy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zakupy.Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class PreferencesUtils {
     static final String PREFERENCES_FILE_NAME = "com.example.zakupy.shared_preferences";
    private static final String LIST_PREFS_NAME = "ITEM_LIST";

    public static ArrayList<Item> readSavedList(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        Set<String> itemSet = preferences.getStringSet(LIST_PREFS_NAME, new HashSet<>());
        return deserializeItems(itemSet);
    }

    private static ArrayList<Item> deserializeItems(Set<String> itemSet) {
        return (ArrayList<Item>) itemSet.stream()
                .map(item -> new Gson().fromJson(item, Item.class))
                .collect(toList());
    }

    public static void saveList(Context context, ArrayList<Item> items) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> itemSet = serializeItems(items);
        editor.putStringSet(LIST_PREFS_NAME, itemSet);
        editor.apply();
    }

    private static Set<String> serializeItems(ArrayList<Item> items) {
        return items.stream()
                .map(item -> new Gson().toJson(item))
                .collect(toSet());
    }
}
