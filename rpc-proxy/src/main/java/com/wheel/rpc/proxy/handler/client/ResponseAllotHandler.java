package com.wheel.rpc.proxy.handler.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wheel.rpc.core.model.RpcResponse;
import com.wheel.rpc.proxy.common.ProxyRequestCache;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 分配server的返回结果
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月11日 上午10:55:50
 */
public class ResponseAllotHandler extends ChannelInboundHandlerAdapter {
    
    public static final Logger LOG = LoggerFactory.getLogger(ResponseAllotHandler.class);
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcResponse response = (RpcResponse)msg;
        String requestId = response.getRequestId();
        boolean isSuccess = ProxyRequestCache.setResponse(requestId, response);
        if(!isSuccess) {
            LOG.error(String.format("Unknow requestId {}", requestId));
        }
    }
}
