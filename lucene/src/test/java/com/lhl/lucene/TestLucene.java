package com.lhl.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 测试lucene.
 * Created by lunhengle on 2017/1/20.
 */
public class TestLucene {
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(TestLucene.class);
    /**
     * 内存存储.
     */
    private static Directory directory = new RAMDirectory();

    /**
     * 测试日志.
     */
    @Test
    public void testLogger() {
        logger.info("打印日志！");
    }

    /**
     * 组织数据.
     */
    @Before
    public void setUp() {
        storeIndex();
    }

    /**
     * 测试lucene.
     */
    @Test
    public void testLucene() throws IOException {
        readIndex("hello");
    }

    /**
     * 读取索引.
     *
     * @param queryString 查询字符串
     */
    private void readIndex(final String queryString) {
        try {
            Term term = new Term("title", queryString);
            Query query = new TermQuery(term);
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
            searcher.search(query, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                int docId = hits[i].doc;
                Document doc = searcher.doc(docId);
                logger.info((i + 1) + "\t" + "\t" + doc.get("title") + doc.get("isbn"));
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * 存储索引.
     */
    private void storeIndex() {
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);
            addDoc(indexWriter, "hello", "99999");
            addDoc(indexWriter, "hello", "88888");
            addDoc(indexWriter, "Lucene in Action", "123456");
            addDoc(indexWriter, "Lucene for Dummies", "789000");
            addDoc(indexWriter, "The Art of Computer Science", "78xxxxx");
            indexWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 创建 索引文档.
     *
     * @param w     写
     * @param title title 字段
     * @param isbn  isbn 字段
     */
    private void addDoc(IndexWriter w, String title, String isbn) {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        try {
            w.addDocument(doc);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
