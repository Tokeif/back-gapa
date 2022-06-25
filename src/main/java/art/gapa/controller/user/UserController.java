package art.gapa.controller.user;

import art.gapa.common.web.R;
import art.gapa.controller.user.cmd.LoginCMD;
import art.gapa.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
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

    private final UserService service;

    @PostMapping("/login")
    public R<String> login(@RequestBody LoginCMD cmd) {
        return R.ok(service.login(cmd.getPhone(), cmd.getCode()));
    }

}
