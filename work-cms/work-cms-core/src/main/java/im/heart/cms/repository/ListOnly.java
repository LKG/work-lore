package im.heart.cms.repository;

import java.math.BigInteger;

public class ListOnly {
    private final BigInteger id;

    private final String title;

    public ListOnly(BigInteger id, String title) {
        this.id = id;
        this.title = title;
    }
    public BigInteger getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
