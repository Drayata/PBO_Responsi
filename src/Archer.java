/**
 * Hero pemanah. Setiap tembakan menambah fokus;
 * setelah fokus cukup, tembakan berikutnya menjadi critical.
 * Implements 1 interface: Levelable.
 */
public class Archer extends Hero implements Levelable {

    private static final int FOCUS_NEEDED = 3;

    private int focus;

    // Overload #1: memakai konstruktor Hero(String) -> super(name)
    public Archer(String name) {
        super(name);
        this.focus = 0;
    }

    // Overload #2: stat custom
    public Archer(String name, int hp, int mana, int attackPower) {
        super(name, hp, mana, attackPower);
        this.focus = 0;
    }

    // SYARAT 6 & 8: override abstract method + if/else yang membaca & mengubah focus
    @Override
    public void useSkill(Hero target) {
        if (focus >= FOCUS_NEEDED) {
            // Cabang critical: fokus penuh, damage x3, fokus direset
            System.out.println(getName() + " membidik dan menembak CRITICAL ke " + target.getName());
            target.takeDamage(getAttackPower() * 3);
            focus = 0;
        } else {
            // Cabang biasa: tembakan normal, fokus bertambah
            System.out.println(getName() + " menembakkan panah ke " + target.getName());
            target.takeDamage(getAttackPower());
            focus++;
        }
    }

    // SYARAT 5 & 6
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("   Class: Archer | Fokus: " + focus + "/" + FOCUS_NEEDED);
    }

    @Override
    public void levelUp() {
        if (getLevel() < MAX_LEVEL) {
            setLevel(getLevel() + 1);
            setAttackPower(getAttackPower() + 3);
            System.out.println(getName() + " naik ke level " + getLevel() + " (ATK +3)");
        } else {
            System.out.println(getName() + " sudah mencapai level maksimum (" + MAX_LEVEL + ")");
        }
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = Math.max(0, focus);
    }
}