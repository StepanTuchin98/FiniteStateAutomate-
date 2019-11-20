package com.automate.model;

import java.util.Objects;

public class PairTokenClassToken {
    private String tokenClass;
    private String token;

    public PairTokenClassToken(String tokenClass, String token) {
        this.tokenClass = tokenClass;
        this.token = token;
    }

    public String getTokenClass() {
        return tokenClass;
    }

    public void setTokenClass(String tokenClass) {
        this.tokenClass = tokenClass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairTokenClassToken that = (PairTokenClassToken) o;
        return Objects.equals(tokenClass, that.tokenClass) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenClass, token);
    }
}
