package com.base.givon.librarymaster.common.provider;

import com.github.pwittchen.prefser.library.Prefser;

import static com.base.givon.librarymaster.common.Constant.GUEST_TOKEN_KEY;

public class GuestTokenProvider implements TokenProvider {
    private Prefser prefser;

    public GuestTokenProvider(Prefser prefser) {
        this.prefser = prefser;
    }

    @Override
    public String getToken() {
        if (prefser != null) {
            return prefser.get(GUEST_TOKEN_KEY, String.class, "");
        }
        return null;
    }
}