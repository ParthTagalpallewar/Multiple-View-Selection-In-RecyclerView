package com.example.multiselectinrecyclerview.RecyclerWithActionMode

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.util.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiselectinrecyclerview.R
import com.example.multiselectinrecyclerview.RecyclerWithActionMode.`interface`.CLickListnerInterface
import com.example.multiselectinrecyclerview.RecyclerWithActionMode.adapter.RecyclerAdapter
import com.example.multiselectinrecyclerview.model.Email
import com.example.multiselectinrecyclerview.model.Emails
import com.example.multiselectinrecyclerview.databinding.ActivityRecyclerViewWithActionViewBinding
import com.example.multiselectinrecyclerview.utils.DeleteItems
import com.example.multiselectinrecyclerview.utils.log
import com.example.multiselectinrecyclerview.utils.startUp
import com.example.multiselectinrecyclerview.utils.unSelectAll

class RecyclerViewWithActionView : AppCompatActivity(),CLickListnerInterface {

    lateinit var allEmails: ArrayList<Email>

    lateinit var binding: ActivityRecyclerViewWithActionViewBinding
    lateinit var mAdapter: RecyclerAdapter

    var actionMode:ActionMode? = null
    lateinit var  callback : ActionMode.Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewWithActionViewBinding.inflate(layoutInflater)
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
                Log.d(startUp+"zyq","Destory Action mode called" + "\nseletedItems :- "+mAdapter.selectedItems.size)
                allEmails.log("DestoryMode-zuq-113")
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