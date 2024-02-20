package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    void insertBatch(List<DishFlavor> dishFlavorList);

    /**
     * 删除口味通过Dishid
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id =#{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 批量删除口味通过dishId
     * @param dishIds
     */
    void deleteBatchByDishIds(List<Long> dishIds);

    /**
     * 根据菜品id获取菜品口味
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
