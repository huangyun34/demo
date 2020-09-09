package com.my.admin.leetcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MultipleThread {
    static class R implements Runnable {

        CyclicBarrier cyclicBarrier;
        int i;

        public R() {
        }

        public R(int i, CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            this.i = i;
        }

        @Override
        public void run() {
            try {
//                Thread.sleep(i * 100);
                i = i + 1;
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class R1 implements Callable<List<String>> {

        CyclicBarrier cyclicBarrier;
        int x;

        public R1() {
        }

        public R1(int x, CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            this.x = x;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> l = new ArrayList<>();
            switch (x) {
                case 1:
                    Thread.sleep(1000);
                    l.add("1111");
                    break;
                case 2:
                    Thread.sleep(2000);
                    l.add("2222");
                    break;
                case 3:
                    Thread.sleep(3000);
                    l.add("333");
                    break;
                case 4:
                    Thread.sleep(4000);
                    l.add("4444");
                    break;
                case 5:
                    Thread.sleep(5000);
                    l.add("5555");
                    break;
                case 6:
                    Thread.sleep(6000);
                    l.add("6666");
                    break;
                default:
                    break;
            }
            cyclicBarrier.await();
            return l;
        }
    }

    static class R2 implements Callable<List<String>> {

        int x;

        public R2() {
        }

        public R2(int x) {
            this.x = x;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> l = new ArrayList<>();
            switch (x) {
                case 1:
                    Thread.sleep(1000);
                    l.add("1111");
                    break;
                case 2:
                    Thread.sleep(2000);
                    l.add("2222");
                    break;
                case 3:
                    Thread.sleep(3000);
                    l.add("333");
                    break;
                case 4:
                    Thread.sleep(4000);
                    l.add("4444");
                    break;
                case 5:
                    Thread.sleep(5000);
                    l.add("5555");
                    break;
                case 6:
                    Thread.sleep(6000);
                    l.add("6666");
                    break;
                default:
                    break;
            }
            return l;
        }
    }

    private static void a(Integer threadNum) throws BrokenBarrierException, InterruptedException, ExecutionException {
        Date now1 = new Date();
        List<String> l = new ArrayList<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum + 1);
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        List<Future<List<String>>> list = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            Future<List<String>> d = executorService.submit(new R1(i, cyclicBarrier));
            list.add(d);
//                executorService.execute(new R(x, cyclicBarrier));
        }
        System.out.println(cyclicBarrier.await());
        for (Future<List<String>> f : list) {
            l.addAll(f.get());
        }
        Date now2 = new Date();
        System.out.println("a:" + (now2.getTime() - now1.getTime()));

        executorService.shutdown();
        //            System.out.println("结束");
//            j++;
//        }
//        List<ChannelArticleDTO> articleDTOS = new ArrayList<>();
//        Collections.sort(articleDTOS, (o1, o2) -> {
//            if (o1.getSortNumber() > o2.getSortNumber()) {
//                return -1;
//            } else {
//                return 1;
//            }
//        });
    }

    private static void b(Integer threadNum) throws BrokenBarrierException, InterruptedException, ExecutionException {
        Date now1 = new Date();
        List<String> l = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            switch (i) {
                case 1:
                    Thread.sleep(1000);
                    l.add("1111");
                    break;
                case 2:
                    Thread.sleep(2000);
                    l.add("2222");
                    break;
                case 3:
                    Thread.sleep(3000);
                    l.add("333");
                    break;
                case 4:
                    Thread.sleep(4000);
                    l.add("4444");
                    break;
                case 5:
                    Thread.sleep(5000);
                    l.add("5555");
                    break;
                case 6:
                    Thread.sleep(6000);
                    l.add("6666");
                    break;
                default:
                    break;
            }
        }

        Date now2 = new Date();
        System.out.println("b:" + (now2.getTime() - now1.getTime()));

        //            System.out.println("结束");
//            j++;
//        }
//        List<ChannelArticleDTO> articleDTOS = new ArrayList<>();
//        Collections.sort(articleDTOS, (o1, o2) -> {
//            if (o1.getSortNumber() > o2.getSortNumber()) {
//                return -1;
//            } else {
//                return 1;
//            }
//        });
    }

    private static void c(Integer threadNum) throws BrokenBarrierException, InterruptedException, ExecutionException {
        Date now1 = new Date();
        List<String> l = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        List<R2> list = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            list.add(new R2(i));
        }
        List<Future<List<String>>> ds = executorService.invokeAll(list);
        for (Future<List<String>> f : ds) {
            l.addAll(f.get());
        }
        Date now2 = new Date();
        System.out.println("c:" + (now2.getTime() - now1.getTime()));

        executorService.shutdown();
        //            System.out.println("结束");
//            j++;
//        }
//        List<ChannelArticleDTO> articleDTOS = new ArrayList<>();
//        Collections.sort(articleDTOS, (o1, o2) -> {
//            if (o1.getSortNumber() > o2.getSortNumber()) {
//                return -1;
//            } else {
//                return 1;
//            }
//        });
    }
}
