package run.zhinan.zhouyi.classic.divine.liuyao;

import lombok.Getter;


public class EffectStatus {
    HexagramYao target;
    HexagramYao actor;
    Effect      effect;
    int         power;
    @Getter
    String      description;

    public EffectStatus(HexagramYao target, HexagramYao actor, Effect effect, int power) {
        this.actor  = actor;
        this.target = target;
        this.effect = effect;
        this.power  = power;
        this.description = actor.position.getName() + actor.relation.getName() + (beBackGenerated() || beBackControlled() ? "回头" : "") + effect.getName() + "(" + (power > 0 ? "+" : "") + power + ")";
    }

    public boolean beConflicted() {
        return Effect.Conflict.equals(effect) || Effect.Reverse.equals(effect);
    }

    public boolean beControlled() {
        return Effect.Curb.equals(effect);
    }

    public boolean beBackEffected() {
        return target.isChange() && !actor.position.equals(Position.ENVIRONMENT);
    }

    public boolean beBackGenerated() {
        return beBackEffected() && Effect.Give.equals(effect);
    }

    public boolean beBackControlled() {
        return beBackEffected() && beControlled();
    }
}
