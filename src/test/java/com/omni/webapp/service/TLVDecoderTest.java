package com.omni.webapp.service;

import io.github.binaryfoo.DecodedData;
import io.github.binaryfoo.RootDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Component
class TLVDecoderTest {
    TLVDecoderImpl tlvDecoder = new TLVDecoderImpl();
    List<DecodedData> decoded = new RootDecoder().decode("6F1A840E315041592E5359532E4444463031A5088801025F2D02656E", "emv", "constructed");
    List<List<String>> newList = new ArrayList<>();

    @Test
    void decodeTLVDataWithValidEMV() {
        String data = "6F1A840E315041592E5359532E4444463031A5088801025F2D02656E";
        List<List<String>> list1 = tlvDecoder.decodeTLVData(data);
        ArrayList<String> a1 = new ArrayList<>(Arrays.asList("88 (SFI - Short file identifier)", "02", "5F2D (language preference)", "en"));
        ArrayList<String> a2 = new ArrayList<>(Arrays.asList("84 (dedicated file name)", "1PAY.SYS.DDF01", "A5 (FCI proprietary template)", "8801025F2D02656E"));
        ArrayList<String> a3 = new ArrayList<>(Arrays.asList("6F (FCI template)", "840E315041592E5359532E4444463031A5088801025F2D02656E"));
        List<List<String>> list2 = new ArrayList<>(Arrays.asList(a3, a2, a1));
        tlvDecoder.translateToReadableList(decoded, newList);
        assertEquals(list2,list1);
    }

    @Test
    void decodeTLVDataUnevenNumberHexDigits() {
        String data = "123456789";
        Throwable exception = assertThrows(RuntimeException.class,
                ()->{tlvDecoder.decodeTLVData(data);} );
        assertEquals("Uneven number(9) of hex digits passed to hex2byte.", exception.getMessage());
    }

    @Test
    void decodeTLVDataUneven() {
        String data = "123456789000000000000000000000000000000000000000000000000000000000000000000000000000000";
        Throwable exception = assertThrows(RuntimeException.class,
                ()->{tlvDecoder.decodeTLVData(data);} );
        assertEquals("Uneven number(87) of hex digits passed to hex2byte.", exception.getMessage());
    }
}