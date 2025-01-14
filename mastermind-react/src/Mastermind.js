// Components: i) stateless component -> js function
//            ii) stateful component -> (A) class syntax
//            since 16.3                (B) js function + useState() Hook

// State -> Stateful Component
//          Global -> Context API, Reducer API
import {PureComponent} from "react";
import Move from "./Move";
import Card from "./components/common/Card";
import CardHeader from "./components/common/CardHeader";
import CardBody from "./components/common/CardBody";
import Badge from "./components/common/Badge";
import ProgressBar from "./components/common/ProgressBar";
import Button from "./components/common/Button";
import InputText from "./components/common/InputText";
import Table from "./components/common/Table";
import Container from "./components/common/Container";
// level: 3 -> 4 -> ... -> 10 -> wins the game
//  lives: 3
// secret: 549 -> 3615
// number of moves: 4 -> 0
// guess: 123 -> No Match
//        456 -> -2
//        574 -> -1+1
//        548 -> +2
//        549 -> wins
//
class Mastermind extends PureComponent { // Stateful Component
    constructor(props, context) {
        super(props, context);
        this.state = { // Model
            "level": 3,
            "secret": this.createSecret(3),
            "guess": 123,
            "numberOfMoves": 0,
            "maxNumberOfMoves": 10,
            "moves": [],
            lives: 3,
            counter: 60,
            pbCounterWidth: '100%',
            pbCounterClass: "progress-bar bg-success"
        }
        /*
        this.state = { // Model
            game: {
                "level": 3,
                "secret": this.createSecret(3),
                 "guess": 123,
                 "numberOfMoves": 0,
                 "moves": [],
                 "lives": 3,
                 "counter": 60
            },
            constraints: {
                "maxNumberOfMoves": 10,
                "maxCounter": 60
            },
            ui: {
                pbCounterWidth: '100%',
                pbCounterClass: "progress-bar bg-success"
            },
            statistics: {
                "wins": 0,
                "loses": 0
            }
        }*/
    }

    createDigit = (min, max) => {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    createSecret = (numberOfDigits) => {
        const digits = [];

        digits.push(this.createDigit(1, 9));
        while (digits.length < numberOfDigits) {
            const digit = this.createDigit(0, 9);
            if (digits.includes(digit)) continue;
            digits.push(digit);
        }
        let secretNumber = digits.reduce((secret, digit) => 10 * secret + digit);
        console.log(secretNumber);
        return secretNumber;
    }

    initializeGame = (game) => {
        game.secret = this.createSecret(game.level);
        game.numberOfMoves = 0;
        game.moves = [];
    }

    handleInputChange = (e) => {
        let newState = {...this.state};
        newState.guess = Number(e.target.value);
        this.setState(newState, () => {
            console.log(`After state has changed: ${JSON.stringify(this.state)}`);
        });
        console.log(`After setState() call: ${JSON.stringify(this.state)}`);
    };

    alternativeHandleInputChange = (e) => {
        let guess = Number(e.target.value);
        this.setState({guess}, () => {
            console.log(`After state has changed: ${JSON.stringify(this.state)}`);
        });
        console.log(`After setState() call: ${JSON.stringify(this.state)}`);
    };

    componentDidMount() {
        /*
        let localState = localStorage.getItem("mastermind");
        if (localState) {
            localState = JSON.parse(localState);
        }

         */
        setInterval(() => {
            let newGame = {...this.state};
            newGame.counter--;
            if (newGame.counter <= 0) {
                newGame.lives--;
                if (newGame.lives === 0) {
                    //TODO: Player loses
                } else {
                    this.initializeGame(newGame);
                    newGame.counter = 60;
                }
            }
            newGame.pbCounterWidth = ((newGame.counter * 5) / 3) + "%";
            if (newGame.counter < 30) {
                newGame.pbCounterClass = "progress-bar bg-danger";
            } else if (newGame.counter < 40) {
                newGame.pbCounterClass = "progress-bar bg-warning";
            } else if (newGame.counter < 50) {
                newGame.pbCounterClass = "progress-bar bg-info";
            } else {
                newGame.pbCounterClass = "progress-bar bg-success";
            }
            this.setState(newGame, this.saveStateToLocalStorage);
        }, 1_000);
    }

    saveStateToLocalStorage = () => {
        localStorage.setItem("mastermind", JSON.stringify({...this.state}));
    }
    play = () => {
        let newGame = {...this.state};
        newGame.moves = [...this.state.moves];
        newGame.numberOfMoves++;
        if (newGame.secret === newGame.guess) {
            //TODO: player wins at this level
        } else {
            let message = this.createMessage(newGame.secret, newGame.guess);
            newGame.moves.push(new Move(newGame.guess, message));
            if (newGame.numberOfMoves >= newGame.maxNumberOfMoves) {
                newGame.lives--;
                if (newGame.lives <= 0) {
                    // TODO: player loses
                } else {
                    this.initializeGame(newGame);
                }
            }
        }
        this.setState(newGame, () => {
        });
    }

    render() {
        return ( // Model -- bind --> View
            <Container>
                <Card>
                    <CardHeader title="*Game Console*"/>
                    <CardBody>
                        <Badge label={"*Level*"} bgColor={"bg-warning"} value={this.state.level}/>
                        <Badge label={"*Lives*"} bgColor={"bg-danger"} value={this.state.lives}/>
                        <Badge label={"*Number Of Moves*"} bgColor={"bg-info"} value={this.state.numberOfMoves}/>
                        <ProgressBar value={this.state.counter} pbClass={this.state.pbCounterClass}
                                     pbWidth={this.state.pbCounterWidth}/>
                        <div className="mb-3">
                            <InputText value={this.state.guess}
                                       label={"Guess"}
                                       name={"guess"}
                                       placeHolder={"Enter your guess"}
                                       changeHandler={this.alternativeHandleInputChange}/>
                            <Button label={"Play"} buttonClass={"btn-success"} clickHandler={this.play}/>
                        </div>
                    </CardBody>
                </Card>
                <Card>
                    <CardHeader title={"Moves"}/>
                    <CardBody>
                        <Table columns={["Guess", "Message"]} rows={this.state.moves} fields={["guess", "message"]}/>
                    </CardBody>
                </Card>
            </Container>
        );
    }

    createMessage = (secret, guess) => {
        let secretAsString = secret.toString();
        let guessAsString = guess.toString();
        let perfectMatch = 0;
        let partialMatch = 0;
        for (let i = 0; i < guessAsString.length; i++) {
            let g = guessAsString.charAt(i);
            for (let j = 0; j < secretAsString.length; j++) {
                let s = secretAsString.charAt(j);
                if (s === g) {
                    if (i === j) {
                        perfectMatch++;
                    } else {
                        partialMatch++;
                    }
                }
            }
        }
        if (partialMatch === 0 && perfectMatch === 0) {
            return "No Match";
        }
        let message = "";
        if (partialMatch > 0) {
            message = `-${partialMatch}`;
        }
        if (perfectMatch > 0) {
            message = `${message}+${perfectMatch}`;
        }
        return message;
    }
}

export default Mastermind;
