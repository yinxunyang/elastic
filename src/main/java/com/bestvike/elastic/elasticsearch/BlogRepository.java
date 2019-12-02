package com.bestvike.elastic.elasticsearch;

import com.bestvike.elastic.elasticsearch.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES资源库接口
 * ElasticsearchRepository类似jpa，帮我们实现了很多方法
 * String为ID的类型
 */
public interface BlogRepository extends ElasticsearchRepository<Blog, String > {

	/**
	 * 分页查询博客：
	 * Distinct为去除重复的数据
	 * Containing关键字为表示包含
	 * 就是title或summary或content包含关键字就返回内容
	 * 返回的是分页的
	 */
	Page<Blog> findByContent(String content, Pageable pageable);
	Page<Blog> findByTitle(String title, Pageable pageable);
	Page<Blog> findBySummary(String summary, Pageable pageable);

	Page<Blog> findDistinctByContentContainingOrSummaryContainingOrTitleContaining(String title, String summary, String content, Pageable pageable);
}