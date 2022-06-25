package art.gapa.controller.user;

import art.gapa.common.auth.JwtUtil;
import art.gapa.common.web.R;
import art.gapa.controller.user.cmd.LoginCMD;
import art.gapa.domain.user.UserInfo;
import art.gapa.domain.user.repository.UserRepository;
import art.gapa.domain.user.service.UserService;
import art.gapa.domain.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final JwtUtil jwtUtil;

    private final UserRepository repository;

    private final UserService service;

    private final WalletService walletService;

    @PostMapping("/login")
    @Transactional(rollbackFor = Exception.class)
    public R<String> login(@RequestBody LoginCMD cmd) {
        UserInfo user = repository.findByPhone(cmd.getPhone());

        // 用户未注册
        if (user == null) {
            user = service.register(cmd.getPhone(), cmd.getPhone());
            walletService.createWallet(user.getId());
        }

        Assert.isTrue("000000".equals(cmd.getCode()), "验证码不正确");

        return R.ok(jwtUtil.build(user));
    }

}
