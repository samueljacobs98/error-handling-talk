/**
 * Dealing with the undersired path:
 * Error handling in JavaScript
 */

// 1. try-catch-finally

function lebronJames(succeed) {
  console.log("Lebron with the pass!");
  if (succeed) {
    console.log("AD with the catch...");
  } else {
    throw new Error("Bad throw by Lebron!");
  }
}

// lebronJames(true);
// lebronJames(false);

function stephCurry(lebronSucceeds) {
  try {
    lebronJames(lebronSucceeds);
  } catch (err) {
    console.log(err.message);
    console.log("Curry with the catch...");
  } finally {
    console.log("...and he scores!");
  }
}

// we could...
function stephCurry(lebronSucceeds) {
  try {
    lebronJames(lebronSucceeds);
  } catch (err) {
    console.log(err.message);
    console.log("Curry with the catch...");
  }

  console.log("...and he scores!");
}

// stephCurry(true);
// stephCurry(false);

/**
 * The issues of handling errors in this way:
 *  - Pollutes code with error handling logic
 *  - Doesn't adhere to the single responsibility principle
 */

function tryCatchFinally(func, handle, final) {
  try {
    func();
  } catch (err) {
    handle(err);
  } finally {
    final();
  }
}

function curryWithTheSteal(err) {
  console.log(err.message);
  console.log("Curry with the catch...");
}

function heScores() {
  console.log("...and he scores!");
}

// need lazy evaluation
// tryCatchFinally(() => lebronJames(true), curryWithTheSteal, heScores);
// tryCatchFinally(() => lebronJames(false), curryWithTheSteal, heScores);
