package com.springmvc.util;

import com.springmvc.entity.MerkleTrees;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {

        List<String> tempTxList = new ArrayList<String>();
        tempTxList.add("a");
        tempTxList.add("b");
        tempTxList.add("c");
        tempTxList.add("d");
        tempTxList.add("e");

        MerkleTrees merkleTrees = new MerkleTrees(tempTxList);
        merkleTrees.merkle_tree();
        System.out.println("root : " + merkleTrees.getRoot());

        /*ArrayList<BlockOld> blockChain = new ArrayList<>();

        BlockOld genesisBlock = new BlockOld();
        genesisBlock.setIndex(0);
        genesisBlock.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        genesisBlock.setVac(0);
        genesisBlock.setPrevHash("");
        genesisBlock.setHash(calculateHash(genesisBlock));
        blockChain.add(genesisBlock);


        port(5678); //默认端口是4567，你可以设置别的端口*/
        /*get("/", (q, a) ->{
            return JSON.toJSON(blockChain).toString();
        });*/

        /*post("/", (q, a) -> {

            String body = "a";

            Message m = JSONObject.parseObject(body, Message.class);

            if (m == null) {
                return "vac is NULL";
            }

            int vac = m.getVac();

            BlockOld lastBlock = blockChain.get(blockChain.size() - 1);

            BlockOld newBlock = generateBlock(lastBlock, vac);

            if (isBlockValid(newBlock, lastBlock)) {

                blockChain.add(newBlock);

                System.out.println(JSON.toJSON(blockChain));

            } else {

                return "HTTP 500: Invalid BlockOld Error";

            }

            return "success!";

        });*/
    }
}
