package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum QuestionType {
    Wealth       ("财运", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"妻财"})),
    Studies      ("学业", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"父母"})),
    Career       ("事业", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"官鬼"})),
    Parent       ("父母", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"父母"})),
    Descendant   ("子女", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"子孙"})),
    interpersonal("人际", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"兄弟"})),
    Marriage     ("婚姻", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"配偶"})),
    Love         ("恋爱", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"配偶"})),
    Emotion      ("情感", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"配偶"})),
    Health       ("健康", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"世爻"})),
    Other        ("其他", hexagram -> QuestionType.findUseYao(hexagram, new String[] {"应爻"}));

    String name;
    Function<LiuYaoHexagram, List<HexagramYao>> findUseYao;

    public static List<HexagramYao> findUseYao(LiuYaoHexagram hexagram, String[] relations) {
        List<HexagramYao> useYaos = new ArrayList<>();
        for (String relation : relations) {
            for (HexagramYao yao : hexagram.yaos) {
                System.out.println(yao);
            }
        }
        return useYaos;
    }
}
