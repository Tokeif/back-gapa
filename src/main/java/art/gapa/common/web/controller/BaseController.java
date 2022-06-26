package art.gapa.common.web.controller;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.auth.LoginUserUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JoverZhang
 */
public abstract class BaseController {

    public static final String NEW_PRODUCT = "新品";

    public static final String MARKET = "市场";

    public static final String USER = "用户";

    public static final String WALLET = "钱包";

    public static final String ORDER = "订单";

    @Setter(onMethod_ = @Autowired)
    private LoginUserUtil loginUserUtil;

    protected LoginUser loginUser() {
        return loginUserUtil.loginUser();
    }

}
