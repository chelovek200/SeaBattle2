class Player {
    var name: String = ""
    var field: Array<CharArray> = Array(10) { CharArray(10) { '.' } }
    var shots: Int = 0
    var hits: Int = 0
    var shipsLeft: Int = 20

    fun isAlive(): Boolean {
        return shipsLeft > 0
    }

    fun accuracy(): Double {
        if (shots == 0) return 0.0
        return hits * 100.0 / shots
    }

    fun status(): String {
        val acc = String.format("%.1f", accuracy())
        return "Игрок $name: палуб осталось $shipsLeft, выстрелов $shots, попаданий $hits, точность $acc%"
    }

    fun takeDamage(): Boolean {
        if (shipsLeft > 0) shipsLeft--
        return shipsLeft > 0
    }

    fun registerShot(hit: Boolean) {
        shots++
        if (hit) hits++
    }

    fun reset() {
        shots = 0
        hits = 0
        shipsLeft = 20
        for (r in field.indices) {
            for (c in field[r].indices) {
                field[r][c] = '.'
            }
        }
    }

    fun printField(showShips: Boolean = true, debug: Boolean = false) {
        val size = field.size
        val header = if (debug) "=== Поле игрока $name (debug) ===" else "=== Поле игрока $name ==="
        println(header)

        fun getCellChar(r: Int, c: Int): Char {
            val cell = field[r][c]
            return if (cell == '#' && !showShips) '.' else cell
        }

        if (debug) {
            print("   ")
            println("+---".repeat(size) + "+")
            for (r in 0 until size) {
                print("$r  ")
                for (c in 0 until size) {
                    print("| ${getCellChar(r, c)} ")
                }
                println("|")
                print("   ")
                println("+---".repeat(size) + "+")
            }
        } else {
            print("   ")
            for (c in 0 until size) print("$c ")
            println()
            for (r in 0 until size) {
                print("$r  ")
                for (c in 0 until size) {
                    print("${getCellChar(r, c)} ")
                }
                println()
            }
        }
    }

    fun printStats() {
        println(status())
    }
}