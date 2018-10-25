package com.springmvc.entity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class BlockOld {

    /**是这个块在整个链中的位置*/
    private int index;

    /**显而易见就是块生成时的时间戳*/
    private String timestamp;

    /**虚拟资产。我们要记录的数据*/
    private int vac;

    /**是这个块通过 SHA256 算法生成的散列值*/
    private String hash;

    /**指向前一个块的 SHA256 散列值*/
    private String prevHash;

    ArrayList<BlockOld> blockOldChain;
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getVac() {
        return vac;
    }

    public void setVac(int vac) {
        this.vac = vac;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public static String calculateHash(BlockOld blockOld) {

        String record = (blockOld.getIndex()) + blockOld.getTimestamp() + (blockOld.getVac()) + blockOld.getPrevHash();
        //return SHA256.crypt(record);
        return UUID.fromString(record).toString();

    }

    public static BlockOld generateBlock(BlockOld oldBlockOld, int vac) {

        BlockOld newBlockOld = new BlockOld();
        newBlockOld.setIndex(oldBlockOld.getIndex() + 1);
        newBlockOld.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        newBlockOld.setVac(vac);
        newBlockOld.setPrevHash(oldBlockOld.getHash());
        newBlockOld.setHash(calculateHash(newBlockOld));
        return newBlockOld;
    }

    public static boolean isBlockValid(BlockOld newBlockOld, BlockOld oldBlockOld) {

        if (oldBlockOld.getIndex() + 1 != newBlockOld.getIndex()) {
            return false;
        }

        if (!oldBlockOld.getHash().equals(newBlockOld.getPrevHash())) {
            return false;
        }

        if (!calculateHash(newBlockOld).equals(newBlockOld.getHash())) {
            return false;
        }
        return true;

    }

    public void replaceChain(ArrayList<BlockOld> newBlockOlds) {
        if (newBlockOlds.size() > blockOldChain.size()) {
            blockOldChain = newBlockOlds;
        }
    }
}
