package com.google.developers.teacup.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.developers.teacup.R
import com.google.developers.teacup.picker.TeaPickerViewAdapter
import com.google.developers.teacup.ui.SettingActivity
import com.google.developers.teacup.ui.add.AddTeaActivity
import com.google.developers.teacup.ui.list.TeaListActivity
import com.google.developers.teacup.ui.timer.TimerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TeaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = TeaViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this,viewModelFactory).get(TeaViewModel::class.java)
        val origin = viewModel.originCountries
        val type = viewModel.teaTypes

        val adapter = TeaPickerViewAdapter()
        pager.adapter = adapter
        adapter.submitData(TeaPickerViewAdapter.PageType.COUNTRIES,origin)
        adapter.submitData(TeaPickerViewAdapter.PageType.TYPES,type)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent? = when (item.itemId) {
            R.id.action_add -> Intent(this, AddTeaActivity::class.java)
            R.id.action_timer -> Intent(this, TimerActivity::class.java)
            R.id.action_list -> Intent(this, TeaListActivity::class.java)
            R.id.action_settings -> Intent(this, SettingActivity::class.java)
            else -> null
        } ?: return super.onOptionsItemSelected(item)

        startActivity(intent)
        return true
    }
}
