# Responsi Akhir Praktikum PBO - Turn-Based Arena RPG

## Identitas

- Nama : Indra Surya Adinata
- NIM : H1D025100
- Shift KRS : B
- Shift Baru : A
- Tema (digit terakhir NIM 0/1) : Game

## Deskripsi

Simulasi arena RPG berbasis console. Tiga jenis hero (Warrior, Mage, Archer) mewarisi
superclass abstract `Hero` dan bertarung dengan skill masing-masing.

## Struktur

| File                                       | Peran                    |
| ------------------------------------------ | ------------------------ |
| `Hero.java`                                | Superclass abstract      |
| `Warrior.java`, `Mage.java`, `Archer.java` | Subclass                 |
| `Healable.java`, `Levelable.java`          | Interface                |
| `Main.java`                                | Pengujian 10 persyaratan |

## Pemetaan Persyaratan

| #   | Konsep                    | Lokasi                                                                               |
| --- | ------------------------- | ------------------------------------------------------------------------------------ |
| 1   | State & behavior          | `Hero`: `takeDamage()` (void), `isAlive()`, `getHpPercent()` (return)                |
| 2   | Immutability              | `Hero`: `MAX_LEVEL` (public static final), `name` (private final)                    |
| 3   | Enkapsulasi & overloading | Semua atribut private + getter/setter; konstruktor ganda di `Hero` dan tiap subclass |
| 4   | Inheritance               | `Warrior`, `Mage`, `Archer` extends `Hero`; `Main` terpisah                          |
| 5   | Super chaining            | `super(...)` di konstruktor subclass; `super.showStatus()`                           |
| 6   | Overriding                | `showStatus()` dan `useSkill()` dengan `@Override`                                   |
| 7   | Abstract class            | `Hero` + `abstract void useSkill(Hero)`                                              |
| 8   | Logika state              | `useSkill()` di tiap subclass (if/else membaca & mengubah mana/rage/fokus)           |
| 9   | Polimorfisme interface    | `Healable healer = new Mage("Mira");` dan array `Levelable[]` di `Main`              |
| 10  | Multiple interface        | `Mage implements Healable, Levelable`                                                |

## Compile dan Run

```bash
javac -d out src/*.java
java -cp out Main
```

## Screenshot Luaran

![output](<screenshots/output(1).png>)
![output](<screenshots/output(2).png>)
