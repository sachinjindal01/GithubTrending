package com.kutumb.trending.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;

public class RepoModel implements Serializable {
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("results")
    @Expose
    private boolean result;

    @SerializedName("items")
    @Expose
    private List<ItemModel> items;

    public RepoModel(int count, boolean result, List<ItemModel> items) {
        this.count = count;
        this.result = result;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isResults() {
        return result;
    }

    public void setResults(boolean result) {
        this.result = result;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
}
