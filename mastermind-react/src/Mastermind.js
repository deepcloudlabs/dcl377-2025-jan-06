// Components: i) stateless component -> js function
//            ii) stateful component -> (A) class syntax
//            since 16.3                (B) js function + Hooks

// State -> Stateful Component
//          Global -> Context API, Reducer API
import {PureComponent} from "react";
import Move from "./Move";
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
            lives: 3
        }
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

    play = () => {
        let newGame = {...this.state};
        newGame.moves = [...this.state.moves];
        newGame.numberOfMoves++;
        if (newGame.secret === newGame.guess) {
            //TODO: player wins at this level
        } else {
            let message = this.createMessage(newGame.secret,newGame.guess);
            newGame.moves.push(new Move(newGame.guess,message));
            if (newGame.numberOfMoves >= newGame.maxNumberOfMoves) {
                    newGame.lives--;
                    if (newGame.lives <= 0){
                        // TODO: player loses
                    } else {
                        this.initializeGame(newGame);
                    }
            }
        }
        this.setState(newGame, () => {});
    }

    render() {
        return ( // Model -- bind --> View
            <div className="Container">
                <p></p>
                <div className="card">
                    <div className="card-header">
                        <h4 className="card-title">Game Console</h4>
                    </div>
                    <div className="card-body">
                        <div className="mb-3">
                            Level:<span className="badge bg-info">{this.state.level}</span>
                        </div>
                        <div className="mb-3">
                            Lives:<span className="badge bg-info">{this.state.lives}</span>
                        </div>
                        <div className="mb-3">
                            Moves:<span className="badge bg-info">{this.state.numberOfMoves} of {this.state.maxNumberOfMoves}</span>
                        </div>
                        <div className="mb-3">
                            <label className="form-label" htmlFor="guess">Guess:</label>
                            <input type="number"
                                   id="guess"
                                   name="guess"
                                   className="form-control"
                                   placeholder="Enter your guess"
                                   onChange={this.alternativeHandleInputChange}
                                   value={this.state.guess} />
                            <button className="btn btn-success"
                            onClick={this.play}>Play</button>
                        </div>
                    </div>
                </div>
                <div className="card">
                    <div className="card-header">
                        <h4 className="card-title">Moves</h4>
                    </div>
                    <div className="card-body">
                        <table className="table table-bordered table-hover table-responsive table-striped">
                            <thead>
                                <tr>
                                    <th>Guess</th>
                                    <th>Message</th>
                                </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.moves.map((move, i) => (
                                    <tr key={move.guess*i*17}>
                                        <td>{move.guess}</td>
                                        <td>{move.message}</td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }

    createMessage = (secret, guess)=> {
        let secretAsString = secret.toString();
        let guessAsString = guess.toString();
        let perfectMatch = 0;
        let partialMatch = 0;
        for (let i = 0; i < guessAsString.length; i++) {
            let g = guessAsString.charAt(i);
            for (let j = 0; j < secretAsString.length; j++) {
                let s = secretAsString.charAt(j);
                if (s === g){
                    if (i === j){
                        perfectMatch++;
                    } else {
                        partialMatch++;
                    }
                }
            }
        }
        if (partialMatch === 0 && perfectMatch === 0){
            return "No Match";
        }
        let message = "";
        if (partialMatch > 0){
            message = `-${partialMatch}`;
        }
        if (perfectMatch > 0){
            message = `${message}+${perfectMatch}`;
        }
        return message;
    }
}

export default Mastermind;
