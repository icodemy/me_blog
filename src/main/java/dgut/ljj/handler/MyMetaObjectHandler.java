package dgut.ljj.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * mybatis-plus 填充配置
 * @author liangjinju
 * @date 2022/4/8 20:51
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        //log.info("start insert.............");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        //log.info("start insert.............");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}