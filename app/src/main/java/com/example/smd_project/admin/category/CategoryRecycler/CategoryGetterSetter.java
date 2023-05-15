package com.example.smd_project.admin.category.CategoryRecycler;

import android.app.Application;

public class CategoryGetterSetter extends Application {
    public static String categoryText;

    public static int getCategoryId() {
        return categoryId;
    }

    public static void setCategoryId(int categoryId) {
        CategoryGetterSetter.categoryId = categoryId;
    }

    public static int categoryId;
    public CategoryGetterSetter() {

    }public CategoryGetterSetter(String categoryText,int categoryId) {
        this.categoryText = categoryText;
        this.categoryId = categoryId;
    }

    public static String getCategoryText() {
        return categoryText;
    }

    public static void setCategoryText(String categoryText) {
        CategoryGetterSetter.categoryText = categoryText;
    }
}
