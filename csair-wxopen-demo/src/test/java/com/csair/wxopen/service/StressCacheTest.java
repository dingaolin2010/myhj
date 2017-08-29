package com.csair.wxopen.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csair.wxopen.BootApiApplication;
import com.csair.wxopen.core.service.CacheService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApiApplication.class)
@WebAppConfiguration
public class StressCacheTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	CacheService cacheService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		System.out.println("test start");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDownAfterClass() throws Exception {
		System.out.println("test end");
		Thread.sleep(2000);
	}

	@Test
	public void test() {

		try {
			long totaltime = System.currentTimeMillis();

			int threadcount = 50;
			int count = 10000;
			List<Long> result = Collections.synchronizedList(new ArrayList<Long>());

			CountDownLatch startSignal = new CountDownLatch(1);
			CountDownLatch doneSignal = new CountDownLatch(threadcount);

			for (int i = 0; i < threadcount; i++) {
				new Thread(new Task(String.valueOf(i), result, count, startSignal, doneSignal)).start();
			}

			// 等待所有线程都实例化完才开始
			startSignal.countDown();

			doneSignal.await();

			if (result.size() == threadcount) {
				long total = 0;
				for (long l : result) {
					total += l;
				}

				System.out.println(new StringBuffer().append("cache test consume: ").append(total)
						.append(", average boundle consume: ").append(total / (long) result.size())
						.append(", average per request :").append(total / (long) result.size() / (long) count));
			}

			totaltime = System.currentTimeMillis() - totaltime;

			System.out.println("total consume: " + totaltime);

			Thread.sleep(5000);

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	class Task implements java.lang.Runnable {

		String name;
		List<Long> result;
		int count;
		CountDownLatch start;
		CountDownLatch done;

		public Task(String n, List<Long> r, int c, CountDownLatch start, CountDownLatch done) {
			name = n;
			count = c;
			result = r;
			this.start = start;
			this.done = done;
		}

		public void run() {

			try {

				start.await();
				long time = System.currentTimeMillis();

				for (int i = 0; i < count; i++) {
					cacheService.put(String.valueOf(i), i);

					Assert.assertTrue(cacheService.get(String.valueOf(i), Integer.class) == i);

					String nodename = new StringBuilder("node").append(name).append(i).toString();

					Node node = new Node();
					node.setName(nodename);

					cacheService.put(node.getName(), node);
					Assert.assertEquals(((Node) cacheService.get(node.getName(), Node.class)).getName(), nodename);

				}

				time = System.currentTimeMillis() - time;

				result.add(time);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				done.countDown();
			}

		}

	}

}
