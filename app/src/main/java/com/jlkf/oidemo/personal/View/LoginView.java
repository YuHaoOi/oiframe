package com.jlkf.oidemo.personal.View;

import com.jlkf.oidemo.other.base.BaseLoadView;
import com.jlkf.oidemo.other.bean.UserBean;

/**
 * Created by DuoNuo on 2017/9/15.
 */

public interface LoginView extends BaseLoadView{
    void canLogin(boolean canLogin);
    void showUser(UserBean userBean);
}
