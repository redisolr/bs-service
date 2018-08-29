package com.qtxln.search.mapper;

import com.qtxln.search.model.BookGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-07-31 11:19
 */
@Mapper
@Repository
public interface BookGoodsMapper {

    List<BookGoods> selectAll();

    BookGoods selectById(Long id);
}
