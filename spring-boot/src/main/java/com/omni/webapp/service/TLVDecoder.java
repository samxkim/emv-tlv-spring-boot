package com.omni.webapp.service;

import java.util.List;

public interface TLVDecoder {
    List<List<String>> decodeTLVData(String data);
}
