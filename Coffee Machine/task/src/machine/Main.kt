package machine


fun main() {
    val coffeeMachineManager = CoffeeMachineManager(
        CoffeeMachineConfiguration(
            400, 540, 120, 9,
            550
        )
    )
    coffeeMachineManager.startMachine()
}

