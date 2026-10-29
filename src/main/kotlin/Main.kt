fun printBothFields(player: Player, enemy: Player, showEnemyShips: Boolean = false) {
    if (player.field.size != enemy.field.size) {
        println("Ошибка: размеры полей не совпадают!")
        return
    }

    val size = player.field.size
    println("=== Поле игрока ${player.name} ===".padEnd(size * 2 + 15) + "=== Поле игрока ${enemy.name} ===")

    print("   ")
    for (c in 0 until size) print("$c ")
    print("    ")
    print("   ")
    for (c in 0 until size) print("$c ")
    println()

    for (r in 0 until size) {
        print("$r  ")
        for (c in 0 until size) print("${player.field[r][c]} ")
        print("    ")
        print("$r  ")
        for (c in 0 until size) {
            val cell = enemy.field[r][c]
            if (cell == '#' && !showEnemyShips) print(". ") else print("$cell ")
        }
        println()
    }
}

fun printFields(players: Array<Player>, showShips: BooleanArray) {
    if (players.isEmpty()) return
    val size = players[0].field.size

    for (i in players.indices) {
        if (players[i].field.size != size) {
            println("Ошибка: поля имеют разный размер!")
            return
        }
    }

    val fieldWidth = 3 + size * 2 + 2

    for (i in players.indices) {
        val title = players[i].name
        val padding = (fieldWidth - title.length) / 2
        print(" ".repeat(maxOf(0, padding)) + title)
        print(" ".repeat(maxOf(0, fieldWidth - title.length - padding)))
    }
    println()

    for (i in players.indices) {
        print("   ")
        for (c in 0 until size) print("$c ")
        print("  ")
    }
    println()

    for (r in 0 until size) {
        for (i in players.indices) {
            print("$r  ")
            for (c in 0 until size) {
                val cell = players[i].field[r][c]
                if (cell == '#' && !showShips[i]) print(". ") else print("$cell ")
            }
            print("  ")
        }
        println()
    }
}

fun main() {
    val player = Player()
    val enemy = Player()
    player.name = "Вы"
    enemy.name = "Компьютер"

    player.registerShot(true)
    player.registerShot(false)
    player.registerShot(true)
    println(player.status())

    player.takeDamage()
    player.takeDamage()
    println("Палуб осталось: ${player.shipsLeft}, Жив: ${player.isAlive()}")

    player.field[3][2] = '#'
    player.field[3][3] = '#'
    enemy.field[5][7] = '#'
    enemy.field[5][5] = 'X'
    enemy.field[5][6] = 'O'

    printBothFields(player, enemy)

    player.printField(debug = true)

    val thirdPlayer = Player()
    thirdPlayer.name = "Третий"
    thirdPlayer.field[0][0] = 'O'

    printFields(
        arrayOf(player, enemy, thirdPlayer),
        booleanArrayOf(true, false, false)
    )

    player.reset()
    player.printStats()
}