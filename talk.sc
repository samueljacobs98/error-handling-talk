
/**
 * Scala is a language where values are almost preferred to variables.
 *
 * val - the keyword for defining a value
 * myValue - the name for my value
 * :String - the type annotation, here, myValue is a String, = "Hello, world!"
  */
val myValue: String = "Hello, world!"

/* Traits are a way to define reusable components that can be mixed into classes
   In our case, custom errors for basketball */
trait MyError {
  val message: String

  def prettyMessage: String = s"Error: $message!"

}

// An example of a case class using trait MyError
case class BadPassError(name: String, message: String) extends MyError {
  override def prettyMessage: String = s"$message by $name!"
}

// Instantiated:
val badPassByLebron = BadPassError("Lebron", "Bad throw")
println(badPassByLebron.prettyMessage) // "Bad throw by Lebron!"

/* Let's model with types like we did before in Go...
 ... but also take advantage of Scala's Option */

/* If something is optional, that means it value may or may not exist.
   Here we have Option[MyError], which we're using to model an optional error case.
   If an error is present, we will get Some(value), where `value` is our error.
   If there is no value, we will get None.
   Think of it like a useful wrapper around a value that may or may not be there. */
def lebronJames(succeed: Boolean): Option[MyError] = {
  println("Lebron with the pass!")
  if (succeed) {
    println("AD with the catch")
    None
  } else {
    Some(BadPassError("Lebron", "Bad throw"))
  }
}

def stephCurry(lebronSucceeds: Boolean): Unit = {
  val nameOfScorer = lebronJames(lebronSucceeds) match {
    case Some(error) =>
      println(error)
      println("Curry with the catch...")
      "Curry"
    case None =>
      println("No luck with the steal...")
      "AD"
  }
  println(s"...and $nameOfScorer scores!")
}

stephCurry(true)
stephCurry(false)

// Okay, but this doesn't really give us much more than previously...


// Modelling a basketball player
trait Player {
  val name: String
}

case class Warrior(name: String) extends Player

case class Laker(name: String) extends Player {
  def passTo(player: Laker, stealer: Option[Warrior] = None): Either[Warrior, Laker] = {
      println(s"${this.name} with the pass!")
    stealer match {
      case Some(thief) =>
        println(s"Intercepted by $thief!")
        Left(thief)
      case _ =>
        Right(player)
    }
  }
}



val anthonyDavis = Laker("AD")
val stephenCurry = Warrior("Steph")
val lebronJames = Laker("Lebron")

val receiver1 = lebronJames.passTo(anthonyDavis, Some(stephenCurry))
val receiver2 = lebronJames.passTo(anthonyDavis, None)

val next = receiver2 match {
  case Right(scorer: Laker) => s"${scorer.name} for the Lakers!"
  case Left(scorer: Warrior) => s"${scorer.name} for the Warriors!"
}

println(next)
