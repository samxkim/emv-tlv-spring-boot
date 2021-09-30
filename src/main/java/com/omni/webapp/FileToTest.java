package com.omni.webapp;

import java.util.*;

import com.omni.webapp.service.TLVDecoderImpl;
import com.payneteasy.tlv.*;
import io.github.binaryfoo.DecodedData;
import io.github.binaryfoo.RootDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileToTest {
    private List<DecodedData> decoded;
    private String s;

    public void translateToList(List<DecodedData> decoded) {
/*      [6F (FCI template)]
        840E315041592E5359532E4444463031A5088801025F2D02656E
        [84 (dedicated file name)]
        1PAY.SYS.DDF01
        [A5 (FCI proprietary template)]
        8801025F2D02656E
        [88 (SFI - Short file identifier)]
        02
        [5F2D (language preference)]
        en*/
        this.decoded = decoded;
        for (DecodedData element : decoded) {
            if (element.getRawData().length() > 0) {
                System.out.print("[");
                System.out.print(element.getRawData());
                System.out.println("] ");
            }
            System.out.println(element.getDecodedData() + "\n");
            translateToList(element.getKids());
        }
    };
    // Change out of main once debug is complete
    public static void main(String[] args) {
//        List<DecodedData> decoded = new RootDecoder().decode("6F1A840E315041592E5359532E4444463031A5088801025F2D02656E", "emv", "constructed");
//        new EVTTags().translateToList(decoded);
        System.out.println(new TLVDecoderImpl().decodeTLVData("6F1A840E315041592E5359532E4444463031A5088801025F2D02656E"));

    }
    }
