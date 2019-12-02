package com.bestvike.elastic;

import com.bestvike.elastic.elasticsearch.Blog;
import com.bestvike.elastic.elasticsearch.BlogRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogTest {

	@Autowired
	private BlogRepository blogRepository;

	@After
	public void init(){

		//先清除所有数据
		blogRepository.deleteAll();

		//添加数据
		blogRepository.save(new Blog("登鹳雀楼", "王之涣", "白日依山尽，黄河入海流。欲穷千里目，更上一层楼。"));
		blogRepository.save(new Blog("静夜思", "李白", "床前明月光，疑是地上霜。举头望明月，低头思故乡。"));
		blogRepository.save(new Blog("江雪", "柳宗元", "千山鸟飞绝，万径人踪灭。孤舟蓑笠翁，独钓寒江雪。"));
		blogRepository.save(new Blog("九月九日忆山东兄弟", "王维", "独在异乡为异客，每逢佳节倍思亲。遥知兄弟登高处，遍插茱萸少一人。"));
		blogRepository.save(new Blog("登楼", "杜甫", "花近高楼伤客心，万方多难此登临。锦江春色来天地，玉垒浮云变古今。北极朝廷终不改，西山寇盗莫相侵。可怜后主还祠庙，日暮聊为《梁甫吟》。"));

	}

	@Test
	public void test(){
		/**
		 * Pageable 是Spring Data库中定义的一个接口，该接口是所有分页相关信息的一个抽象，通过该接口，我们可以得到和分页相关所有信息（例如pageNumber、pageSize等），这样，Jpa就能够通过pageable参数来得到一个带分页信息的Sql语句。
		 * 0表示从第0条开始
		 * 20表示每页最多20条数据
		 */
		Pageable pageable = new PageRequest(0, 20);
//        String title = "登";
//        String summary = "";
//        String content = "日";
//        Page<Blog> page = blogRepository.findDistinctByContentContainingOrSummaryContainingOrTitleContaining(content, content, content, pageable);
		Page<Blog> page = blogRepository.findAll(pageable);
		System.out.println("------------------------------------------------------------------");

		System.out.println("count=" + page.getTotalElements());

		for (Blog blog : page.getContent()) {
			System.out.println(blog.toString());
		}

		System.out.println("------------------------------------------------------------------");
		Assert.assertEquals(page.getTotalElements(), 5);

	}
}