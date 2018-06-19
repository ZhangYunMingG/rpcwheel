package com.wheel.rpc.communication.channel;


/**
 * 
 * 通道的抽样
 * 
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2018年6月19日 上午11:49:47
 */
public interface IRpcChannel {
    
    public void write(Object msg);
    
    public void writeAndFlush(Object msg);
}
