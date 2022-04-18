package dgut.ljj.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangjinju
 * @date 2022/04/10/ 17:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private Integer userId;//用户id

    private String username;//用户名

    private String avatar;//头像

    private String role;//用户角色
}
