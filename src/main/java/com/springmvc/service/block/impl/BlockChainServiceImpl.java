package com.springmvc.service.block.impl;

import com.springmvc.entity.block.Block;
import com.springmvc.entity.block.BlockChain;
import com.springmvc.service.block.BlockChainService;

public class BlockChainServiceImpl implements BlockChainService {

    private BlockChain blockChain;

    public BlockChainServiceImpl(BlockChain blockChain){
        this.blockChain = blockChain;
    }

    /**
     * 追加块
     * @param block
     *
     * TODO 校验块是否是有效的
     */
    @Override
    public void appendBlock(Block block) {
        blockChain.getBlocks().add(block);
    }
}
