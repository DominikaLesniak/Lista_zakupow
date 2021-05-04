package com.example.zakupy;

import com.example.zakupy.utils.SortMode;

public interface DialogResultsListener {
    void passCreatedItem(String inputText);

    void passSortMode(SortMode sortMode);
}
