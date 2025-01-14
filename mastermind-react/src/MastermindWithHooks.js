// 1. Component-based Programming
//    i) Stateful Component
//       A) Class
//       B) React Hooks -> function -> useState()
//   ii) * Stateless Component
//       C) function
// level  -> 3
// secret -> 549
// 123 -> No match
// 456 -> -2
// 576 -> +1
// 584 -> -1 +1
// 549 -> level -> 4, secret -> 3615
// 1234 -> -2
// 60 sec. + max move 10, lives: 3
import { useEffect, useState} from "react";
import Move from "./model/Move";
import initializeGame, {createSecret} from "./utility/mastermind-utility";
import ProgressBar from "./component/common/ProgressBar";
import DisabledInput from "./component/common/DisabledInput";
import Container from "./component/common/Container";
import {useNavigate} from "react-router";

const initialSecret = createSecret(3);
function MastermindHook() { // Stateful Component
    let [game, setGame] = useState({
        level: 3,
        lives: 3,
        moves: [],
        secret: initialSecret,
        guess: 123,
        numberOfMoves: 0
    });
    let [constraint, setConstraint] = useState({
        counter: 60,
        maxNumberOfMoves: 10,
        pbCounterWidth: '100%',
        pbCounterClass: "progress-bar bg-success"
    });
    const navigate = useNavigate();
    useEffect(()=>{
        let mastermind = JSON.parse(localStorage.getItem("mastermind"));
        if (mastermind.game){
            setGame(mastermind.game);
        }
        if (mastermind.constraint){
            setConstraint(mastermind.constraint);
        }
    }, []);
    useEffect(() => {
        let timer = setInterval(() => {
            let newConstraint = {...constraint};
            let newGame = {...game};
            newConstraint.counter--;
            if (newConstraint.counter <= 0) {
                newGame.lives--;
                if (newGame.lives === 0) {
                    navigate("/loses");
                } else {
                    initializeGame(newGame);
                    newConstraint.counter = 60;
                }
            }
            newConstraint.pbCounterWidth = ((newConstraint.counter * 5) / 3) + "%";
            if (newConstraint.counter < 30) {
                newConstraint.pbCounterClass = "progress-bar bg-danger";
            } else if (newConstraint.counter < 40) {
                newConstraint.pbCounterClass = "progress-bar bg-warning";
            } else if (newConstraint.counter < 50) {
                newConstraint.pbCounterClass = "progress-bar bg-info";
            } else {
                newConstraint.pbCounterClass = "progress-bar bg-success";
            }
            setGame(newGame);
            setConstraint(newConstraint);
        }, 1_000);
        return ()=>{
            clearInterval(timer);
        }
    });

    function play() {
        let newGame = {...game};
        let newConstraint = {...constraint};
        newGame.numberOfMoves++;
        if (newGame.secret === newGame.guess) {
            newGame.level++;
            if (newGame.level > 10) {
                navigate("/wins");
            } else {
                initializeGame(newGame);
            }
        } else {
            if (newGame.numberOfMoves > newConstraint.maxNumberOfMoves) {
                newGame.lives--;
                if (newGame.lives <= 0) {
                    navigate("/loses");
                } else {
                    initializeGame(newGame);
                }
            } else {
                newGame.moves = [...newGame.moves, new Move(newGame)];
            }
        }
        setGame(newGame);
        setConstraint(newConstraint);
        saveStateToLocalStorage();
    }

    function saveStateToLocalStorage() {
        localStorage.setItem("mastermind", JSON.stringify({...game,...constraint}))
    }

    function handleInputChange(event) {
        let guess = event.target.value;
        let newGame = {...game};
        newGame.guess = Number(guess);
        setGame(newGame);
    }

    return ( // View
        <Container>
            <div className="card">
                <div className="card-header">
                    <h4 className="card-title">Mastermind Game Board -- React Hooks + React Router version</h4>
                </div>
                <div className="card-body">
                    <div className="mb-3">
                        <DisabledInput id="gameLevel"
                                       label="Game Level"
                                       value={game.level}
                                       name="gameLevel"></DisabledInput>
                    </div>
                    <div className="mb-3">
                        <DisabledInput id="lives"
                                       label="Lives"
                                       value={game.lives}
                                       name="lives"></DisabledInput>
                    </div>
                    <div className="mb-3">
                        <DisabledInput id="numberOfMoves"
                                       label="Number of Moves"
                                       value={game.numberOfMoves}
                                       name="numberOfMoves"></DisabledInput>
                    </div>
                    <div className="mb-3">
                        <ProgressBar pbColor={constraint.pbCounterClass}
                                     pbWidth={constraint.pbCounterWidth}
                                     value={constraint.counter}></ProgressBar>
                    </div>
                    <div className="mb-3">
                        <div className="form-floating">
                            <input type="number"
                                   className="form-control"
                                   id="guess"
                                   name="guess"
                                   onChange={handleInputChange}
                                   value={game.guess}/>
                            <label htmlFor="guess">Guess</label>
                            <button className="btn btn-success"
                                    onClick={play}>Play
                            </button>
                        </div>
                    </div>
                    <div className="mb-3">
                        <table className="table table-bordered table-hover table-striped table-responsive">
                            <thead>
                            <tr>
                                <th>No</th>
                                <th>Guess</th>
                                <th>Partial Match</th>
                                <th>Perfect Match</th>
                                <th>Message</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                game.moves.map(
                                    (move, index) =>
                                        <tr key={7 * move.guess + index}>
                                            <td>{index + 1}</td>
                                            <td>{move.guess}</td>
                                            <td>{move.partial}</td>
                                            <td>{move.perfect}</td>
                                            <td>{move.message}</td>
                                        </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </Container>
    );
}

export default MastermindHook;
