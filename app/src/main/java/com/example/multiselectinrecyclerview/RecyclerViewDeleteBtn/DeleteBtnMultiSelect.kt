package com.example.multiselectinrecyclerview.RecyclerWithActionMode

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiselectinrecyclerview.RecyclerViewDeleteBtn.`interface`.SecondItemViewClickListener
import com.example.multiselectinrecyclerview.RecyclerViewDeleteBtn.adapter.SecondRecyclerView
import com.example.multiselectinrecyclerview.databinding.ActivityDeleteBtnMultiSelectBinding
import com.example.multiselectinrecyclerview.model.Email
import com.example.multiselectinrecyclerview.model.Emails
import com.example.multiselectinrecyclerview.utils.*

class DeleteBtnMultiSelect : AppCompatActivity(), SecondItemViewClickListener {

    lateinit var allEmails: ArrayList<Email>

    lateinit var binding: ActivityDeleteBtnMultiSelectBinding

    lateinit var mAdapter: SecondRecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeleteBtnMultiSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        //getting emails data
        allEmails = Emails().fakeEmails()

        //setting it in recycler view
        setUpRecyclerVIew()
        allEmails.log(startUp + " SetupRecyclerview")


        handelBtns()

    }


    private fun setUpRecyclerVIew() {
        mAdapter = SecondRecyclerView(allEmails,this)
        binding.recyclerViewMain.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = mAdapter
        }
        mAdapter.notifyDataSetChanged()
    }


    private fun handelBtns() {
        binding.rvDeleteBtnLayout.show()

        //delete btn click handeling
        binding.IVDeleteBtn.setOnClickListener {
            //On selection mode
            moveToSelectionMode()
        }
        binding.IVBack.setOnClickListener {
            moveToNormalMode()
        }

        binding.IVTickBtn.setOnClickListener {
            mAdapter.deleteEmails()
            moveToNormalMode()
        }
    }

    fun moveToSelectionMode() {
        binding.TVSelectedItems.text = "0"
        binding.rvOkBtnLayout.show()
        binding.rvDeleteBtnLayout.hide()
        mAdapter.selectionMode = true
    }

    fun moveToNormalMode() {
        binding.rvOkBtnLayout.hide()
        binding.rvDeleteBtnLayout.show()
        mAdapter.selectionMode = false


        mAdapter.selectedItems.clear()
        allEmails.unSelectAll()

        Log.d(
            startUp + "zyq",
            "Destory Action mode called" + "\nseletedItems :- " + mAdapter.selectedItems.size
        )
        allEmails.log("DestoryMode-zuq-113")


        mAdapter.notifyDataSetChanged()

    }

    override fun onClick(position: Int) {
        if (mAdapter.selectionMode) {
            //else it is alredy in action mode
            mAdapter.toggleSelection(position)//if select then dislection and vice versa

            //handeling size to write in textview
            handleSizeInTextView()
        } else {
            Toast.makeText(this,allEmails[position].user,Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleSizeInTextView() {
        val size: Int = mAdapter.selectedItems.size()
        binding.TVSelectedItems.text = size.toString()


    }


    override fun onLongClick(position: Int) {
        if (!mAdapter.selectionMode) {
            moveToSelectionMode()
            mAdapter.toggleSelection(position)//if select then dislection and vice versa

            //handeling size to write in textview
            handleSizeInTextView()
        }
    }


}