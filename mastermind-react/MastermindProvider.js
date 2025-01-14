import {createSecret} from "./utility/mastermind-utility";
import {createContext, useReducer} from "react";
import MastermindStateless from "./MastermindStateless";
import constraintReducer from "./reducer/ConstraintRedcuer";
import GameReducer from "./reducer/GameReducer";

const initialSecret = createSecret(3);
export const gameInitialState = {
    level: 3,
    lives: 3,
    moves: [],
    secret: initialSecret,
    guess: 123,
    numberOfMoves: 0,
    playerWins: false,
    playerLoses: false
};
export const constraintInitialState =
    {
        counter: 60,
        maxNumberOfMoves: 10,
        pbCounterWidth: '100%',
        pbCounterClass: "progress-bar bg-success"
    };
export const GameContext = createContext(null);
export const ConstraintContext = createContext(null);

export default function MastermindProvider() {
    const [game, dispatchGame] = useReducer(GameReducer, gameInitialState);
    const [constraint, dispatchConstraint] = useReducer(constraintReducer, constraintInitialState);
    return (
        <GameContext.Provider value={{game, dispatchGame}}>
            <ConstraintContext.Provider value={{constraint, dispatchConstraint}}>
                <MastermindStateless></MastermindStateless>
            </ConstraintContext.Provider>
        </GameContext.Provider>
    );
}
