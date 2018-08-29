package com.qtxln.search.repository;

import com.qtxln.search.model.BookGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author QT
 * 2018-07-31 11:38
 */
@Component
public interface BookGoodsRepository extends ElasticsearchRepository<BookGoods,Long> {
}
