package dgut.ljj.vo.requestVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author liangjinju
 * @date 2022/04/10/ 0:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
