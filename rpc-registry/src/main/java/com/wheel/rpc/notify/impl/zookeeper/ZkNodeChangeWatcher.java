package com.wheel.rpc.notify.impl.zookeeper;

import java.util.List;
import org.I0Itec.zkclient.IZkChildListener;

import com.google.common.collect.Lists;
import com.wheel.rpc.core.model.ServiceProviderNode;

/**
 * 结点变化的watcher
 * 1:创建子结点(服务上线)
 * 2:删除之结点(服务下线)
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月4日 上午11:16:19
 */
public class ZkNodeChangeWatcher implements IZkChildListener {
    
    private ZookeeperNotify notify;
    
    public ZkNodeChangeWatcher(ZookeeperNotify notifyArgs) {
        this.notify = notifyArgs;
    }
    
    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
        List<ServiceProviderNode> onlineNodes = Lists.newArrayListWithCapacity(currentChilds.size());
        for (String nodeInString : currentChilds) {
            onlineNodes.add(new ServiceProviderNode(nodeInString));
        }
        
        notify.serviceNodes(notify.getServiceName(), onlineNodes);
    }
}
