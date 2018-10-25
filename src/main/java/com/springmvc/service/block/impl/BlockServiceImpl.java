package com.springmvc.service.block.impl;

import com.springmvc.entity.block.Block;
import com.springmvc.entity.block.BlockChain;
import com.springmvc.service.block.BlockService;
import com.springmvc.util.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockServiceImpl implements BlockService {

    private BlockChain blockChain;
    private final int DIFFICULTY = 2;

    public BlockServiceImpl(BlockChain blockChain){
        this.blockChain = blockChain;
    }

    @Override
    public Block geneGenesisBlock() {
        Block block = new Block();
        block.setIndex(-1);
        block.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        block.setData("gene genesis block");
        block.setCurrentHash(geneBlockHash(block));
        block.setPreBlockHash("");
        return block;
    }

    @Override
    public Block geneNextBlock(String newData) {
        Block preBlock = blockChain.getBlocks().get(blockChain.getBlocks().size()-1);
        Block newBlock = new Block();
        newBlock.setIndex(preBlock.getIndex()+1);
        newBlock.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        newBlock.setData(newData);
        newBlock.setPreBlockHash(preBlock.getCurrentHash());
        newBlock.setCurrentHash(geneBlockHash(newBlock));
        return newBlock;
    }

    @Override
    public String geneBlockHash(Block block) {
        String target = new String(new char[DIFFICULTY]).replace('\0', '0');
        int i=0;
        boolean flag =true;
        String hash="";
        while(flag){
            try {
                hash = HashUtils.SHA256(block.getIndex() + block.getTimestamp() + block.getData() + block.getPreBlockHash() + i);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (!hash.substring( 0, DIFFICULTY).equals(target)){
                i++;
                //System.out.println(hash);
            }else{
                flag =false;
            }
        }
        return hash;

    }
}
