package com.hino.hearts.ui.home

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityHomeBinding
import com.hino.hearts.ui.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_home)

        initLayout()
//        initObserver()
        initViewModel()
        initEvent()
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
    }

    private fun initLayout() {
        setSupportActionBar(tb_home)
        addFragment(HomeFragment())

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            tb_home,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction { add(R.id.fl_home_container, fragment) }
        this.title = ""
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
