package com.hino.hearts.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityHomeBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.login.LoginActivity
import com.hino.hearts.ui.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_toolbar_home.*
import kotlinx.android.synthetic.main.nav_header_view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    companion object {
        private const val TWO_HUNDREDS: Long = 200
    }

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var toggle: ActionBarDrawerToggle

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_home)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_notification -> {
                startActivity<NotificationActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        fl_close.onClick {
            val handler = Handler()
            handler.postDelayed({
                drawer_layout.closeDrawer(GravityCompat.START)
            }, TWO_HUNDREDS)
        }

        nav_header_view.onClick {
            val handler = Handler()
            handler.postDelayed({
                toast("Profile item clicked")
            }, TWO_HUNDREDS)

            drawer_layout.closeDrawer(GravityCompat.START)
        }

        cl_pending_transaction.onClick {
            val handler = Handler()
            handler.postDelayed({
                toast("Pending Transactions item clicked")
            }, TWO_HUNDREDS)

            drawer_layout.closeDrawer(GravityCompat.START)
        }

        cl_privacy_prolicy.onClick {
            val handler = Handler()
            handler.postDelayed({
                toast("Privacy Policy item clicked")
            }, TWO_HUNDREDS)

            drawer_layout.closeDrawer(GravityCompat.START)
        }

        cl_help.onClick {
            val handler = Handler()
            handler.postDelayed({
                toast("Help item clicked")
            }, TWO_HUNDREDS)

            drawer_layout.closeDrawer(GravityCompat.START)
        }

        cl_logout.onClick {
            val handler = Handler()
            handler.postDelayed({
                viewModel.logout()
                val intent = Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }, TWO_HUNDREDS)

            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    private fun initLayout() {
        setSupportActionBar(tb_home)
        setupNavigationDrawer()
        addFragment(HomeFragment())
    }

    private fun setupNavigationDrawer() {
        toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            tb_home,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        tb_home.setNavigationIcon(R.drawable.ic_menu)

        Glide.with(this)
            .load(viewModel.imagePath.value)
            .into(iv_add_visit_progress)

        tv_header_view_name.text = viewModel.name.value
        tv_header_view_role.text = viewModel.role.value
        tv_app_version.text = getString(R.string.app_version, viewModel.versionName.value)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction { add(R.id.fl_home_container, fragment) }
        this.title = ""
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
