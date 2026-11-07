/**
 * Superclass abstract untuk semua hero di arena.
 * Syarat: 1 (state & behavior), 2 (immutability), 3 (enkapsulasi & overloading),
 * 7 (abstract class).
 */
public abstract class Hero {

    // SYARAT 2: konstanta global
    public static final int MAX_LEVEL = 10;

    // SYARAT 2: atribut instans kekal (hanya diisi di konstruktor, tanpa setter)
    private final String name;
    private final int maxHp;

    // SYARAT 1 & 3: state objek, semuanya private
    private int hp;
    private int mana;
    private int attackPower;
    private int level;

    // SYARAT 3: konstruktor overload #1 (nilai default)
    public Hero(String name) {
        this(name, 100, 50, 10);
    }

    // SYARAT 3: konstruktor overload #2 (nilai lengkap)
    public Hero(String name, int hp, int mana, int attackPower) {
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.mana = mana;
        this.attackPower = attackPower;
        this.level = 1;
    }

    // SYARAT 1: method prosedur (void)
    public void takeDamage(int damage) {
        setHp(this.hp - damage);
        System.out.println("  > " + name + " menerima " + damage + " damage");
    }

    // SYARAT 1: method fungsi (return value)
    public boolean isAlive() {
        return hp > 0;
    }

    // SYARAT 1: method fungsi (return value)
    public int getHpPercent() {
        return hp * 100 / maxHp;
    }

    // Method biasa yang nanti di-override subclass (SYARAT 5 & 6)
    public void showStatus() {
        System.out.println("[" + name + "] HP: " + hp + "/" + maxHp
                + " | Mana: " + mana
                + " | ATK: " + attackPower
                + " | Lv: " + level);
    }

    // SYARAT 7: abstract method, wajib diimplementasikan subclass
    public abstract void useSkill(Hero target);

    // ===== Getter & Setter (name dan maxHp TIDAK punya setter karena final) =====
    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, maxHp));
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, mana);
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = Math.max(0, attackPower);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(1, Math.min(level, MAX_LEVEL));
    }
}