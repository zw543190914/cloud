package com.zw.cloud.order.dao;

import com.zw.cloud.order.entity.OrderTbl;
import com.zw.cloud.order.entity.OrderTblExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderTblMapper {
    long countByExample(OrderTblExample example);

    int deleteByExample(OrderTblExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderTbl record);

    int insertSelective(OrderTbl record);

    List<OrderTbl> selectByExample(OrderTblExample example);

    OrderTbl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderTbl record, @Param("example") OrderTblExample example);

    int updateByExample(@Param("record") OrderTbl record, @Param("example") OrderTblExample example);

    int updateByPrimaryKeySelective(OrderTbl record);

    int updateByPrimaryKey(OrderTbl record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table order_tbl
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<OrderTbl> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table order_tbl
     *
     * @mbg.generated
     * @author hewei
     */
    OrderTbl selectOneByExample(OrderTblExample example);
}