package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Relation {
    Parent    ("父母", Effect.normalEffects),
    Brother   ("兄弟", Effect.normalEffects),
    Descendant("子孙", Effect.normalEffects),
    Wife      ("妻财", Effect.normalEffects),
    Officer   ("官鬼", Effect.normalEffects),
    Month     ("月建", new Effect[] {Effect.Combine, Effect.Conflict,
                                           Effect.Same, Effect.Support, Effect.Give, Effect.Leak, Effect.Cost, Effect.Curb}),
    Day       ("日建", new Effect[] {Effect.Same, Effect.Support, Effect.Give, Effect.Leak, Effect.Cost, Effect.Curb}),
    Empty     ("旬空", new Effect[] {Effect.Same});

    final String name;
    final Effect[] effects;
    public int getValue() {
        return ordinal();
    }
    public static Relation getByValue(int value) {
        return values()[value];
    }
}
