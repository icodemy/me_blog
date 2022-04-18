package dgut.ljj.controller.admin;

import dgut.ljj.commom.Result;
import dgut.ljj.service.CategoryService;
import dgut.ljj.vo.requestVo.CategoryVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author liangjinju
 * @date 2022/04/11/ 15:18
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @ApiOperation("添加分类")
    @RequiresAuthentication
    @RequiresRoles("admin")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated CategoryVo categoryVo){
        return categoryService.add(categoryVo);
    }

    @ApiOperation("获取分类列表")
    @RequiresAuthentication
    @GetMapping("/list")
    public Result list(){
        return categoryService.list();
    }

    @ApiOperation("更新分类")
    @RequiresAuthentication
    @RequiresRoles("admin")
    @PostMapping("/update")
    public Result update(@RequestBody @Validated CategoryVo categoryVo){
        return categoryService.update(categoryVo);
    }

    @ApiOperation("删除分类")
    @RequiresAuthentication
    @RequiresRoles("admin")
    @DeleteMapping("/delete/{id}")
    public Result update(@NotBlank(message = "分类id为空") @PathVariable("id") Integer categoryId){
        return categoryService.delete(categoryId);
    }
}
