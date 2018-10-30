package org.modularframework;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EhCacheTest {
  @Autowired
  private CacheManager cacheManager;

  @Test
  public void 초기화() {
    assertNotNull(cacheManager);
    assertNotNull(cacheManager.getCache("test"));
  }

  @Test
  public void 캐시생성() {
    Cache cache = cacheManager.getCache("test");
    cache.put("modular", "ok");
    assertEquals(cache.get("modular").get(), "ok");
  }

}
