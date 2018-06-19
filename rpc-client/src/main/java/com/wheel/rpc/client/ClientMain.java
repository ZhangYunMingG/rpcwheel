package com.wheel.rpc.client;

import com.wheel.rpc.client.proxy.ProxyFactory;
import com.wheel.rpc.communication.channel.IRpcWriteChannel;
import com.wheel.rpc.communication.channel.impl.NettyRpcWriteChannel;
import com.wheel.rpc.core.common.CommonUtils;
import com.wheel.rpc.core.test.IHello;

import io.netty.channel.Channel;

/**
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月14日 上午11:02:57
 */
public class ClientMain {

    public static void main(String[] args) {
        
        String ip = CommonUtils.getLocalAddressIp();
        int port = 8889;
        int ioThreadCnt = 20;
        
        RpcClient rpcClient = new RpcClient(ip, port, ioThreadCnt);
        rpcClient.open();
        
        Channel channel = rpcClient.getChannel();
        IRpcWriteChannel rpcWriteChannel = new NettyRpcWriteChannel(channel);
        IHello proxyRef = ProxyFactory.createProxy(IHello.class, rpcWriteChannel);
        String resp = proxyRef.sayHello("wanghaisheng");
        System.out.println("resp : " + resp);
    }
}
