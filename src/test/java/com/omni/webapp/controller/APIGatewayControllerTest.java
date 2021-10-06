package com.omni.webapp.controller;

import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import com.omni.webapp.service.EMVTag;
import com.omni.webapp.service.TLVDecoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@WebMvcTest(controllers = APIGatewayController.class)
class APIGatewayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EMVTag emvTag;

    @MockBean
    private TLVDecoder tlvDecoder;

    @MockBean
    private TagRepository tagRepository;

    @BeforeEach
    public void init() {
    }

    @AfterEach
    public void destroy() {
        tagRepository.deleteAll();
    }

    @Test
    void whenValidInputTagReturn302() throws Exception {
        String responseBody = "{\"name\":\"41\",\"description\":" +
                "\"Country Code: Country code (encoding specified in ISO 3166-1) and optional national data\"," +
                "\"update_date\":null}";
        given(emvTag.getEMVTag("41"))
                .willReturn(java.util.Optional.of(new Tag("41", "Country Code: Country code " +
                        "(encoding specified in ISO 3166-1) and optional national data")));

        this.mockMvc.perform(get("/emvtagsearch")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", "41"))
                .andExpect(status().isFound())
                .andExpect(content().string(responseBody));
    }

    @Test
    void whenInvalidInputTagReturn400() throws Exception {
        given(emvTag.getEMVTag("dsadsadasdsa")).willThrow(new TagNotFoundException());
        this.mockMvc.perform(get("/emvtagsearch")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", "dsadsadasdsa"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenValidInputKeywordReturn302() throws Exception {
        String responseBody = "[{\"name\":\"9f6e\",\"description\":\"Visa Low-Value Payment (VLP) Issuer Authorisation Code\",\"update_date\":null}]";
        Optional<List<Tag>> returnedTagList = Optional.of(List.of(new Tag("9f6e", "Visa Low-Value Payment (VLP) Issuer Authorisation Code")));
        given(emvTag.getEMVTagByKeyword("visa"))
        .willReturn(returnedTagList);

        this.mockMvc.perform(get("/emvtagsearchdescription")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("description", "visa"))
                .andExpect(status().isFound())
                .andExpect(content().string(responseBody));
    }

    @Test
    void whenInvalidInputKeywordReturn400() throws Exception {
        String responseBody = "[{\"name\":\"9f6e\",\"description\":\"Visa Low-Value Payment (VLP) Issuer Authorisation Code\",\"update_date\":null}]";
        Optional<List<Tag>> returnedTagList = Optional.of(List.of(new Tag("9f6e", "Visa Low-Value Payment (VLP) Issuer Authorisation Code")));
        given(emvTag.getEMVTagByKeyword("visa"))
                .willReturn(returnedTagList);

        this.mockMvc.perform(get("/emvtagsearchdescription")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("description", "visa"))
                .andExpect(status().isFound())
                .andExpect(content().string(responseBody));
    }

    @Test
    void whenValidInputTLVReturn302() throws Exception {
        String responseBody = "[[\"6F (FCI template)\",\"840E315041592E5359532E4444463031A5088801025F2D02656E\"],[\"84 (dedicated file name)\",\"1PAY.SYS.DDF01\",\"A5 (FCI proprietary template)\",\"8801025F2D02656E\"],[\"88 (SFI - Short file identifier)\",\"02\",\"5F2D (language preference)\",\"en\"]]";
        ArrayList<String> a1 = new ArrayList<>(Arrays.asList("88 (SFI - Short file identifier)", "02", "5F2D (language preference)", "en"));
        ArrayList<String> a2 = new ArrayList<>(Arrays.asList("84 (dedicated file name)", "1PAY.SYS.DDF01", "A5 (FCI proprietary template)", "8801025F2D02656E"));
        ArrayList<String> a3 = new ArrayList<>(Arrays.asList("6F (FCI template)", "840E315041592E5359532E4444463031A5088801025F2D02656E"));
        List<List<String>> list1 = new ArrayList<>(Arrays.asList(a3, a2, a1));
        given(tlvDecoder.decodeTLVData("6F1A840E315041592E5359532E4444463031A5088801025F2D02656E"))
                .willReturn(list1);

        this.mockMvc.perform(get("/tlvdecoder/6F1A840E315041592E5359532E4444463031A5088801025F2D02656E")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isFound())
                        .andExpect(content().string(responseBody));
    }

    @Test
    void whenInvalidInputTLVReturn400() throws Exception {
        String responseBody = "{\"status\":\"BAD_REQUEST\",\"errorCode\":\"400\",\"message\":\"Invalid request input\",\"detail\":\"Please enter valid input.\"}";
        given(tlvDecoder.decodeTLVData("dsadsadadsa")).willThrow(new TagNotFoundException());

        this.mockMvc.perform(get("/tlvdecoder/6F1A840E315041592E5359532E4444463031A5088801025F2D02656E")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(responseBody));
    }
}