public abstract class FantasyCharacter implements Fighter {
    // Имя персонажа
    private String name;

    // Статы персонажа
    private int healthPoints; // Очки здоровья
    private int strength; // Сила
    private int dexterity; // Ловкость

    // Опыт и золото
    private int xp;
    private int gold;

    // Конструктор
    public FantasyCharacter(String name, int healthPoints, int strength, int dexterity, int xp, int gold) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.dexterity = dexterity;
        this.xp = xp;
        this.gold = gold;
    }

    // Метод для ведения боя

    @Override
    public int attack() {
        if (dexterity * 3 > getRandomValue()) return strength;
        else return 0;
        /*Если наша ловкость, умноженная на 3, больше, чем случайное
         значение, то мы атакуем в размере нашей силы, если нет,
         то возвращаем 0, что приравнивается к промаху.
         */
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
        /*Мы при помощи метода getRandomValue() получаем число
        от 0 до 100
         */
    }

    // Переопределяем вывод в консоль, чтобы выводилось имя и очки здоровья

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, healthPoints);
    }
}
