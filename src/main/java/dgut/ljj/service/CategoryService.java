package dgut.ljj.service;

import dgut.ljj.commom.Result;
import dgut.ljj.vo.requestVo.CategoryVo;

/**
 * @author liangjinju
 * @date 2022年04月11日 15:16
 */
public interface CategoryService {
    Result add(CategoryVo categoryVo);

    Result list();

    Result update(CategoryVo categoryVo);

    Result delete(Integer categoryId);
}
