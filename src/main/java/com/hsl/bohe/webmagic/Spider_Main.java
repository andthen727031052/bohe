package com.hsl.bohe.webmagic;


import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;


public class Spider_Main {

    public static void main(String[] args) {
        /*new Spider(new FoodProcess()).addUrl("https://www.xiami.com/billboard/332").thread(5).addPipeline(new ConsolePipeline()).run();*/
//        new Spider(new FoodProcess()).addUrl("https://y.qq.com/n/yqq/toplist/4.html").thread(5).addPipeline(new ConsolePipeline()).run();
       // new Spider(new FoodProcess()).addUrl("http://www.boohee.com/food/").thread(5).addPipeline(new ConsolePipeline()).run();
       // new Spider(new FoodProcess()).addUrl("http://www.boohee.com/food/group/1?page=1").thread(4).addPipeline(new ConsolePipeline()).run();
        new Spider(new FoodProcess()).addUrl("http://www.boohee.com/food/group/10?page=6").thread(4).addPipeline(new ConsolePipeline()).run();

    }
}
