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
import {useContext, useEffect} from "react";
import ProgressBar from "./component/common/ProgressBar";
import DisabledInput from "./component/common/DisabledInput";
import Container from "./component/common/Container";
import {ConstraintContext, GameContext} from "./MastermindProvider";
import {useNavigate} from "react-router";

function MastermindStateless() { // Stateful Component

    const {game, dispatchGame} = useContext(GameContext);
    const {constraint, dispatchConstraint} = useContext(ConstraintContext);
    useEffect(() => {
        const timer = setInterval(() => dispatchConstraint({type: "COUNT_DOWN"}), 1_000);
        return () => clearInterval(timer)
    });
    const navigate = useNavigate();
    useEffect(()=>{
        if (game.playerWins){
            navigate("/wins");
            return;
        }
        else if (game.playerLoses){
            navigate("/loses");
            return;
        }
    }, [game.playerWins, game.playerLoses,navigate])

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
                                   onChange={(event) => dispatchGame({type: "GUESS_CHANGED", event})}
                                   value={game.guess}/>
                            <label htmlFor="guess">Guess</label>
                            <button className="btn btn-success"
                                    onClick={() => dispatchGame({type: "PLAY"})}>Play
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

export default MastermindStateless;
