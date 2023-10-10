package com.xinshijie.common.exception;

import java.io.Serial;

/**
 * 演示模式异常
 *
 * @author xinshijie
 */
public class DemoModeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DemoModeException() {
    }
}
