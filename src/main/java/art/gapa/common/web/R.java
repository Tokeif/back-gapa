package art.gapa.common.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author JoverZhang
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class R<T> {

    private final Integer code;

    private final T data;

    private final String message;

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return ok(data, "ok");
    }

    public static <T> R<T> ok(T data, String message) {
        return create(0, data, message);
    }

    public static <T> R<T> error(String message) {
        return error(null, message);
    }

    public static <T> R<T> error(T data, String message) {
        return create(-1, data, message);
    }

    public static <T> R<T> create(int code, T data, String message) {
        return new R<>(code, data, message);
    }

}
