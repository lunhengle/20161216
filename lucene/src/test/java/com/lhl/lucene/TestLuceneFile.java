package com.lhl.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.SpanOrQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.BytesRef;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * 测试文件存储lucene索引.
 * Created by lunhengle on 2017/1/22.
 */
public class TestLuceneFile {
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(TestLuceneFile.class);
    /**
     * 文件路径.
     */
    private static String filePath = "D:\\IdeaProject\\20161216\\lucene\\src\\main\\resources\\data\\index";

    /**
     * 测试存储lucene文件索引.
     */
    @Test
    public void testStoreIndex() throws IOException {
        this.storeIndex();
    }

    /**
     * 精准查询.
     *
     * @throws IOException
     */
    @Test
    public void testReadIndex() throws IOException {
        //精准查询
        Term term = new Term("id", "11");
        Query query = new TermQuery(term);
        this.readIndex(query);

    }

    /**
     * 前缀查询.
     * 即 从前往后每一个字每一个字匹配
     *
     * @throws IOException
     */
    @Test
    public void testPrefixQuery() throws IOException {
        //前缀查询
        Term term = new Term("title", "新闻");
        Query query = new PrefixQuery(term);
        this.readIndex(query);
    }

    /**
     * 多个查询条件查询.
     *
     * @throws IOException
     */
    @Test
    public void testBooleanQuery() throws IOException {
        //多值查询
        Term term = new Term("title", "新");
        Term term1 = new Term("title", "闻");
        BooleanClause booleanClause = new BooleanClause(new TermQuery(term), BooleanClause.Occur.SHOULD);
        BooleanClause booleanClause1 = new BooleanClause(new PrefixQuery(term1), BooleanClause.Occur.FILTER);
        Query query = new BooleanQuery.Builder().add(booleanClause).add(booleanClause1).build();
        this.readIndex(query);
    }

    /**
     * 区间查询.
     *
     * @throws IOException
     */
    @Test
    public void testRangeQuery() throws IOException {
        //区间查询
        BytesRef lowerTerm = new BytesRef("1");
        BytesRef upperTerm = new BytesRef("3");
        Query query = new TermRangeQuery("id", lowerTerm, upperTerm, false, true);
        this.readIndex(query);
    }

    /**
     * 短语查询.
     *
     * @throws IOException
     */
    @Test
    public void testPhraseQuery() throws IOException {
        Term term = new Term("content", "新");
        Term term1 = new Term("content", "闻");
        Query query = new PhraseQuery.Builder().add(term).add(term1).setSlop(5).build();
        this.readIndex(query);
    }

    /**
     * 多词组查询.
     * 可以根据组合声明不同的查询方式，可以实现前缀查询，后缀查询，混合查询。
     *
     * @throws IOException
     */
    @Test
    public void testMultiPhraseQuery() throws IOException {
        Term term = new Term("content", "新华");
        Term term1 = new Term("content", "新闻");
        Query query = new MultiPhraseQuery.Builder().add(term).add(term1).setSlop(5).build();
        this.readIndex(query);
    }

    /**
     * 正则查询.
     *
     * @throws IOException
     */
    @Test
    public void testRegexQuery() throws IOException {
        Term term = new Term("content", "你好*");
        Query query = new RegexpQuery(term);
        this.readIndex(query);
    }

    /**
     * 跨度查询.
     *
     * @throws IOException
     */
    @Test
    public void testSpanQuery() throws IOException {
        Term term = new Term("content", "新");
        SpanTermQuery spanTermQuery = new SpanTermQuery(term);
        Term term1 = new Term("content", "world");
        SpanTermQuery spanTermQuery1 = new SpanTermQuery(term1);
        Query query = new SpanOrQuery(spanTermQuery, spanTermQuery1);
        this.readIndex(query);
    }

    /**
     * 删除索引值.
     *
     * @throws IOException
     */
    @Test
    public void testDeleteIndex() throws IOException {
        this.deleteIndex("3");
    }

    /**
     * 存储索引.
     */
    private void storeIndex() throws IOException {
        /**
         * 定义标准的分词.
         */
        Analyzer analyzer = new CJKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        File file = new File(filePath);
        /**
         * 索引存储文件夹.
         */
        Directory directory = new SimpleFSDirectory(file.toPath());
        /**
         * 写入.
         */
        IndexWriter indexWriter = new IndexWriter(directory, config);
        /**
         * 定义 field.
         */
        this.storeDocument(indexWriter, "1", "abc", "hello");
        this.storeDocument(indexWriter, "2", "abc", "world");
        this.storeDocument(indexWriter, "3", "abc", "haha");
        this.storeDocument(indexWriter, "4", "abc", "lunyu");
        this.storeDocument(indexWriter, "5", "abc", "lunhengle");
        this.storeDocument(indexWriter, "6", "abc", "lunxinghe");
        this.storeDocument(indexWriter, "7", "test", "testContent");
        this.storeDocument(indexWriter, "8", "新华社日报", "新华社日报新闻");
        this.storeDocument(indexWriter, "9", "新闻", "新华社日报新闻");
        this.storeDocument(indexWriter, "10", "闻闻", "hello world");
        this.storeDocument(indexWriter, "11", "闻闻", "你好 世界");
        indexWriter.close();
    }

    /**
     * 存储成文档.
     *
     * @param indexWriter 写入
     * @param id          id
     * @param title       标题
     * @param content     内容
     * @throws IOException 异常信息
     */
    private void storeDocument(IndexWriter indexWriter, final String id, final String title, final String content) throws IOException {
        Term term = new Term("id", id);
        /**
         * 定义field.
         */
        Field field1 = new StringField("id", id, Field.Store.YES);
        Field field2 = new TextField("title", title, Field.Store.YES);
        Field field3 = new TextField("content", content, Field.Store.YES);
        /**
         * 定义document.
         */
        Document document = new Document();
        document.add(field1);
        document.add(field2);
        document.add(field3);
        indexWriter.updateDocument(term, document);
    }

    /**
     * 删除索引.
     *
     * @param id 索引id
     * @throws IOException
     */
    private void deleteIndex(final String id) throws IOException {
        File file = new File(filePath);
        Directory directory = new SimpleFSDirectory(file.toPath());
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
        Term term = new Term("id", id);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriter.deleteDocuments(term);
        indexWriter.close();
    }

    /**
     * 读取索引.
     *
     * @param query 查询类
     */
    private void readIndex(final Query query) throws IOException {
        /**
         * 索引存储文件夹.
         */
        File file = new File(filePath);
        Directory directory = FSDirectory.open(file.toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        TopScoreDocCollector topScoreDocCollector = TopScoreDocCollector.create(10);
        indexSearcher.search(query, topScoreDocCollector);
        ScoreDoc[] scoreDocs = topScoreDocCollector.topDocs().scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            int doc = scoreDocs[i].doc;
            Document document = indexSearcher.doc(doc);
            logger.info(">>>>>>>>>>>>>>>>>>index:" + (i + 1) + " title:" + document.get("title") + " content:" + document.get("content"));
        }
    }
}
