package com.zw.cloud.netty.web.dao.poem;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.cloud.netty.web.entity.poem.Poem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-01-17
 */
public interface PoemMapper extends BaseMapper<Poem> {

    @Select("select * from poem \n" +
            "    where match(title,content) against(#{title});")
    List<Poem> queryByTitleOrContent(@Param("title") String title);

    @Select("select * from poem  where title = #{title} limit 1;")
    Poem queryByTitle(@Param("title") String title);
}
