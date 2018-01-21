package kr.co.diordna.speedmemo.model;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by ryans on 2018-01-21.
 */

public class Memo implements Serializable{

    private int index;
    private String content;
    private DateTime updateAt;
    private DateTime createAt;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        long time = Long.parseLong(updateAt);
        this.updateAt = new DateTime().withMillis(time);
    }

    public DateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        long time = Long.parseLong(createAt);
        this.createAt = new DateTime().withMillis(time);
    }
}
