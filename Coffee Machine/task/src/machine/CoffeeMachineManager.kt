package machine

import exceptions.NotEnoughResourcesException


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