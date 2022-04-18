package dgut.ljj;

import dgut.ljj.mapper.UserMapper;
import dgut.ljj.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MeBlogApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("visitor");
        user.setPassword("123456");
        final int insert = userMapper.insert(user);
        if(insert > 0){
            System.out.println("insert success");
        }

    }

    @Test
    void test1(){
        System.out.println(userMapper.selectList(null));
    }

}
