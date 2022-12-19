package com.likes.common.util.robin;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 阿布
 * <p>
 * 平滑加权轮询算法(解决nginx无法代理rtmp协议负载均衡)
 */
public class SmoothWeightedRoundRobin {
    private volatile List<Node> pushList = new ArrayList<>(); // 推流服务地址列表
    private volatile List<Node> playList = new ArrayList<>(); // 拉流服务地址列表
    private final ConcurrentHashMap<String, List<Node>> nodeMap = new ConcurrentHashMap<>();

    private final ReentrantLock pollPushLock = new ReentrantLock();
    private final ReentrantLock pollPlayLock = new ReentrantLock();

    private static int pollPushIndex = 0;
    private static int pollPlayIndex = 0;

    public SmoothWeightedRoundRobin() {}

    public SmoothWeightedRoundRobin(Node... nodes) {
        for (Node node : nodes) {
            pushList.add(node);
        }
    }

    public SmoothWeightedRoundRobin(List<Node> nodes) {
        if (null != nodes && nodes.size() > 0) {
            pushList.addAll(nodes);
        }
    }

    @SafeVarargs
    public SmoothWeightedRoundRobin(List<Node>... nodes) {
        for (List<Node> node : nodes) {
            if (node != null && node.size() > 0) {
                nodeMap.put(node.get(0).getRegion(), node);
            }
        }
    }

    public SmoothWeightedRoundRobin(ConcurrentHashMap<String, List<Node>> map) {
        nodeMap.clear();
        nodeMap.putAll(map);
    }

    /**
     * 轮询获取推流地址
     *
     * @return
     */
    public Node pollPushServer() {
        int size = pushList.size();
        if (size == 0) {
            return null;
        }

        if (size == 1) {
            return pushList.get(0);
        }

        Node node;
        try {
            pollPushLock.lock();
            pollPushIndex = pollPushIndex >= size ? 0 : pollPushIndex;
            node = pushList.get(pollPushIndex);
            pollPushIndex++;
        } catch (Exception e) {
            node = pushList.get(0);
        } finally {
            pollPushLock.unlock();
        }
        return node;
    }

    public Node pollPlayServer() {
        if (null == playList || playList.size() == 0) {
            return null;
        }
        int size = playList.size();
        if (size == 1) {
            return playList.get(0);
        }

        Node node;
        try {
            pollPlayLock.lock();
            pollPlayIndex = pollPlayIndex >= size ? 0 : pollPlayIndex;
            node = playList.get(pollPlayIndex);
            pollPlayIndex++;
        } catch (Exception e) {
            node = playList.get(0);
        } finally {
            pollPlayLock.unlock();
        }
        return node;
    }

    /**
     * 轮询获取拉流地址
     *
     * @param serverId 推流服务器id
     * @return
     */
    public Node pollPlayServer(String serverId) {
        if (null == serverId || "".equals(serverId.trim())) {
            return null;
        }

        List<Node> playNodeList = nodeMap.get(serverId);
        if (null == playNodeList || playNodeList.size() == 0) {
            return null;
        }

        int size = playNodeList.size();
        if (size == 1) {
            return playNodeList.get(0);
        }

        Node node;
        try {
            pollPlayLock.lock();
            pollPlayIndex = pollPlayIndex >= size ? 0 : pollPlayIndex;
            node = playNodeList.get(pollPlayIndex);
            pollPlayIndex++;
        } catch (Exception e) {
            node = playNodeList.get(0);
        } finally {
            pollPlayLock.unlock();
        }
        return node;
    }

    public Node select() {
        try {
            pollPushLock.lock();
            return this.selectInner();
        } finally {
            pollPushLock.unlock();
        }
    }

    public Node select(String region) {
        try {
            pollPushLock.lock();
            return this.selectInner(region);
        } finally {
            pollPushLock.unlock();
        }
    }

    private Node selectInner(String region) {
        if (StringUtils.isEmpty(region)) {
            region = "";
        }
        int totalWeight = 0;
        Node maxNode = null;
        int maxWeight = 0;
        List<Node> nodes = nodeMap.get(region);

        if (nodes == null || nodes.size() == 0) {
            for (Entry<String, List<Node>> entry : nodeMap.entrySet()) {
                // 国家匹配失败，则取第一条处理
                nodes = entry.getValue();
                break;
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            totalWeight += n.getWeight();
            // 每个节点的当前权重要加上原始的权重
            n.setCurrentWeight(n.getCurrentWeight() + n.getWeight());
            // 保存当前权重最大的节点
            if (maxNode == null || maxWeight < n.getCurrentWeight()) {
                maxNode = n;
                maxWeight = n.getCurrentWeight();
            }
        }

        // 被选中的节点权重减掉总权重
        maxNode.setCurrentWeight(maxNode.getCurrentWeight() - totalWeight);
        // nodeList.forEach(node -> System.out.print(node.getCurrentWeight()));
        return maxNode;
    }

    private Node selectInner() {
        int totalWeight = 0;
        Node maxNode = null;
        int maxWeight = 0;

        for (int i = 0; i < pushList.size(); i++) {
            Node n = pushList.get(i);
            totalWeight += n.getWeight();
            // 每个节点的当前权重要加上原始的权重
            n.setCurrentWeight(n.getCurrentWeight() + n.getWeight());
            // 保存当前权重最大的节点
            if (maxNode == null || maxWeight < n.getCurrentWeight()) {
                maxNode = n;
                maxWeight = n.getCurrentWeight();
            }
        }
        // 被选中的节点权重减掉总权重
        maxNode.setCurrentWeight(maxNode.getCurrentWeight() - totalWeight);
        // nodeList.forEach(node -> System.out.print(node.getCurrentWeight()));
        return maxNode;
    }

    public SmoothWeightedRoundRobin setPlayList(List<Node> playList) {
        this.playList = playList;
        return this;
    }

    public SmoothWeightedRoundRobin setPushList(List<Node> pushList) {
        this.pushList = pushList;
        return this;
    }
}
