package com.bidanet.bdcms;

import java.util.Random;

/**
 * Created by xuejike on 2017/2/27.
 */
public class VersionRep {
    private int count=0;
    private int maxCount=10;

    public VersionRep(Runnable runnable) {
        run(runnable);
    }

    private void run(Runnable runnable){
        try {
            runnable.run();
        }catch (Exception e){
            if (count<maxCount){
                count++;
                try {
                    Random random = new Random();
                    int s1 = random.nextInt(100)*count;
//                    int sl= Integer.parseInt(String.valueOf(Math.floor(Math.random()*100)))*count;
                    System.out.println("退避"+count+"-->"+s1);
                    Thread.sleep(s1);
                    run(runnable);
                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
                }
            }else{
                System.out.println("退避失败");
                throw new RuntimeException("--");
            }
        }
    }
}
