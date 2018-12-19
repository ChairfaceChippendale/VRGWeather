package com.ujujzk.ui.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.ujujzk.ui.R;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private MainContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSearchView();
    }

    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void initSearchView() {
        binding.searchView.setOnClickListener(v -> {
            if (binding.searchView.isIconified()) {
                binding.searchView.setIconified(false);
            }
        });
        binding.searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                minimizeKeyboard();
                binding.searchView.clearFocus();
                doSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //not implemented
                return false;
            }
        });
    }

    private void doSearch(String query) {
        presenter.findForecast(query);
    }


    private void minimizeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
