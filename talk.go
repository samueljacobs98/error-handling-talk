package main

import (
	"errors"
	"fmt"
)

// func main() {
// fmt.Println("Hello, World!")
// }

func main() {
	// stephCurry(true)
	stephCurry(false)
	fmt.Println("...and he scores!")
}

func lebronJames(succeed bool) error {
	fmt.Println("Lebron with the pass!")
	if (succeed) {
		fmt.Println("AD with the catch")
		return nil
	} else {
		return errors.New("Bad throw by Lebron!")
	}
}

func stephCurry(lebronSucceeds bool) {
	err := lebronJames(lebronSucceeds)
	if err != nil {
		fmt.Println(err)
		fmt.Println("Curry with the catch...")
		return
	}

	fmt.Println("No luck with the steal")
}

