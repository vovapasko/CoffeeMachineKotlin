# Virtual Coffee Machine

This task is my implementation of the [hyperskill course](https://hyperskill.org/projects/67). 




<h5 id="description" style="text-align: center;">Description</h5>

<p>Let's redesign our program and write a class that represents the coffee machine. The class should have a method that takes a string as input. Every time the user inputs a line (a string) to the console, the program invokes this method with one argument: the line that user input to the console. This system simulates pretty accurately how real-world electronic devices work. External components (like buttons on the coffee machine or tapping on the screen) generate events that pass into the single interface of the program.</p>

<p>The class should not use system input at all; it only should handle input that comes to it via this method and its string argument.</p>



<h5 id="output-example" style="text-align: center;">Output example</h5>

<p>Your coffee machine should have the same initial resources as in the example (400 ml of water, 540 ml of milk, 120 g of coffee beans, 9 disposable cups, $550 in cash.<br>

<pre><code class="language-no-highlight">Write action (buy, fill, take, remaining, exit): &gt; remaining

The coffee machine has:
400 of water
540 of milk
120 of coffee beans
9 of disposable cups
$550 of money

Write action (buy, fill, take, remaining, exit): &gt; buy

What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: &gt; 2
I have enough resources, making you a coffee!

Write action (buy, fill, take, remaining, exit): &gt; remaining

The coffee machine has:
50 of water
465 of milk
100 of coffee beans
8 of disposable cups
$557 of money

Write action (buy, fill, take, remaining, exit): &gt; buy

What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: &gt; 2
Sorry, not enough water!

Write action (buy, fill, take, remaining, exit): &gt; fill

Write how many ml of water do you want to add: &gt; 1000
Write how many ml of milk do you want to add: &gt; 0
Write how many grams of coffee beans do you want to add: &gt; 0
Write how many disposable cups of coffee do you want to add: &gt; 0

Write action (buy, fill, take, remaining, exit): &gt; remaining

The coffee machine has:
1050 of water
465 of milk
100 of coffee beans
8 of disposable cups
$557 of money

Write action (buy, fill, take, remaining, exit): &gt; buy

What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: &gt; 2
I have enough resources, making you a coffee!

Write action (buy, fill, take, remaining, exit): &gt; remaining

The coffee machine has:
700 of water
390 of milk
80 of coffee beans
7 of disposable cups
$564 of money

Write action (buy, fill, take, remaining, exit): &gt; take

I gave you $564

Write action (buy, fill, take, remaining, exit): &gt; remaining

The coffee machine has:
700 of water
390 of milk
80 of coffee beans
7 of disposable cups
$0 of money

Write action (buy, fill, take, remaining, exit): &gt; exit</code></pre>