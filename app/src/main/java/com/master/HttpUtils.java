package com.master;

import java.util.List;

/**
 * @param
 * @author Litao-pc on 2016/11/24.
 *         ~
 */

public class HttpUtils  {

    private String articletitle;
    private String articlephoto;
    private String articledesc;
    private String articlemodel;
    private String articlepower;
    private String draft;
    private List<ArticlebodyBean> articlebody;

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticlephoto() {
        return articlephoto;
    }

    public void setArticlephoto(String articlephoto) {
        this.articlephoto = articlephoto;
    }

    public String getArticledesc() {
        return articledesc;
    }

    public void setArticledesc(String articledesc) {
        this.articledesc = articledesc;
    }

    public String getArticlemodel() {
        return articlemodel;
    }

    public void setArticlemodel(String articlemodel) {
        this.articlemodel = articlemodel;
    }

    public String getArticlepower() {
        return articlepower;
    }

    public void setArticlepower(String articlepower) {
        this.articlepower = articlepower;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public List<ArticlebodyBean> getArticlebody() {
        return articlebody;
    }

    public void setArticlebody(List<ArticlebodyBean> articlebody) {
        this.articlebody = articlebody;
    }

    public static class ArticlebodyBean {
        private String bodytitle;
        private List<BodycontentBean> bodycontent;

        public String getBodytitle() {
            return bodytitle;
        }

        public void setBodytitle(String bodytitle) {
            this.bodytitle = bodytitle;
        }

        public List<BodycontentBean> getBodycontent() {
            return bodycontent;
        }

        public void setBodycontent(List<BodycontentBean> bodycontent) {
            this.bodycontent = bodycontent;
        }

        public static class BodycontentBean {
            private String index;
            private String type;
            private String content;

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
