package com.spotify.oauth2.exception;


public final class FrameworkException extends RuntimeException {

    public FrameworkException(String messg) {
        super(messg);
        printStackTrace();
    }

    public FrameworkException(String messg, Throwable e) {
        super(messg, e);
        printStackTrace();
    }

    public FrameworkException(Throwable e) {
        super(e);
        printStackTrace();
    }

    @Override
    public final void printStackTrace() {
        super.printStackTrace();
    }
}
