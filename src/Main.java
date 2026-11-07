public class Main {
    public static void main(String[] args) {

        // ===== SYARAT 3 & 4: konstruktor overload + inheritance =====
        section("SYARAT 3 & 4", "Overloaded constructor + Inheritance (3 subclass dari Hero)");
        Warrior warrior = new Warrior("Brutus");                 // konstruktor 1 argumen
        Mage mage = new Mage("Eldrin");                          // konstruktor 1 argumen
        Archer archer = new Archer("Lyra", 90, 60, 13);          // konstruktor 4 argumen
        Warrior golem = new Warrior("Golem", 500, 0, 5);         // konstruktor 4 argumen (target latihan)

        // ===== SYARAT 2: immutability =====
        section("SYARAT 2", "Immutability");
        System.out.println("Konstanta Hero.MAX_LEVEL (public static final) = " + Hero.MAX_LEVEL);
        System.out.println("Atribut name (private final) milik warrior = " + warrior.getName()
                + " -> tidak ada setName(), nilainya tak bisa diubah");

        // ===== SYARAT 1: state & behavior =====
        section("SYARAT 1", "State & behavior (void + return value)");
        warrior.takeDamage(10);                                  // method void
        System.out.println("isAlive() -> " + warrior.isAlive()); // method return boolean
        System.out.println("getHpPercent() -> " + warrior.getHpPercent() + "%"); // method return int
        warrior.setHp(warrior.getMaxHp());                       // pulihkan lewat setter

        // ===== SYARAT 5 & 6: super chaining + overriding =====
        section("SYARAT 5 & 6", "Super chaining (super.showStatus) + @Override showStatus");
        warrior.showStatus();
        mage.showStatus();
        archer.showStatus();

        // ===== SYARAT 7 & 4: abstract class + polimorfisme class =====
        section("SYARAT 7", "Abstract class: Hero tidak bisa di-new, useSkill() lewat variabel Hero");
        // Hero h = new Hero("x");  // ERROR kompilasi: Hero is abstract
        Hero[] heroes = {warrior, mage, archer};
        for (Hero h : heroes) {
            h.useSkill(golem);                                   // satu pemanggilan, perilaku berbeda
        }

        // ===== SYARAT 8: logika state (if/else) =====
        section("SYARAT 8a", "Mage: mana cukup -> Fireball, mana habis -> pukulan biasa + regen");
        for (int i = 1; i <= 4; i++) {
            System.out.println("-- Giliran ke-" + i + " | Mana sebelum: " + mage.getMana());
            mage.useSkill(golem);
        }
        System.out.println("Mana akhir: " + mage.getMana());

        section("SYARAT 8b", "Warrior: HP >= 30% -> tebasan (rage naik), HP < 30% -> BERSERK");
        warrior.useSkill(golem);
        warrior.useSkill(golem);
        System.out.println("Rage sekarang: " + warrior.getRage());
        warrior.takeDamage(90);                                  // HP turun ke 25%
        System.out.println("HP Brutus sekarang " + warrior.getHpPercent() + "%");
        warrior.useSkill(golem);
        System.out.println("Rage setelah berserk: " + warrior.getRage());

        section("SYARAT 8c", "Archer: fokus < 3 -> tembakan biasa, fokus >= 3 -> CRITICAL");
        // Archer sudah menembak 1x di bagian SYARAT 7 (fokus = 1)
        for (int i = 1; i <= 3; i++) {
            archer.useSkill(golem);
        }
        System.out.println("Fokus akhir: " + archer.getFocus());

        // ===== SYARAT 9: polimorfisme interface =====
        section("SYARAT 9", "Polimorfisme interface: Interface obj = new Subclass()");
        Healable healer = new Mage("Mira");                      // variabel bertipe interface
        Hero miraAsHero = (Hero) healer;                         // downcast hanya agar bisa diberi damage
        miraAsHero.takeDamage(50);
        healer.heal(30);                                         // hanya heal() yang terlihat lewat Healable
        // healer.useSkill(golem);  // ERROR kompilasi: Healable tidak punya useSkill()

        Levelable[] party = {warrior, mage, archer};
        System.out.println("-- Semua anggota party naik level lewat tipe Levelable --");
        for (Levelable l : party) {
            l.levelUp();
        }
        mage.setLevel(Hero.MAX_LEVEL);
        System.out.println("-- Mage dipaksa level maksimum, lalu levelUp() lagi --");
        mage.levelUp();

        // ===== SYARAT 10: multiple interface =====
        section("SYARAT 10", "Mage implements Healable, Levelable");
        System.out.println("mage instanceof Healable   -> " + (mage instanceof Healable));
        System.out.println("mage instanceof Levelable  -> " + (mage instanceof Levelable));
        System.out.println("warrior instanceof Healable -> " + (warrior instanceof Healable));

        // ===== Penutup: pertarungan singkat =====
        section("PENUTUP", "Duel Rex (Warrior) vs Nyx (Mage) lewat variabel Hero");
        Hero p1 = new Warrior("Rex");
        Hero p2 = new Mage("Nyx");
        int round = 1;
        while (p1.isAlive() && p2.isAlive() && round <= 20) {
            System.out.println("--- Ronde " + round + " ---");
            p1.useSkill(p2);
            if (p2.isAlive()) {
                p2.useSkill(p1);
            }
            round++;
        }
        System.out.println();
        p1.showStatus();
        p2.showStatus();
        Hero winner = p1.isAlive() ? p1 : p2;
        System.out.println("Pemenang: " + winner.getName());
    }

    private static void section(String syarat, String judul) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("[" + syarat + "] " + judul);
        System.out.println("==================================================");
    }
}