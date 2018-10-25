package com.springmvc.entity.block;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义区块链（使用List数据结构）
 */
public class BlockChain {
    private List<Block> blocks;

    public BlockChain(){
        blocks = new ArrayList();
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

}
