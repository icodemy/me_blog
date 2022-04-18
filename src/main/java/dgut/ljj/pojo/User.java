package dgut.ljj.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liangjinju
 * @date 2022/04/08/ 20:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;//用户id

    private String username;//用户名

    private String password;//密码

    private String avatar;//头像

    private String role;//用户角色

    private String permission;//用户权限

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;//更新时间

    private Integer status;//用户状态

    @TableLogic
    private Integer deleted;//逻辑删除
}
