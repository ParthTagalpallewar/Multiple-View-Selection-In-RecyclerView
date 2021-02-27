package com.example.multiselectinrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.`interface`.CLickListnerInterface
import com.example.model.Email
import com.example.model.Emails
import com.example.multiselectinrecyclerview.databinding.ActivityMainBinding
import com.example.recyclerView.RecyclerAdapter
import com.example.utils.DeleteItems
import com.example.utils.log
import com.example.utils.startUp
import com.example.utils.unSelectAll

class MainActivity : AppCompatActivity(),CLickListnerInterface {

    lateinit var allEmails: ArrayList<Email>

    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: RecyclerAdapter

    var actionMode:ActionMode? = null
     lateinit var  callback : ActionMode.Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        //getting emails data
        allEmails = Emails().fakeEmails()

        //setting it in recycler view
        setUpRecyclerVIew()
        allEmails.log(startUp+" SetupRecyclerview")

    }



    private fun setUpRecyclerVIew() {
        mAdapter = RecyclerAdapter(allEmails, this)
        binding.recyclerViewMain.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = mAdapter
        }
        mAdapter.notifyDataSetChanged()
    }

    //handeling item clicks
    override fun onClick(position: Int) {
        enableActionMode(position)
    }
    override fun onLongClick(position: Int) {
        enableActionMode(position)
    }




    private fun enableActionMode(position: Int) {
       //if it is not in action mode the start action mode
        if (actionMode == null){
            initActionModeCallback()
            actionMode = startSupportActionMode(callback)
        }

        //else it is alredy in action mode
        mAdapter.toggleSelection(position)//if select then dislection and vice versa

       //handeling size to write in textview
        handleSizeInTextView()
    }

    private fun initActionModeCallback() {
        callback = object : ActionMode.Callback{
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode!!.menuInflater.inflate(R.menu.menu_delete, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                if (item!!.itemId == R.id.action_delete){
                    mAdapter.deleteEmails()
                    mode!!.finish()
                    Log.d(startUp+DeleteItems,"Calling From Delete btn CLicked")
                    return true
                }
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                mAdapter.selectedItems.clear()
                allEmails.unSelectAll()
                mAdapter.notifyDataSetChanged()
                actionMode = null
            }
        }
    }

    private fun handleSizeInTextView() {
        val size: Int = mAdapter.selectedItems.size()
        if (size == 0) {
            actionMode!!.finish()
        } else {
            actionMode!!.apply {
                title = size.toString()
                invalidate()
            }
        }
    }
}