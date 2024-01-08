package run.zhinan.zhouyi.classic.divine.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class BaseDivine {
    final String question;
    final LocalDateTime divineTime;
    Map<String, String> context = new HashMap<>();

    public void addContext(String key, String value) {
        context.put(key, value);
    }
}
