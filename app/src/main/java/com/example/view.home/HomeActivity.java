package com.example.view.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.Utils;
import com.example.adapter.RecyclerViewHomeAdapter;
import com.example.adapter.ViewPagerHeaderAdapter;
import com.example.model.Categories;
import com.example.model.Meals;
import com.example.mvpfoodrecipeapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


// TODO 31 implement the HomeView interface at the end
public class HomeActivity extends AppCompatActivity implements HomeView{

    /*
     * TODO 32 Add @BindView Annotation (1)
     *
     * 1. viewPagerHeader
     * 2. recyclerCategory
     *
     */
    @BindView(R.id.viewPagerHeader)
    ViewPager viewPagerMeal;
    @BindView(R.id.recylcerCtegory)
    RecyclerView recyclerViewCategory;


    /*
     *  TODO 33 Create variable for presenter;
     */
    HomePresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*
         *  TODO 34 bind the ButterKnife (2)
         */
        ButterKnife.bind(this);

        /*
         *  TODO 35 Declare the presenter
         *  new HomePresenter(this)
         */
        presenter=new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    // TODO 36 Overriding the interface
    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.INVISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.INVISIBLE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter=new ViewPagerHeaderAdapter(meal,this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20,0,150,0);
        headerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter homeAdapter=new RecyclerViewHomeAdapter(category,this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onerrorLoading(String message) {
        Utils.showDialogMessage(this,"Title:",message);
    }


}

