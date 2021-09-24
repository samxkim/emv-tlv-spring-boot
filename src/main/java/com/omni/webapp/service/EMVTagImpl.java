package com.omni.webapp.service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import com.omni.webapp.EVTTags;
import com.payneteasy.tlv.*;
import javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EMVTagImpl implements EMVTag<String>{

    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);

    @Override
    public String getEMVTag(String value) {
        final Logger LOG = LoggerFactory.getLogger(EVTTags.class);
        String inputString = "9F01";
        byte[] byteArray = inputString.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(byteArray));
        byte[] Test1 = new byte[]{(byte) 0x5f, (byte) 0x20};
        byte[] bytes = HexUtil.parseHex("50045649534157131000023100000033D44122011003400000481F");

        BerTlvParser parser = new BerTlvParser((BerTagFactory) LOG);
        BerTlvs tlvs = parser.parse(bytes, 0, bytes.length);

        BerTlvLogger.log("    ", tlvs, (IBerTlvLogger) LOG);
        return null;
    }

    @Override
    public String getKeyword(String value) {
        return null;
    }
}
