package com.my.admin;

import com.my.admin.model.User;
import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import io.swagger.util.Json;
import net.minidev.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.staticdocs.SwaggerResultHandler;
import springfox.documentation.swagger2.web.Swagger2Controller;

import java.util.Collections;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@AutoConfigureMockMvc
@EnableWebMvc
public class Swagger2Test {

    private String snippetDir = "target/generated-snippets";
    private String outputDir = "target/asciidoc";

    @Autowired
    private MockMvc mockMvc;

    @After
    public void Test() throws Exception{
        // 得到swagger.json,写入outputDir目录中
        mockMvc.perform(get(Swagger2Controller.DEFAULT_URL).accept(MediaType.APPLICATION_JSON))
                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
                .andExpect(status().isOk())
                .andReturn();

        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
                .withExamples(snippetDir)
                .build()
                .intoFolder(outputDir);// 输出
    }
    @Test
    public void contextLoads() throws Exception {
        mockMvc.perform(get("/test/connect")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("checkConnect", preprocessResponse(prettyPrint())));
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//        params.add("name", "ssdn");
//        mockMvc.perform(get("/connect/param")
//                .param("name", "chifan")
//                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
//                .andDo(MockMvcRestDocumentation.document("checkParamConnect", preprocessResponse(prettyPrint())));

    }

    @Test
    public void contextLoads1() throws Exception {
        mockMvc.perform(post("/test/connect/address")
                .content(Json.pretty(new User("22", "33")))
//                .params(params)
//                .param()
                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("checkAddress", preprocessResponse(prettyPrint())));

    }
}
