println("Hello, world!")

trait Error {
  val name: String
  val message: String
}

case class BadPassError(name: String, message: String) extends Error {
  override def toString: String = s"$message by $name!"
}

//def lebronJames(succeed: Boolean): Option[Error] = {
//  println("Lebron with the pass!")
//  if (succeed) {
//    println("AD with the catch")
//    None
//  } else {
//    Some(BadPassError("Lebron", "Bad throw"))
//  }
//}
//
//def stephCurry(lebronSucceeds: Boolean): Unit = {
//  val nameOfScorer = lebronJames(lebronSucceeds) match {
//    case Some(error) => {
//      println(error)
//      println("Curry with the catch...")
//      "Curry"
//    }
//    case None => {
//      println("No luck with the steal...")
//      "AD"
//    }
//  }
//  println(s"...and $nameOfScorer scores!")
//}
//
//stephCurry(true)
//stephCurry(false)

trait Player {
  val name: String
  type PassOutcome
}

trait Laker extends Player {
  type PassOutcome = Either[Warrior, Laker]
}

trait Warrior extends Player {
  type PassOutcome = Either[Laker, Warrior]
}

case class AD() extends Laker {
  val name = "AD"
}

case class Curry() extends Laker {
  val name = "Steph"
}

case class Lebron() extends Laker {
  val name = "Lebron"


  def passTo(receiver: Laker, stealer: Option[Warrior] = None): PassOutcome = {
    println(s"$name with the pass!")
    stealer match {
      case Some(thief: Warrior) => Left(thief)
      case _ => Right(receiver)
    }
  }
}

val lebron = Lebron()
val steph = Curry()
val ant = AD()

val receiver = lebron.passTo(ant)

val next = receiver match {
  case Right(scorer: Laker) => s"${scorer.name} for the Lakers!"
  case Left(scorer: Warrior) => s"${scorer.name} for the Warriors!"
}

println(next)
