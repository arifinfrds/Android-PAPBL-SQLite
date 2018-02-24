package com.arifinfrds.papbl_sqlite.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.arifinfrds.papbl_sqlite.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.arifinfrds.papbl_sqlite.ui.barang.BarangFragment
import com.arifinfrds.papbl_sqlite.ui.mitra_dagang.MitraDagangFragment
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.SearchView
import com.arifinfrds.papbl_sqlite.extension.toast
import com.arifinfrds.papbl_sqlite.ui.barang.BarangContract
import com.arifinfrds.papbl_sqlite.ui.barang.BarangPresenterImpl
import kotlinx.android.synthetic.main.fragment_barang.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BarangContract.View {

    private var presenter: BarangPresenterImpl? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter = BarangPresenterImpl(this, this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        displayView(R.id.nav_barang)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.action_search)

        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (!searchView.isIconified()) {
                        searchView.setIconified(true);
                    }
                    searchItem.collapseActionView();
                    if (p0 != null) {
                        presenter?.attemptFetch(p0!!)
                    } else {
                        presenter?.attemptShowToasMessage("Search cannot be empty.")
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun navigateToSearchResultActivity() {
        val intent = Intent(this, SearchResultsActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_search -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        displayView(id)
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    private fun displayView(viewId: Int) {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)

        when (viewId) {
            R.id.nav_barang -> {
                title = resources.getString(R.string.title_barang)
                fragment = BarangFragment()
            }
            R.id.nav_mitra_dagang -> {
                title = resources.getString(R.string.title_mitra_dagang)
                fragment = MitraDagangFragment()
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_main, fragment)
            ft.commit()
        }

        // set the toolbar title
        if (supportActionBar != null) {
            supportActionBar!!.setTitle(title)
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
    }

    override fun showToastMessage(message: String) {
        toast(message)
    }

    override fun showDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }

    override fun showIDBarangError() {
        TODO("do nothing")
    }

    override fun showNamaBarangError() {
        TODO("do nothing")
    }

    override fun showBrandBarangError() {
        TODO("do nothing")
    }

    override fun emptyInput() {
        TODO("do nothing")
    }
}
