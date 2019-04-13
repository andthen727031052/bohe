package com.hsl.bohe.webmagic;

import com.hsl.bohe.entity.Food;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class FoodProcess implements PageProcessor {

    private Site site = Site.me().setTimeOut(30000).setRetrySleepTime(3).setSleepTime(10);


    @Override
    public void process(Page page) {

        /*List<String> names = page.getHtml().xpath("ul[@class='row']/li/div[@class='text-box']/h3/a/text()").all();
        List<String> imgs = page.getHtml().xpath("ul[@class='row']/li/div[@class='img-box']/a/img/@src").all();


        for (int i=0;i< names.size();i++){

            String img = imgs.get(i);
            String name = names.get(i);
            System.err.println("食物分类--->"+name);
            System.err.println("图片地址--->"+img);
            FoodType foodType = new FoodType();
            foodType.setImgurl(img);
            foodType.setName(name);
           //foodTypeMapper.insert(foodType);
            new UtilDao().add(foodType);
        }*/


        List<String> names = page.getHtml().xpath("ul[@class='food-list']/li[@class='item clearfix']/div[@class='text-box pull-left']/h4/a/text()").all();
        List<String> heats = page.getHtml().xpath("ul[@class='food-list']/li[@class='item clearfix']/div[@class='text-box pull-left']/p/text()").all();
        List<String> imgs = page.getHtml().xpath("ul[@class='food-list']/li[@class='item clearfix']/div[@class='img-box pull-left']/a/img/@src").all();

        for (int i=0;i< names.size();i++){

            String name = names.get(i);
            String heat = heats.get(i);
            heat = heat.substring(3,heat.indexOf("大卡"));
           /* heat = heat.replace("热量：","");
            heat = heat.replace("大卡(每100*)","");*/
            String img = imgs.get(i);
            System.err.println("食物名称--->"+name);
            System.err.println("食物热量--->"+heat);
            System.err.println("食物图片--->"+img);
            Food food = new Food();
            food.setName(name);
            food.setHeat(Double.parseDouble(heat));
            food.setImgurl(img);
            food.setTid(10);
            new UtilDao().addFood(food);

        }

        if(page.getUrl().get().endsWith("page=6")){
//            List<String> pages=page.getHtml().xpath("div[@class='page-container']/div[@class='page-box']/a/text()").all();
//            System.err.println(pages);
            List<String> targets=new ArrayList<>();
            for(int i=7;i<=9;i++ ){
                String u=page.getUrl().get();
                targets.add(u.substring(0,u.length()-1)+i);
            }
            System.err.println(targets);
            page.addTargetRequests(targets);

        }


    }

    @Override
    public Site getSite() {
        return site;
    }



}
