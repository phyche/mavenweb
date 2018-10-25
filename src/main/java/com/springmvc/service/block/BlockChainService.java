package com.springmvc.service.block;

import com.springmvc.entity.block.Block;

/**
 * 区块链操作接口及实现类
 */
public interface BlockChainService {

    /**
     * 区块链增加区块
     * @param block
     */
    void appendBlock(Block block);
}
