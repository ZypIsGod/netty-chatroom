package com.zyp.test01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Date:2026/1/27
 * @Author：zyp
 * @Description:
 */
public class ByteBufTest {

    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print(buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print(buffer);
        buffer.writeInt(12);
        print(buffer);
        buffer.writeBytes(new byte[]{5});
        print(buffer);
        buffer.writeBytes(new byte[]{6});
        print(buffer);


    }

    private static void print(ByteBuf x) {
        System.out.println(x);
    }
}
