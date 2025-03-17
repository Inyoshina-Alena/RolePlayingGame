public class BattleScene {

    /*Метод, который вызывается при наличии боя,
     сюда мы передаем ссылки на нашего героя и монстра,
     который встал у него на пути
     */
    public  void fight (FantasyCharacter hero, FantasyCharacter monster, Realm.FightCallback fightCallback) {
        Runnable runnable = () -> {

            // Сюда будем записывать, какой сейчас ход по счету
            int turn = 1;

            // Когда бой будет закончен мы
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                // Войны бьют по очереди, поэтому здесь мы описываем логику смены сторон
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                }else{
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }
                try {
                    // Чтобы бой не проходил за секунду, сделаем имитацию работы, как если бы
                    // у нас была анимация
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //Запускаем новый поток
        Thread thread = new Thread(runnable);
        thread.start();
    }
    // Метод для совершения удара
    private Boolean makeHit (FantasyCharacter defender, FantasyCharacter attacker, Realm.FightCallback fightCallback) {

        // Получаем сулу удара
        int hit = attacker.attack();

        // Отнимаем количество урона из здоровья защищающегося
        int defenderHealt = defender.getHealthPoints() - hit;

        // Если атака прошла, выводим в консоль сообщение об этом
        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s Осталось %d единиц здоровья...", defender.getName(), defenderHealt));
        }else{
            // Если атакующий промахнулся (т.е. урон не 0), выводим это сообщение
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }

        // Если здоровье меньше 0 и если защищающийся был героем, то игра заканчивается
        if (defenderHealt <= 0 && defender instanceof Hero) {
            System.out.println("Извините, вы пали в бою...");
            //Вызываем коллбэк, что мы проиграли
            fightCallback.fightLost();
            return true;

            // Если здоровья больше нет и защищающийся монстр, то мы забираем от монстра его опыт и золото
        }else if (defenderHealt <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            // Вызываем коллбэк, что мы победили
            fightCallback.fightWin();
            return true;
        }else{
            // Если защищающийся не повержен, то мы устанавливаем ему новый уровень здоровья
            defender.setHealthPoints(defenderHealt);
            return false;
        }


    }
}