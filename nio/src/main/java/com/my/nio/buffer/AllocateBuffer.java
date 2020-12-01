package com.my.nio.buffer;

import com.sun.management.OperatingSystemMXBean;
import sun.nio.ch.DirectBuffer;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;

/**
 * 类说明：Buffer的分配 -Xmx100M -Xms100M
 */
public class AllocateBuffer {

    public static void main(String[] args) {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

        System.out.println("----------Test allocate--------");
        //分配之前的空闲物理内存大小
        System.out.println("before allocate:"
                + operatingSystemMXBean.getFreePhysicalMemorySize());

        /*堆上分配*/
        ByteBuffer byteBuffer = ByteBuffer.allocate(200000);
        System.out.println("buffer = " + byteBuffer);
        System.out.println("after allocate:"
                + operatingSystemMXBean.getFreePhysicalMemorySize());

        /* 这部分用的直接内存*/
        ByteBuffer byteBufferDirect = ByteBuffer.allocateDirect(200000);
        System.out.println("directBuffer = " + byteBufferDirect);
        System.out.println("after direct allocate:"
                + operatingSystemMXBean.getFreePhysicalMemorySize());

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        byteBuffer = ByteBuffer.wrap(bytes);
        System.out.println(byteBuffer);

        byteBuffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(byteBuffer);
    }
}
