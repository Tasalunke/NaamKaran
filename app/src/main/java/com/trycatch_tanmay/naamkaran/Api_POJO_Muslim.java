package com.trycatch_tanmay.naamkaran;

import com.google.gson.annotations.SerializedName;

public class Api_POJO_Muslim  {
    @SerializedName("id")
    String id;

    @SerializedName("category_id")
    String categoryId;

    @SerializedName("name")
    String name;

    @SerializedName("meaning")
    String meaning;

    @SerializedName("gender")
    String gender;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public String getMeaning() {
        return meaning;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

}
