package com.hino.hearts.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.hino.hearts.R
import com.hino.hearts.adapter.AddVisitButtonAdapter
import com.hino.hearts.databinding.ActivityHomeBinding
import com.hino.hearts.ui.BaseActivity
import com.hino.hearts.ui.appointment.AppointmentDetailActivity
import com.hino.hearts.ui.account.AccountListActivity
import com.hino.hearts.ui.login.LoginActivity
import com.hino.hearts.ui.notification.NotificationActivity
import com.hino.hearts.ui.pendingtransactions.PendingTransactionsActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.layout_add_visit_buttons.*
import kotlinx.android.synthetic.main.layout_toolbar_home.tb_home
import kotlinx.android.synthetic.main.nav_header_view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>(), AddVisitButtonAdapter.OnClick {

    companion object {
        private const val TWO_HUNDREDS: Long = 200
        private const val TWO = 2
        private const val SIX = 6
    }

    private val context = this

    private lateinit var toggle: ActionBarDrawerToggle

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var addVisitButtonAdapter: AddVisitButtonAdapter

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

    override fun onBackPressed() {
        when(layout_add_visit_button.visibility == View.VISIBLE){
            true->{
                hideAddVisitButton()
            }
            false->{
                super.onBackPressed()
            }
        }
    }

    override fun initObserver() {
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

    override fun initEvent() {
        layout_custom_loading.onClick {

        }

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
            startActivity<PendingTransactionsActivity>()

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

        when(viewModel.role.value == "Sales"){
            true-> {
                layout_add_visit_button.onClick {
                    hideAddVisitButton()
                }
            }
        }
    }

    override fun onItemViewClicked(name: Int) {
        toast(getString(name))
        hideAddVisitButton()

        when (name) {
            R.string.appointment -> startActivity<AppointmentDetailActivity>(
                AppointmentDetailActivity.PARAM_ACCOUNT_NAME to "PT Dihardja Software",
                AppointmentDetailActivity.PARAM_OPPORTUNITY to "20 Logistic Truck",
                AppointmentDetailActivity.PARAM_OPPORTUNITY_ENABLED to true,
                AppointmentDetailActivity.PARAM_ACTIVITY_DETAIL to "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            R.string.accounts -> startActivity<AccountListActivity>()
        }
    }

    fun showAddVisitButton() {
        layout_add_visit_button.visibility = View.VISIBLE
    }

    fun hideAddVisitButton() {
        layout_add_visit_button.visibility = View.GONE
    }

    fun showLoading() {
        layout_custom_loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        layout_custom_loading.visibility = View.GONE
    }

    private fun initLayout() {
        setSupportActionBar(tb_home)
        setupNavigationDrawer()
        addFragment(HomeFragment())

        when(viewModel.role.value == "Sales"){
            true-> {
                addVisitButtonAdapter = AddVisitButtonAdapter(this)
                addVisitButtonAdapter.setData(viewModel.addVisitButtonList.value!!)

                val gridLayoutManager = GridLayoutManager(this, SIX)
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(i: Int): Int {
                        return TWO
                    }
                }

                rv_add_visit_buttons.layoutManager = gridLayoutManager
                rv_add_visit_buttons.adapter = addVisitButtonAdapter
            }
        }
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
//            .load(viewModel.imagePath.value)
            .load(R.drawable.header)
            .into(iv_navigation_drawer)

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
