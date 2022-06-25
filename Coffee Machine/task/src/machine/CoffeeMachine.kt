package machine

import exceptions.NotEnoughResourcesException

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