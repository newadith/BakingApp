package com.aditya.bakingapp.home;

import android.content.Context;
import android.content.SharedPreferences;

import com.aditya.bakingapp.object.Recipe;
import com.aditya.bakingapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

class HomePresenterImpl implements HomePresenter {

    private List<Recipe> mRecipes;
    private HomeView mView;
    private HomeInteractor mInteractor;

    HomePresenterImpl(HomeView view){
        mView = view;
        mRecipes = new ArrayList<>();
        mInteractor = new HomeInteractorImpl(this);
    }

    @Override
    public void loadRecipes() {
        mInteractor.loadRecipes();
    }

    @Override
    public void failLoadingRecipes(String message) {
        mView.onErrorLoadingRecipes(message);
    }

    @Override
    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    @Override
    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        mView.onShowRecipes(mRecipes);
    }

    @Override
    public Recipe getRecipeAt(int index) {
        try {
            return mRecipes.get(index);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public void setActiveRecipeId(Context context, long id) {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(Constants.PREFERENCES, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(Constants.Param.RECIPE_ID, id);
        editor.apply();
    }
}
