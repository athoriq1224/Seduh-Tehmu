package com.google.developers.teacup.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.developers.teacup.R
import com.google.developers.teacup.paging.TeaViewHolder
import com.google.developers.teacup.ui.SettingActivity
import com.google.developers.teacup.ui.add.AddTeaActivity
import kotlinx.android.synthetic.main.activity_tea_list.*
import kotlinx.android.synthetic.main.content_main.*

class TeaListActivity : AppCompatActivity() {

    lateinit var viewModel: TeaListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tea_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        val factory = TeaListViewModelFactory.createFactory(this, viewModel.sortBy)
        viewModel = ViewModelProvider(this, factory).get(TeaListViewModel::class.java)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            val intent = Intent(this, AddTeaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAction() {

        val callback = Callback()
        val itemTouchHelper = ItemTouchHelper(callback)

        itemTouchHelper.attachToRecyclerView(recycler_view)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_list, menu)
        val menuItem = menu.findItem(R.id.action_filter)
        viewModel.teaFilter.observe(this, Observer {
            when (it) {
                FilterType.NONE -> {
                    menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_list)
                    setTitle(R.string.app_name)
                }
                FilterType.FAVORITES -> {
                    menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_filter_list)
                    setTitle(getString(R.string.app_name) + getString(R.string.filter_results))
                }
                FilterType.SEARCH_RESULTS -> {
                    menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_search)
                    setTitle(getString(R.string.app_name) + getString(R.string.search_results))
                }
                else -> {
                    menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_list)
                    setTitle(R.string.app_name)
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                viewModel.toggleFilter()
                return true
            }
            R.id.action_settings -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class Callback : ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val tea = (viewHolder as TeaViewHolder).getTea()
            viewModel.delete(tea)
        }
    }
}
