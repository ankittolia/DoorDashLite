package doordash.com.doordash.view.ui;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import doordash.com.doordash.R;
import doordash.com.doordash.databinding.ActivityMainBinding;
import doordash.com.doordash.service.model.RestaurantModel;
import doordash.com.doordash.view.adapter.CustomAdapter;
import doordash.com.doordash.viewmodel.MainActivityViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ankit on 9/15/2018.
 */

public class MainActivity extends AppCompatActivity implements MvvmView {
    @Inject
    MainActivityViewModel viewModel;
    private List<RestaurantModel> restaurantModels;
    private CustomAdapter adapter;
    private CompositeDisposable disposables;
    ProgressDialog pgDialog;
    ActivityMainBinding activityMainBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        showProgressBar();
        createDisposables();
    }

    @Override
    public void createDisposables() {
        disposables = new CompositeDisposable();
        //Add restaurants observer
        disposables.add(viewModel.getRestaurants().
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribeWith(getDisposableObserver()));
    }

    private DisposableObserver<List<RestaurantModel>> getDisposableObserver() {
        return new DisposableObserver<List<RestaurantModel>>() {

            @Override
            public void onComplete() {
                initRecyclerView();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<RestaurantModel> value) {
                if (value != null) {
                    restaurantModels = new ArrayList<RestaurantModel>();
                    restaurantModels.addAll(value);
                }
            }
        };
    }

    public void initRecyclerView() {
        if (restaurantModels != null) {
            adapter = new CustomAdapter(this, restaurantModels);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            activityMainBinding.customRecyclerView.setLayoutManager(layoutManager);
            activityMainBinding.customRecyclerView.setAdapter(adapter);
        }
        hideProgressBar();
    }

    private void showProgressBar() {
        pgDialog = new ProgressDialog(this);
        pgDialog.setMessage(getResources().getString(R.string.progress_message));
        pgDialog.setCancelable(true);
        pgDialog.show();
    }

    private void hideProgressBar() {
        if (pgDialog != null) {
            pgDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

}
