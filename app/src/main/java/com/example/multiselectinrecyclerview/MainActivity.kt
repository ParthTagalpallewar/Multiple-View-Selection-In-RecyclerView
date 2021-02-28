package com.example.multiselectinrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.multiselectinrecyclerview.RecyclerWithActionMode.DeleteBtnMultiSelect
import com.example.multiselectinrecyclerview.RecyclerWithActionMode.RecyclerViewWithActionView
import com.example.multiselectinrecyclerview.databinding.ActivityMainBinding
import com.example.toolbar.utils.move


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.mainActionRecyclerBtn.setOnClickListener{
            move(RecyclerViewWithActionView::class.java)
        }

        binding.mainDeleteRecyclerBtn.setOnClickListener{
            move(DeleteBtnMultiSelect::class.java)
        }
    }



}