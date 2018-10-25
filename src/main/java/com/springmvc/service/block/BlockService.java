package com.springmvc.service.block;

import com.springmvc.entity.block.Block;

/**
 * 定义块操作接口及实现类
 */
public interface BlockService {

    /**
     * 生成创世块
     * @return
     */
    Block geneGenesisBlock();

    /**
     * 根据前一块生成后一块
     * @param newData 当前块内容
     * @return
     */
    Block geneNextBlock(String newData);

    /**
     * 工作量证明
     * @param block
     * @return
     */
    String geneBlockHash(Block block);
}
