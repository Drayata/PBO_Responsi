/**
 * Hero petarung jarak dekat. Semakin lama bertarung, rage menumpuk.
 * Saat HP kritis, Warrior masuk mode berserk.
 * Implements 1 interface: Levelable.
 */
public class Warrior extends Hero implements Levelable {

    private static final int BERSERK_THRESHOLD = 30; // persen HP
    private static final int MAX_RAGE = 100;

    private int rage;

    // Overload #1: default stat Warrior
    public Warrior(String name) {
        this(name, 120, 30, 14);
    }

    // Overload #2: stat custom. SYARAT 5: super chaining lewat super(...)
    public Warrior(String name, int hp, int mana, int attackPower) {
        super(name, hp, mana, attackPower);
        this.rage = 0;
    }

    // SYARAT 6 & 8: override abstract method + logika if/else yang membaca & mengubah state
    @Override
    public void useSkill(Hero target) {
        if (getHpPercent() < BERSERK_THRESHOLD) {
            // Cabang berserk: baca HP, pakai rage, lalu reset rage
            int damage = getAttackPower() * 2 + getRage();
            System.out.println(getName() + " BERSERK! Melepaskan seluruh rage (" + getRage() + ")");
            target.takeDamage(damage);
            setRage(0);
        } else {
            // Cabang normal: serang biasa, rage bertambah
            System.out.println(getName() + " menebas " + target.getName());
            target.takeDamage(getAttackPower());
            setRage(getRage() + 20);
        }
    }

    // SYARAT 5 & 6: override + pemanggilan super.showStatus()
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("   Class: Warrior | Rage: " + rage + "/" + MAX_RAGE);
    }

    @Override
    public void levelUp() {
        if (getLevel() < MAX_LEVEL) {
            setLevel(getLevel() + 1);
            setAttackPower(getAttackPower() + 4);
            System.out.println(getName() + " naik ke level " + getLevel() + " (ATK +4)");
        } else {
            System.out.println(getName() + " sudah mencapai level maksimum (" + MAX_LEVEL + ")");
        }
    }

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = Math.max(0, Math.min(rage, MAX_RAGE));
    }
}