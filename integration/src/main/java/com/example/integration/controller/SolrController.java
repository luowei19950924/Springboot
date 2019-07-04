package com.example.integration.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luowei
 * @date 2019/5/14 18:59
 */
@Controller
public class SolrController {

    @Autowired
    private SolrClient solrClient;

    @RequestMapping("solr")
    @ResponseBody
    public String test() throws Exception{
        SolrDocument document = solrClient.getById("1");
        return document.toString();
    }
}
