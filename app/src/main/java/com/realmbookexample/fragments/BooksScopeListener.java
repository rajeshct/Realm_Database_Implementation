package com.realmbookexample.fragments;

import android.support.v4.app.Fragment;

import com.realmbookexample.database.RealmManager;
import com.realmbookexample.presenter.BooksPresenter;

/**
 *
 */
public class BooksScopeListener extends Fragment {
    public static String TAG=BooksScopeListener.class.getSimpleName();
    BooksPresenter booksPresenter;

    public BooksScopeListener() {
        setRetainInstance(true);
        RealmManager.getInstance().incrementCount();
        booksPresenter = new BooksPresenter();
    }

    @Override
    public void onDestroy() {
        RealmManager.getInstance().decrementCount();
        super.onDestroy();
    }

    public BooksPresenter getPresenter() {
        return booksPresenter;
    }
}
