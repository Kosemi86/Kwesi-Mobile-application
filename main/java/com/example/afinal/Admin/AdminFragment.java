package com.example.afinal.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.afinal.Database;
import com.example.afinal.Model.Category;
import com.example.afinal.R;


import java.util.List;

public class AdminFragment extends Fragment {

    private EditText categoryNameEditText;
    private Button addCategoryButton;
    private Button loadCategoriesButton;
    private Button deleteCategoryButton;
    private List<Category> categories;
    private int currentPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get references to the views
        categoryNameEditText = view.findViewById(R.id.categoryNameEditText);
        addCategoryButton = view.findViewById(R.id.addCategoryButton);
        loadCategoriesButton = view.findViewById(R.id.loadCategoriesButton);
        deleteCategoryButton = view.findViewById(R.id.delete_category_button);

        // Set a click listener for the add category button
        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category name from the edit text
                String categoryName = categoryNameEditText.getText().toString().trim();
                // Insert the category into the database
                Database db = new Database(getContext());
                db.addCategory(categoryName);
                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
            }
        });










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






        deleteCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categories != null && categories.size() > 0) {
                    Category categoryToDelete = categories.get(currentPosition);
                    Database database= new Database(getContext());
                    database.deleteCategory(categoryToDelete.getId());
                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    categories.remove(categoryToDelete);
                    currentPosition = 0;
                    if (categories.size() > 0) {
                        categoryNameEditText.setText(categories.get(currentPosition).getName());
                    } else {
                        categoryNameEditText.setText("");
                    }
                }
            }
        });


        // Set a click listener for the update category button
        Button updateCategoryButton = view.findViewById(R.id.edit_category_button);
        updateCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category name and ID from the edit text and current category
                String categoryName = categoryNameEditText.getText().toString().trim();
                Category currentCategory = categories.get(currentPosition);
                int categoryId = currentCategory.getId();
                // Update the category in the database
                Database db = new Database(getContext());
                db.updateCategory(categoryId, categoryName);
                Toast.makeText(getContext(), "Category updated to " + categoryName, Toast.LENGTH_SHORT).show();
            }
        });





    }
}