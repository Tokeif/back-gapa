package art.gapa.common.web.controller;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.auth.LoginUserUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JoverZhang
 */
public class BaseController {

    @Setter(onMethod_ = @Autowired)
    private LoginUserUtil loginUserUtil;

    protected LoginUser loginUser() {
        return loginUserUtil.loginUser();
    }

}
