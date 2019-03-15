package com.socialnetwork.Fragments;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
//import android.os.Handler;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v7.widget.SearchView;

import com.socialnetwork.Adapter.RecyclerAdapter;
import com.socialnetwork.Database.FirebaseManager;
import com.socialnetwork.R;

import java.util.ArrayList;
//import java.util.logging.Handler;

import static com.socialnetwork.Database.FirebaseManager.getGroups;
import static com.socialnetwork.Utils.Constants.listGroup;

public class GroupFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Object> values;
    private RecyclerAdapter adapter;
    private ProgressBar progressBar;
    private RelativeLayout lytProgress;
    private SearchView searchView;

    public GroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.group_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        int span = 1;
        GridLayoutManager manager = new GridLayoutManager(getContext(), span);
        recyclerView.setLayoutManager(manager);

        progressBar = view.findViewById(R.id.progressBar);
        lytProgress = view.findViewById(R.id.lytProgress);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_group, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView sv = (SearchView) myActionMenuItem.getActionView();
        searchView(sv);
        // return true;
    }

    public void searchView(SearchView sv){
        searchView = sv;
        if (searchView != null) {
            searchView.requestFocus();
            searchView.setIconified(true);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (query.length() >= 3) {
                        setData();
                    } else if (query.length() > 1) {
                        Toast toast = Toast.makeText(getContext(), getResources().getString(R.string.app_search_producto_min), Toast.LENGTH_LONG);
                        toast.show();
                    }
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String s) {
                    if (s.length() == 0) {
                 //       recyclerView.setVisibility(View.GONE);
                    }
                    return false;
                }
            });
            ImageView closeButton = searchView.findViewById(R.id.search_close_btn);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText et = searchView.findViewById(R.id.search_src_text);
                    et.setText("");
                    searchView.setQuery("", false);
                  //  recyclerView.setVisibility(View.GONE);
                    // searchView.onActionViewCollapsed();
                    //  searchView.collapseActionView();
                }
            });
            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    }
                }
            });
        }
    }


    public void setData(){
        recyclerView.setVisibility(View.VISIBLE);

        adapter = new RecyclerAdapter(getActivity(), listGroup, new RecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClick(Object obj, int position) {
            }
        });
        recyclerView.setAdapter(adapter);
        listGroup = new ArrayList<>();
    }
}