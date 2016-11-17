package com.xnjr.mall.bo;

import java.util.List;

import com.xnjr.mall.bo.base.IPaginableBO;
import com.xnjr.mall.domain.Category;

/**
 * @author: xieyj 
 * @since: 2016年11月17日 上午8:32:09 
 * @history:
 */
public interface ICategoryBO extends IPaginableBO<Category> {

    public boolean isCategoryExist(String code);

    public String saveCategory(Category data);

    public int removeCategory(String code);

    public int refreshCategory(Category data);

    public List<Category> queryCategoryList(Category condition);

    public Category getCategory(String code);
}
