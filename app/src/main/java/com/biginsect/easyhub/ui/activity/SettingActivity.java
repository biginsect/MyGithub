package com.biginsect.easyhub.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.biginsect.easyhub.R;
import com.biginsect.easyhub.ui.base.BaseActivity;
import com.biginsect.easyhub.ui.contract.ISettingContract;
import com.biginsect.easyhub.ui.presenter.SettingPresenter;

/**
 * @author biginsect
 * @date 2018/8/17.
 */

public class SettingActivity extends BaseActivity<ISettingContract.ISettingView, ISettingContract.ISettingPresenter>
        implements ISettingContract.ISettingView{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @NonNull
    @Override
    public ISettingContract.ISettingPresenter createPresenter() {
        presenter = new SettingPresenter();
        return presenter;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setToolbarTitle(getString(R.string.setting));
    }

    private void logout(){
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(R.string.warning)
                .setMessage(R.string.warning_logout)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(Activity.RESULT_CANCELED);
                        presenter.logout();
                    }
                })
                .show();
    }
}