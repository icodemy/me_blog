package dgut.ljj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dgut.ljj.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author liangjinju
 * @date 2022/04/08/ 21:01
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

//    /**
//     * 获得密码
//     * @param username 用户名
//     */
//    @Select("select password from user where username = #{username}")
//    String getPassword(@Param("username") String username);
//
//    /**
//     * 获得用户角色
//     * @param username 用户名
//     * @return user/admin
//     */
//    @Select("select role from user where username = #{username}")
//    String getRole(@Param("role") String username);
//
//    /**
//     * 修改密码
//     */
//    void updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);
//
//    /**
//     * 获得存在的用户
//     */
//    List<String> getUser();
//
//    /**
//     * 封号
//     */
//    void banUser(String username);
//
//    /**
//     * 检查用户状态
//     */
//    int checkUserBanStatus(String username);
//
//    /**
//     * 获得用户角色默认的权限
//     */
//    String getRolePermission(String username);
//
//    /**
//     * 获得用户的权限
//     */
//    String getPermission(String username);
}
