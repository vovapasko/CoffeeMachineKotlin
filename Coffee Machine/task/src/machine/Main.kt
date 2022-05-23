package machine

data class Coffee(var waterNeeded: Int, var milkNeeded: Int, var coffeeBeansNeeded: Int, var price: Int)
data class CoffeeMachineConfiguration(
    var WaterAmount: Int,
    var MilkAmount: Int,
    var CoffeeBeansAmount: Int,
    var CupsAmount: Int,
    var MoneyAmount: Int
)

class CoffeeMachine(
    parWaterAmount: Int,
    parMilkAmount: Int,
    parCoffeeBeansAmount: Int,
    parCupsAmount: Int,
    parMoneyAmount: Int
) {
    private var waterAmount: Int = parWaterAmount
    private var milkAmount: Int = parMilkAmount
    private var coffeeBeansAmount: Int = parCoffeeBeansAmount
    private var moneyAmount: Int = parMoneyAmount
    private var cupsAmount: Int = parCupsAmount
    private var coffeeAvailableList: Map<String, Coffee> = mapOf(
        "espresso" to Coffee(250, 0, 16, 4),
        "latte" to Coffee(350, 75, 20, 7),
        "cappuccino" to Coffee(200, 100, 12, 6)
    )

    fun takeMoney(): Int {
        val moneyInMachine = moneyAmount
        this.moneyAmount = 0
        return moneyInMachine
    }

    fun printState() {
        println("The coffee machine has: ")
        println("$waterAmount ml of water")
        println("$milkAmount ml of milk")
        println("$coffeeBeansAmount g of coffee beans")
        println("$cupsAmount of disposable cups")
        println("$$moneyAmount of money")
        println()
    }

    fun buyCoffee(coffeeName: String): Coffee {
        val coffeeToChose = coffeeAvailableList[coffeeName]
            ?: throw NoSuchElementException("No such a coffee $coffeeName in available coffee list")
        checkResourcesAmount(coffeeToChose)
        this.milkAmount -= coffeeToChose.milkNeeded
        this.waterAmount -= coffeeToChose.waterNeeded
        this.coffeeBeansAmount -= coffeeToChose.coffeeBeansNeeded
        this.moneyAmount += coffeeToChose.price
        this.cupsAmount -= 1
        return coffeeToChose
    }

    fun fillMachine(waterAmount: Int, milkAmount: Int, coffeeBeans: Int, cupsAmount: Int) {
        this.waterAmount += waterAmount
        this.milkAmount += milkAmount
        this.coffeeBeansAmount += coffeeBeans
        this.cupsAmount += cupsAmount
    }

    private fun checkResourcesAmount(coffee: Coffee) {
        val newMilkAmountEnough: Boolean = (this.milkAmount - coffee.milkNeeded) > 0
        val newWaterAmountEnough: Boolean = (this.waterAmount - coffee.waterNeeded) > 0
        val newCoffeeBeansAmountEnough: Boolean = (this.coffeeBeansAmount - coffee.coffeeBeansNeeded) > 0
        val newCupsAmountEnough: Boolean = (this.cupsAmount - 1) > 0
        when {
            !newMilkAmountEnough -> throw NotEnoughResourcesException("Sorry, not enough milk!")
            !newWaterAmountEnough -> throw NotEnoughResourcesException("Sorry, not enough water!")
            !newCoffeeBeansAmountEnough -> throw NotEnoughResourcesException("Sorry, not enough coffee beans!")
            !newCupsAmountEnough -> throw NotEnoughResourcesException("Sorry, not enough cups!")
        }
    }
}

class NotEnoughResourcesException(message: String) : Throwable(message)

class CoffeeMachineManager(coffeeMachineConfiguration: CoffeeMachineConfiguration) {
    private val coffeeMachine = CoffeeMachine(
        coffeeMachineConfiguration.WaterAmount, coffeeMachineConfiguration.MilkAmount,
        coffeeMachineConfiguration.CoffeeBeansAmount, coffeeMachineConfiguration.CupsAmount,
        coffeeMachineConfiguration.MoneyAmount
    )
    private val coffeeAlias: Map<Int, String> = mapOf(
        1 to "espresso",
        2 to "latte",
        3 to "cappuccino"
    )

    fun startMachine() {
        var usersAction: String = requestAction()
        while (usersAction != "exit") {
            println()
            when (usersAction) {
                "buy" -> buyCoffee()
                "fill" -> fillCoffee()
                "take" -> takeMoneyFromMachine()
                "remaining" -> this.coffeeMachine.printState()
            }
            usersAction = requestAction()
        }
    }

    private fun takeMoneyFromMachine() {
        val moneyToTake = this.coffeeMachine.takeMoney()
        println("I gave you $$moneyToTake")
        println()
    }

    private fun fillCoffee() {
        print("Write how many ml of water do you want to add: ")
        val waterToAdd = readln().toInt()
        print("Write how many ml of milk do you want to add: ")
        val milkToAdd = readln().toInt()
        print("Write how many grams of coffee beans do you want to add: ")
        val coffeeToAdd = readln().toInt()
        print("Write how many disposable cups of coffee do you want to add: ")
        val cupsToAdd = readln().toInt()
        this.coffeeMachine.fillMachine(waterToAdd, milkToAdd, coffeeToAdd, cupsToAdd)
        println()
    }

    private fun buyCoffee() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        val coffeeToBuyChoice = readln()
        if (coffeeToBuyChoice == "back") {
            println()
            return
        }
        try {
            val coffeeToBuy = this.coffeeAlias[coffeeToBuyChoice.toInt()]!!
            try {
                this.coffeeMachine.buyCoffee(coffeeToBuy)
                println("I have enough resources, making you a coffee!")
            } catch (e: NotEnoughResourcesException) {
                println(e.message)
            }
        } catch (e: NullPointerException) {
            println("There is no such an option")
        }
        println()
    }


    private fun requestAction(): String {
        print("Write action (buy, fill, take, remaining, exit): ")
        return readln()
    }

}


fun main() {
    val coffeeMachineManager = CoffeeMachineManager(
        CoffeeMachineConfiguration(
            400, 540, 120, 9,
            550
        )
    )
    coffeeMachineManager.startMachine()
}

