/**
 * Hero penyihir. Skill kuat bergantung pada mana.
 * SYARAT 10: implements dua interface sekaligus (Healable, Levelable).
 */
public class Mage extends Hero implements Healable, Levelable {

    private static final int SKILL_COST = 40;
    private static final int HEAL_COST = 20;
    private static final int MANA_REGEN = 20;

    // Overload #1: default stat Mage
    public Mage(String name) {
        this(name, 80, 120, 15);
    }

    // Overload #2: stat custom. SYARAT 5: super(...)
    public Mage(String name, int hp, int mana, int attackPower) {
        super(name, hp, mana, attackPower);
    }

    // SYARAT 6 & 8: override abstract method + if/else yang membaca & mengubah mana
    @Override
    public void useSkill(Hero target) {
        if (getMana() >= SKILL_COST) {
            // Cabang mana cukup: serangan sihir kuat, mana berkurang
            System.out.println(getName() + " merapal Fireball ke " + target.getName());
            target.takeDamage(getAttackPower() * 3);
            setMana(getMana() - SKILL_COST);
        } else {
            // Cabang mana kurang: serangan tongkat biasa, mana pulih sedikit
            System.out.println(getName() + " kehabisan mana, memukul dengan tongkat");
            target.takeDamage(getAttackPower());
            setMana(getMana() + MANA_REGEN);
        }
    }

    // SYARAT 5 & 6
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("   Class: Mage | Biaya skill: " + SKILL_COST + " mana");
    }

    @Override
    public void heal(int amount) {
        if (getMana() >= HEAL_COST) {
            int before = getHp();
            setHp(getHp() + amount);
            setMana(getMana() - HEAL_COST);
            System.out.println(getName() + " memulihkan HP: " + before + " -> " + getHp()
                    + " (mana -" + HEAL_COST + ")");
        } else {
            System.out.println(getName() + " tidak punya cukup mana untuk heal");
        }
    }

    @Override
    public void levelUp() {
        if (getLevel() < MAX_LEVEL) {
            setLevel(getLevel() + 1);
            setAttackPower(getAttackPower() + 5);
            setMana(getMana() + 10);
            System.out.println(getName() + " naik ke level " + getLevel() + " (ATK +5, Mana +10)");
        } else {
            System.out.println(getName() + " sudah mencapai level maksimum (" + MAX_LEVEL + ")");
        }
    }
}