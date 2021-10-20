package com.omni.webapp.service;

import com.omni.webapp.controller.InvalidTLVException;
import com.omni.webapp.utils.TagTLVUtils;
import io.github.binaryfoo.DecodedData;
import io.github.binaryfoo.RootDecoder;
import javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TLVDecoderImpl implements TLVDecoder{

    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);

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
    }

    @Override
    public List<List<String>> decodeTLVData(String data) throws InvalidTLVException {
        if (!TagTLVUtils.enteredInputValid(data)) {
            throw new InvalidTLVException("Invalid character detected. Please enter data correctly.");
        }
        List<DecodedData> decoded = new RootDecoder().decode(data, "emv", "constructed");
        List<List<String>> newList = new ArrayList<>();
        translateToReadableList(decoded, newList);
        Collections.reverse(newList);
        return newList;
    }
}
