package com.kutumb.trending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OwnerModel implements Serializable {
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("username")
    @Expose
    private String username;

    public OwnerModel(String link, String avatar, String username) {
        this.link = link;
        this.avatar = avatar;
        this.username = username;
    }

    public String getLink() {
        return link;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
