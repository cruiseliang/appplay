package com.touyuanren.perfectplay.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.LoginBean;
import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.di.component.DaggerLoginComponent;
import com.touyuanren.perfectplay.di.module.LoginModule;
import com.touyuanren.perfectplay.presenter.LoginPresenter;
import com.touyuanren.perfectplay.presenter.contract.LoginContract;
import com.touyuanren.perfectplay.ui.widget.LoadingButton;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;


/**
 * Created by Liang on 2017/10/11 0011.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {
    private static final String TAG = "LoginActivity";
    //    @BindView(R.id.tool_bar)
//    Toolbar toolBar;
    @BindView(R.id.txt_mobi)
    EditText txtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout viewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton btnLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {
        Observable<CharSequence> obmob = RxTextView.textChanges(txtMobi);
        Observable<CharSequence> obpwd = RxTextView.textChanges(txtPassword);
        Observable.combineLatest(obmob, obpwd, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mob, CharSequence pwd) {
                return isPhoneValid(mob.toString()) && isPwdValid(pwd.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
//                btnLogin.setEnabled(true);
                //设置某个控件是否可用
                RxView.enabled(btnLogin).call(aBoolean);
            }
        });
        RxView.clicks(btnLogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                mPresenter.login(txtMobi.getText().toString().trim(), txtPassword.getText().toString().trim());
            }
        });
    }

    private boolean isPhoneValid(String phoneNumber) {
        return phoneNumber.length() == 11;
    }

    private boolean isPwdValid(String pwd) {
        return pwd.length() >= 6;
    }

    @Override
    public void showLoading() {
        btnLogin.showLoading();
    }

    @Override
    public void dismissLoading() {
        btnLogin.showButtonText();
    }

    @Override
    public void showError(String msg) {
        btnLogin.showButtonText();
    }

    @Override
    public void checkPhoneError() {
        viewMobiWrapper.setError("手机号不正确");
    }

    @Override
    public void checkPhoneSuccess() {
        viewMobiWrapper.setError("");
        RxView.enabled(btnLogin).call(false);
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        this.finish();
    }
}
