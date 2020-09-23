package com.example.testapp.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var catImageFragment: Fragment1
    lateinit var catFactFragment: Fragment2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        catImageFragment = supportFragmentManager.findFragmentById(R.id.fragment1) as Fragment1
        catFactFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as Fragment2
        loadFragments()
    }

    private fun loadFragments() {
        if (checkForInternetConnection()) {
            catImageFragment.loadCatImage()
            catFactFragment.loadCatFacts()
        } else {
            openInternetConnectionDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.refresh -> {
            refreshFragments()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun refreshFragments() {
        if (checkForInternetConnection()) {
            catImageFragment.refreshCatImage()
            catFactFragment.refreshCatFacts()
        } else {
            openInternetConnectionDialog()
        }
    }

    private fun checkForInternetConnection(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun openInternetConnectionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Отсутствует интернет соединение")
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->

        }

        builder.show()
    }

}