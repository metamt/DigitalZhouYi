package run.zhinan.zhouyi.classic.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PositionType {
    TIAN_GAN    (0, "天干"),
    Di_ZHI      (1, "地支"),
    HIDDEN_GAN_2(2, "第二藏干"),
    HIDDEN_GAN_3(3, "第三藏干");

    int value;
    String name;
}
