package com.springmvc.util;

import com.google.gson.Gson;
import com.springmvc.entity.block.Block;
import com.springmvc.entity.block.BlockChain;
import com.springmvc.service.block.impl.BlockChainServiceImpl;
import com.springmvc.service.block.impl.BlockServiceImpl;

public class BlockTest {

    public static void main(String[] args){

        Gson gson = new Gson();

        //初始区块链
        BlockChain blockChain =new BlockChain();

        BlockChainServiceImpl blockChainServiceImpl=new BlockChainServiceImpl(blockChain);
        BlockServiceImpl blockService = new BlockServiceImpl(blockChain);

        //初始化创世快
        Block genesisBlock = blockService.geneGenesisBlock();

        blockChainServiceImpl.appendBlock(genesisBlock);
        blockChainServiceImpl.appendBlock(blockService.geneNextBlock("test"));
        blockChainServiceImpl.appendBlock(blockService.geneNextBlock("test1"));

        System.out.println(gson.toJson(blockChain));

        for (Block block : blockChain.getBlocks()){
            System.out.println("index: "+block.getIndex());
            System.out.println("timestamp: "+block.getTimestamp());
            System.out.println("preBlockHash: "+block.getPreBlockHash());
            System.out.println("currentHash: "+block.getCurrentHash());
            System.out.println("data: "+block.getData());
            System.out.println();
        }

    }
}
