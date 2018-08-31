package com.biginsect.easyhub.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import com.biginsect.easyhub.R
import com.biginsect.easyhub.constant.ToastType
import com.biginsect.easyhub.ui.adapter.EventListAdapter
import com.biginsect.easyhub.ui.base.BaseListActivity
import com.biginsect.easyhub.ui.contract.IHomePageContract
import com.biginsect.easyhub.ui.presenter.HomePagePresenter
import com.biginsect.easyhub.util.ActivitiesManager
import com.biginsect.easyhub.util.ToastUtils
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.toolbar_homepage.*

/**
 * @author big insect
 * @date 2018/8/30.
 */
class HomePageActivity :BaseListActivity<IHomePageContract.IHomePageView, IHomePageContract.IHomePagePresenter, EventListAdapter>(),
        IHomePageContract.IHomePageView{

    private var mLastBackPressedTime = 0L
    /**两次点击时间间隔在此范围内则退出app*/
    private val mTimeInterval = 1000L

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setToolbar()
        setRecyclerView()
        setDrawerLayout()
    }

    private fun setRecyclerView(){
        rv_project_list.layoutManager = LinearLayoutManager(this)
        rv_project_list.adapter = mAdapter
    }

    private fun setDrawerLayout(){
        homepage_nav.setNavigationItemSelectedListener {
            drawer_layout_left.closeDrawers()
            true
        }
        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout_left, home_page_toolbar,
                R.string.drawer_layout_open, R.string.drawer_layout_close)
        drawerToggle.syncState()
        drawer_layout_left.addDrawerListener(drawerToggle)
    }

    private fun setToolbar(){
        setSupportActionBar(home_page_toolbar)
        val actionBar = supportActionBar
        if (null != actionBar){
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_homepage
    }


    override fun getAdapter(): EventListAdapter {
        return EventListAdapter(this)
    }

    override fun createPresenter(): IHomePageContract.IHomePagePresenter {
        presenter = HomePagePresenter()
        return presenter
    }

    override fun restartApp() {
        activity.finishAffinity()
        startActivity(Intent(activity, SplashActivity::class.java))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (System.currentTimeMillis() - mLastBackPressedTime < mTimeInterval){
            ToastUtils.cancel()
            ActivitiesManager.appExit(this)
        }else{
            mLastBackPressedTime = System.currentTimeMillis()
            ToastUtils.showLongToast(this, "Press again to exit.", ToastType.INFO)
        }
    }

    private fun logout(){
        AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(R.string.warning)
                .setMessage(R.string.warning_logout)
                .setNegativeButton(R.string.cancel) { dialog, which -> dialog?.dismiss() }
                .setPositiveButton(R.string.okay){dialog, which ->
                    setResult(Activity.RESULT_CANCELED)
                    presenter.logout()
                    dialog.dismiss()
                }
                .show()
    }

    companion object {
        fun show(context: Context){
            context.startActivity(Intent(context, HomePageActivity::class.java))
        }
    }
}