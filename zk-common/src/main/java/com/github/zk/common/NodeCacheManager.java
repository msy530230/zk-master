package com.github.zk.common;

import com.github.zk.common.connector.CuratorConnoctor;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
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
    public void registe(String node, NodeCacheListener nodeCacheListener, ExecutorService executorService) throws Exception {
        NodeCache nodeCache = new NodeCache(connoctor.getInstance(), node, false);

        nodeCache.start(true);

        if (null != executorService) {
            nodeCache.getListenable().addListener(nodeCacheListener, executorService);
        } else {
            nodeCache.getListenable().addListener(nodeCacheListener);
        }
    }

    /**
     * 注册子目录监听
     * @param node
     * @param pathChildrenCacheListener
     * @param executorService
     */
    public void registe(String node, PathChildrenCacheListener pathChildrenCacheListener, ExecutorService executorService) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(
                connoctor.getInstance(), node, false);
        // TODO 暂不定义其他特性
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        if (null != executorService) {
            pathChildrenCache.getListenable().addListener(pathChildrenCacheListener, executorService);
        } else {
            pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        }
    }

}
