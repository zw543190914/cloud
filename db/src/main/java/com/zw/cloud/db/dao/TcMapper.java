package com.zw.cloud.db.dao;

import com.zw.cloud.db.entity.Tc;
import com.zw.cloud.db.entity.TcExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TcMapper {
    long countByExample(TcExample example);

    int deleteByExample(TcExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tc record);

    int insertSelective(Tc record);

    @Select("SELECT first FROM `tc` where id > 19000")
    List<Integer> queryFirst();
    @Select("SELECT second FROM `tc` where id > 19000")
    List<Integer> querySecond();
    @Select("SELECT third FROM `tc` where id > 19000")
    List<Integer> queryThird();
    @Select("SELECT four FROM `tc` where id > 19000")
    List<Integer> queryFour();
    @Select("SELECT five FROM `tc` where id > 19000" )
    List<Integer> queryFive();
    @Select("SELECT blue_first FROM `tc` where id > 19000")
    List<Integer> queryBlueFirst();
    @Select("SELECT blue_second FROM `tc` where id > 19000")
    List<Integer> queryBlueSecond();

    List<Tc> selectByExample(TcExample example);

    Tc selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tc record, @Param("example") TcExample example);

    int updateByExample(@Param("record") Tc record, @Param("example") TcExample example);

    int updateByPrimaryKeySelective(Tc record);

    int updateByPrimaryKey(Tc record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table tc
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<Tc> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table tc
     *
     * @mbg.generated
     * @author hewei
     */
    Tc selectOneByExample(TcExample example);
}