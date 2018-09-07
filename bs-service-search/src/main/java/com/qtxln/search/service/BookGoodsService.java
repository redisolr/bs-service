package com.qtxln.search.service;

import com.qtxln.exception.BsSearchException;
import com.qtxln.model.goods.Goods;
import com.qtxln.search.mapper.BookGoodsMapper;
import com.qtxln.search.model.BookGoods;
import com.qtxln.search.repository.BookGoodsRepository;
import com.qtxln.transport.InvokerResult;
import com.qtxln.transport.PageData;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * @author QT
 * 2018-07-31 11:24
 */
@Service
public class BookGoodsService {
    private final BookGoodsMapper bookGoodsMapper;
    private final BookGoodsRepository bookGoodsRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final String SORT_DESC = "1";

    @Autowired
    public BookGoodsService(BookGoodsMapper bookGoodsMapper, BookGoodsRepository bookGoodsRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.bookGoodsMapper = bookGoodsMapper;
        this.bookGoodsRepository = bookGoodsRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    /**
     * 批量保存
     *
     * @return
     */
    public InvokerResult saveAll() {
        List<BookGoods> bookGoods = bookGoodsMapper.selectAll();
        bookGoods.forEach(bookGoods1 -> {
            String[] split = bookGoods1.getGoodsImage().split(",");
            bookGoods1.setGoodsImage(split[0]);
        });
        bookGoodsRepository.saveAll(bookGoods);
        return InvokerResult.getInstance();
    }

    public Goods findById(Long id) throws BsSearchException {
        Optional<BookGoods> optionalBookGoods = bookGoodsRepository.findById(id);
        if (!optionalBookGoods.isPresent()) {
            throw new BsSearchException(BsSearchException.ErrorSearchEnum.RESULT_NULL);
        }
        BookGoods bookGoods = optionalBookGoods.get();
        Goods goods = new Goods();
        BeanUtils.copyProperties(bookGoods, goods);
        return goods;
    }

    public InvokerResult deleteAll() {
        bookGoodsRepository.deleteAll();
        return InvokerResult.getInstance();
    }

    public void save(Long id) {
        BookGoods bookGoods = bookGoodsMapper.selectById(id);
        String[] split = bookGoods.getGoodsImage().split(",");
        bookGoods.setGoodsImage(split[0]);
        bookGoodsRepository.save(bookGoods);
    }

    public void update(Long id) {
        deleteById(id);
        save(id);
    }

    public void deleteById(Long id) {
        bookGoodsRepository.deleteById(id);
    }

    public InvokerResult search(String keyWord, int pageNum, int pageSize, String sort, String direction) {
        Sort sort1;
        if (Objects.equals(direction, SORT_DESC)) {
            sort1 = Sort.by(Sort.Direction.DESC, sort);
        } else {
            sort1 = Sort.by(Sort.Direction.ASC, sort);
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize, sort1);

        SearchQuery searchQuery;
        if (StringUtils.isEmpty(keyWord)) {
            searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).build();
        } else {
            searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(keyWord)).withPageable(pageable).build();
        }
        List<BookGoods> bookGoods = elasticsearchTemplate.queryForList(searchQuery, BookGoods.class);
        long count = elasticsearchTemplate.count(searchQuery, BookGoods.class);

        PageData pageData = new PageData();
        pageData.setTotal(count);
        pageData.setPageSize(pageable.getPageSize());
        pageData.setPageNum(pageable.getPageNumber() + 1);
        pageData.setData(bookGoods.toArray());
        return InvokerResult.getInstance(pageData);
    }
}
