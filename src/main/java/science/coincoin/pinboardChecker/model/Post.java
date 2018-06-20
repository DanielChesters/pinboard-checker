package science.coincoin.pinboardChecker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Daniel Chesters (on 20/06/2018).
 */
public class Post {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("extended")
    @Expose
    private String extended;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("meta")
    @Expose
    private String meta;
    @SerializedName("shared")
    @Expose
    private String shared;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("toread")
    @Expose
    private String toread;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtended() {
        return extended;
    }

    public void setExtended(String extended) {
        this.extended = extended;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToread() {
        return toread;
    }

    public void setToread(String toread) {
        this.toread = toread;
    }
}
