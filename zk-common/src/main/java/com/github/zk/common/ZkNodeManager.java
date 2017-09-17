package com.github.zk.common;

import com.github.zk.common.connector.CuratorConnoctor;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import javax.annotation.Resource;

/**
 * Created by l.zhao on 17-9-16.
 * Desp ZK节点管理 同步
 */
public class ZkNodeManager {

    /**
     * Curator连接
     */
    @Resource
    private CuratorConnoctor connoctor;

    /**
     * 添加节点
     * @param node
     * @return
     */
    private boolean tryCreateNode(String node, CreateMode createMode, String val) throws Exception {
        if (maybePathExists(node)) {
            return false;
        }

        // 默认使用临时节点
        if (null == createMode) {
            createMode = CreateMode.EPHEMERAL;
        }

        connoctor.getInstance()
                .create()
                .withMode(createMode)
                .forPath(node, StringUtils.isEmpty(val) ?
                        StringUtils.EMPTY.getBytes() : val.getBytes());
        return true;
    }

    /**
     * 级联创建
     * @param node
     * @param createMode
     * @param val
     * @return
     * @throws Exception
     */
    private boolean tryCreateNodeDescade(String node, CreateMode createMode, String val) throws Exception {

        if (maybePathExists(node)) {
            return false;
        }

        // 默认使用临时节点
        if (null == createMode) {
            createMode = CreateMode.EPHEMERAL;
        }

        connoctor.getInstance()
                .create()
                .creatingParentsIfNeeded()
                .withMode(createMode)
                .forPath(node, StringUtils.isEmpty(val) ?
                        StringUtils.EMPTY.getBytes() : val.getBytes());
        return true;
    }

    /**
     * 删除节点
     * @param node
     * @return
     * @throws Exception
     */
    private void tryDeleteNode(String node) throws Exception {
        connoctor.getInstance()
                .delete()
                // 强制删除
                .guaranteed()
                .deletingChildrenIfNeeded()
                .forPath(node);

    }

    /**
     * 删除自定义版本
     * @param node
     * @param version
     * @throws Exception
     */
    private void tryDeleteNodeVersion(String node, int version) throws Exception {

        connoctor.getInstance()
                .delete()
                .guaranteed()
                .deletingChildrenIfNeeded()
                .withVersion(version)
                .forPath(node);
    }

    /**
     * 查询节点
     * @param node
     * @param stat
     * @return
     * @throws Exception
     */
    private String tryRetriveNode(String node, Stat stat) throws Exception {

        return connoctor.getInstance()
                .getData()
                .storingStatIn(stat)
                .forPath(node)
                .toString();
    }

    /**
     * 更新节点
     * @param node
     * @param val
     * @throws Exception
     */
    private void tryUpdateNode(String node, String val) throws Exception {
        connoctor.getInstance()
                .setData()
                .forPath(node, val.getBytes());
    }

    /**
     * 更新自定义版本
     * @param node
     * @param val
     * @param version
     * @throws Exception
     */
    private void tryUpdateNodeVersion(String node, String val, int version) throws Exception {
        connoctor.getInstance()
                .setData()
                .withVersion(version)
                .forPath(node, val.getBytes());
    }

    /**
     * 校验节点是否存在
     * @param node
     * @return
     * @throws Exception
     */
    private boolean maybePathExists(String node) throws Exception {
        boolean maybeExists = false;

        Stat stat = connoctor.getInstance().checkExists().forPath(node);

        if (null != stat) {
            maybeExists = true;
        }

        return maybeExists;
    }

}
