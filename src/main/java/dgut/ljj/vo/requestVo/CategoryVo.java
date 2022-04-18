package dgut.ljj.vo.requestVo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author liangjinju
 * @date 2022/04/11/ 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

    private Integer categoryId;

    @NotBlank(message = "分类名称不能为空")
    private String name;

    @NotBlank(message = "分类描述不能为空")
    private String description;
}
