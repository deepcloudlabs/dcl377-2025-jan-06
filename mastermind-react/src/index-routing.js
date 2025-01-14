import React from 'react';
import ReactDOM from 'react-dom/client';
import "bootstrap/dist/css/bootstrap.css";
import {Route, Routes} from "react-router";
import PlayerWins from "./wins/PlayerWins";
import PlayerLoses from "./loses/PlayerLoses";
import {BrowserRouter} from "react-router-dom";
import MastermindProvider from "./MastermindProvider";

const routing =
    <Routes>
        <Route path="/" element={<MastermindProvider></MastermindProvider>}></Route>
        <Route path="/wins" element={<PlayerWins></PlayerWins>}></Route>
        <Route path="/loses" element={<PlayerLoses></PlayerLoses>}></Route>
    </Routes>
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
        {routing}
    </BrowserRouter>
);
