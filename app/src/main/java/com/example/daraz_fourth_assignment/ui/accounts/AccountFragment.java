package com.example.daraz_fourth_assignment.ui.accounts;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daraz_fourth_assignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private AccountViewModel accountViewModel;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        return root;
    }

}
