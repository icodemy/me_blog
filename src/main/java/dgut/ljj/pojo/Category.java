package dgut.ljj.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分类 实体类
 * @author liangjinju
 * @date 2022/04/11/ 15:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("category")
public class Category {

    @TableId(type = IdType.AUTO)
    private Integer categoryId;//分类id

    private String name;//分类名称

    private String description;//分类描述

    private String count;//文章数量

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;//更新时间

    @TableLogic
    private Integer deleted;//逻辑删除
}
