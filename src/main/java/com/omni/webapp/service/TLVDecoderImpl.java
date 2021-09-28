package com.omni.webapp.service;

import io.github.binaryfoo.DecodedData;
import io.github.binaryfoo.RootDecoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TLVDecoderImpl implements TLVDecoder{

    public void translateToReadableList(List<DecodedData> decoded, List<List<String>> newList) {
        List<String> arr1 = new ArrayList<>();
        for (DecodedData element : decoded) {
            if (element.getRawData().length() > 0) {
                arr1.add(element.getRawData());
                arr1.add(element.getDecodedData());
            }
            translateToReadableList(element.getKids(), newList);
        }
        if (arr1.size() >= 1) {
            newList.add(arr1);
        }
    };

    @Override
    public List<List<String>> decodeTLVData(String data) {
        List<DecodedData> decoded = new RootDecoder().decode(data, "emv", "constructed");
        List<List<String>> newList = new ArrayList<>();
        translateToReadableList(decoded, newList);
        Collections.reverse(newList);
        return newList;
    }
}
