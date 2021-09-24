package com.omni.webapp.service;

import io.github.binaryfoo.DecodedData;
import io.github.binaryfoo.RootDecoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TLVDecoderImpl implements TLVDecoder{

    public void translateToReadableList(List<DecodedData> decoded, List<List<String>> newList) {
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
        List<String> arr1 = new ArrayList<>();
        for (DecodedData element : decoded) {
            if (element.getRawData().length() > 0) {
                //System.out.print("[");
                //System.out.print(element.getRawData());
                arr1.add(element.getRawData());
                arr1.add(element.getDecodedData());
                //System.out.println("] ");
            }
            //System.out.println(element.getDecodedData() + "\n");
            translateToReadableList(element.getKids(), newList);
        }
        if (arr1.size() >= 1) {
            newList.add(arr1);
        }
    };

    @Override
    public String decodeTLVData() {
        List<DecodedData> decoded = new RootDecoder().decode("6F1A840E315041592E5359532E4444463031A5088801025F2D02656E", "emv", "constructed");
        List<List<String>> newList = new ArrayList<>();
        translateToReadableList(decoded, newList);
        Collections.reverse(newList);
        System.out.println(newList);
        return "hi";
    }
}
