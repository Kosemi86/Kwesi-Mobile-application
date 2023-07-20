package com.example.afinal.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


import com.example.afinal.Database;
import com.example.afinal.Model.Category;
import com.example.afinal.R;

import java.util.List;


    public class UserFragment extends Fragment {

        private EditText categoryNameEditText;

        private Button loadCategoriesButton;
        private List<Category> categories;
        private int currentPosition;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.userfragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            loadCategoriesButton = view.findViewById(R.id.loadCategoriesButton);
            categoryNameEditText = view.findViewById(R.id.categoryNameEditText);

            // Set a click listener for the load categories button
            loadCategoriesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Load the categories from the database
                    Database db = new Database(getContext());
                    categories = db.getAllCategories();
                    currentPosition = 0;
                    // Set the category names in the edit text view
                    StringBuilder categoryNames = new StringBuilder();
                    if (categories.size() > 0) {
                        categoryNames.append(categories.get(currentPosition).getName());
                    } else {
                        categoryNames.append("No categories found.");
                    }
                    categoryNameEditText.setText(categoryNames.toString());
                }
            });

            // Set a click listener for the next category button
            Button nextCategoryButton = view.findViewById(R.id.nextCategoryButton);
            nextCategoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPosition++;
                    if (currentPosition >= categories.size()) {
                        currentPosition = 0;
                    }
                    categoryNameEditText.setText(categories.get(currentPosition).getName());
                }
            });
        }


    }
