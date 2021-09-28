package com.omni.webapp.service;

import io.github.binaryfoo.DecodedData;
import io.github.binaryfoo.RootDecoder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TLVDecoderImplTest {
    TLVDecoderImpl tlvDecoder = new TLVDecoderImpl();
    List<DecodedData> decoded = new RootDecoder().decode("6F1A840E315041592E5359532E4444463031A5088801025F2D02656E", "emv", "constructed");
    List<List<String>> newList = new ArrayList<>();

    @Test
    void translateToReadableList() {
        ArrayList<String> a1 = new ArrayList<>(Arrays.asList("88 (SFI - Short file identifier)", "02", "5F2D (language preference)", "en"));
        ArrayList<String> a2 = new ArrayList<>(Arrays.asList("84 (dedicated file name)", "1PAY.SYS.DDF01", "A5 (FCI proprietary template)", "8801025F2D02656E"));
        ArrayList<String> a3 = new ArrayList<>(Arrays.asList("6F (FCI template)", "840E315041592E5359532E4444463031A5088801025F2D02656E"));
        List<List<String>> list1 = new ArrayList<>(Arrays.asList(a1, a2, a3));
        tlvDecoder.translateToReadableList(decoded, newList);
        assertEquals(list1, newList);
    }
}