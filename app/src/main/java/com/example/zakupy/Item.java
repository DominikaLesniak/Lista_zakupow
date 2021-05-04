package com.example.zakupy;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private Integer amount;
    private Boolean checked;

    public Item(String name) {
        this.name = name;
        this.amount = null;
        this.checked = false;
    }

    public Item(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
        this.checked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", checked=" + checked +
                '}';
    }
}
