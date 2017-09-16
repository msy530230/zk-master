package com.github.zk.common;

import com.github.zk.common.connector.CuratorConnoctor;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sun.misc.Cache;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * Created by l.zhao on 17-9-16.
 * Desp NodeCache 监听
 */
public class NodeCacheManager {

    @Resource
    private CuratorConnoctor connoctor;

    /**
     * 注册监听
     * @param node
     * @param nodeCacheListener
     * @throws Exception
     */
    public void regist(String node, NodeCacheListener nodeCacheListener, ExecutorService executorService) throws Exception {
        NodeCache nodeCache = new NodeCache(connoctor.getInstance(), node, false);

        nodeCache.start(true);

        if (null != executorService) {
            nodeCache.getListenable().addListener(nodeCacheListener, executorService);
        } else {
            nodeCache.getListenable().addListener(nodeCacheListener);
        }
    }

}
