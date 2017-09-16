package com.github.zk.common.connector;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Properties;

/**
 * Created by l.zhao on 17-9-16.
 * Desp TODO.
 */
public class CuratorConnoctor implements ZkConnector {

    // 默认超时时间和Curator保持一致 60000ms
    private final int default_sessionTimeOut = 60000;

    // 默认时间保持一致 15000ms
    private final int default_connectionTimeOut = 15000;

    // 默认命名空间 主目录
    private final String default_namespace = StringUtils.EMPTY;

    // 重试策略 最大五次 上限5000ms间隔
    private final RetryPolicy default_retryPolicy = new BoundedExponentialBackoffRetry(1000, 5000, 5);

    /**
     * 会话超时
     */
    private int sessionTimeOut;

    /**
     * 连接超时
     */
    private int connectionTimeOut;

    /**
     * 命名空间
     */
    private String nameSpace;

    /**
     * 重试策略
     */
    private RetryPolicy retryPolicy;

    /**
     * ZK地址
     */
    private String bootStarp;

    private CuratorFramework instance = initConnetion();

    // TODO 先返回Curator的对象 后期做封装

    /**
     * 初始化连接
     * @return
     */
    private CuratorFramework initConnetion() {

        assert StringUtils.isNotBlank(bootStarp) : "必须指定明确的ZK环境";
        // TODO 包装builder和start
        CuratorFramework connetion = CuratorFrameworkFactory
                .builder()
                .connectString(bootStarp)
                .connectionTimeoutMs(connectionTimeOut)
                .sessionTimeoutMs(sessionTimeOut)
                .retryPolicy(retryPolicy)
                .namespace(nameSpace)
                .build();

        connetion.start();
        return connetion;
    }

    /**
     * 获取连接
     * @return
     */
    public CuratorFramework getInstance() {
        return this.instance;
    }

    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }
}
