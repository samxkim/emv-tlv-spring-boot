package com.omni.webapp;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

// Change wildcard imports once dev is done
import sasc.emv.*;
import sasc.iso7816.*;
import sasc.util.ByteArrayWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

import static sasc.emv.EMVTags.*;

public class EVTTags {
    // Change out of main once debug is complete
    public static void main(String[] args) {
        String inputString = "9F01";
        String inputString1 = "Uniquely identifies";
        byte[] byteArray = inputString.getBytes(StandardCharsets.UTF_8);
        byte[] byteArray1 = inputString1.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(byteArray));
        System.out.println(Arrays.toString(byteArray1));
        System.out.println(find(byteArray1));
        byte[] Test1 = new byte[]{(byte) 0x5f, (byte) 0x20};
        System.out.println(Arrays.toString(Test1));
        System.out.println(find(Test1));
        // System.out.println(ACQUIRER_IDENTIFIER.getDescription().contains(inputString1));
        Iterator hello1 = iterator();
        while(hello1.hasNext()) {
            LinkedHashMap<ByteArrayWrapper, Tag> hello2 = hello1.next();
            if (hello1.next().toString().contains(inputString1)) {
                System.out.println(hello1);
            }
            System.out.println(hello1.next().toString().contains(inputString1));
        }
        System.out.println(hello1);
        System.out.print(Arrays.toString(inputString.getBytes()));
    }
}
