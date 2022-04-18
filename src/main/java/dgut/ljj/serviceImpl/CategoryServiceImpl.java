package dgut.ljj.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dgut.ljj.commom.Result;
import dgut.ljj.mapper.CategoryMapper;
import dgut.ljj.mapper.UserMapper;
import dgut.ljj.pojo.Category;
import dgut.ljj.service.CategoryService;
import dgut.ljj.vo.requestVo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liangjinju
 * @date 2022/04/11/ 15:16
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result add(CategoryVo categoryVo) {

        // 查看分类是否已经存在
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("name",categoryVo.getName());

        final List<Category> categories = categoryMapper.selectList(wrapper);
        if(categories.size() >0){
            return Result.FAIL("该分类已存在");
        }

        // 不存在则添加
        Category category = new Category();
        category.setName(categoryVo.getName());
        category.setDescription(categoryVo.getDescription());
        final int insert = categoryMapper.insert(category);
        if(insert > 0){
            return Result.SUCCESS(200,"分类添加成功",null);
        }
        return Result.FAIL("添加失败");
    }

    @Override
    public Result list() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("category_id");
        final List<Category> categories = categoryMapper.selectList(wrapper);
        return Result.SUCCESS(200,"",categories);
    }

    @Override
    public Result update(CategoryVo categoryVo) {
        Category category = new Category();
        category.setCategoryId(categoryVo.getCategoryId());
        category.setName(categoryVo.getName());
        category.setDescription(categoryVo.getDescription());
        final int i = categoryMapper.updateById(category);
        if(i > 0){
            return Result.SUCCESS(200,"分类更新成功",null);
        }
        return Result.FAIL("更新失败");
    }

    @Override
    public Result delete(Integer categoryId) {
        final int i = categoryMapper.deleteById(categoryId);
        if( i > 0 ){
            return Result.SUCCESS(200,"分类删除成功",null);
        }
        return Result.FAIL("删除失败");
    }
}
