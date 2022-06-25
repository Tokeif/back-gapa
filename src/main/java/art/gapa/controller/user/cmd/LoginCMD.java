package art.gapa.controller.user.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author JoverZhang
 */
@Data
public class LoginCMD {

    @NotBlank
    @Schema(required = true)
    private String phone;

    @NotBlank
    @Schema(required = true)
    private String code;

}
