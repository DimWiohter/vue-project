package com.coffeewx.utils;

/**
 * @Description:IdGeneratorTest
 * @Author:Kevin
 * @Date:2018-11-01 20:47
 */
public class IdGeneratorTest {

    public static void main(String[] args) {
        test();
        //test2();
    }

    public static void test(){
        IdGenerator idGenerator = new DefaultIdGenerator();
        System.out.println("--------简单测试------------------");
        for (int i=0; i<10; i++){
            System.out.println(idGenerator.next());
            try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test2(){
        System.out.println("--------ID生成器的特殊设置相关----------");
        IdGeneratorConfig config = new DefaultIdGeneratorConfig() {
            @Override
            public String getSplitString() {
                return "-";
            }
            @Override
            public int getInitial() {
                return 1000000;
            }
            @Override
            public String getPrefix() {
                return "NODE01";
            }
        };
        IdGenerator idGenerator = new DefaultIdGenerator(config);
        for (int i=0; i<20; i++){
            String id = idGenerator.next();
            System.out.println(id);
            try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
