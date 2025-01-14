import React, {useEffect, useState} from "react";
import Table from "./components/table";
import TableHeader from "./components/table-header";
import TableBody from "./components/table-body";
import Card from "./components/card";
import CardHeader from "./components/card-header";
import CardBody from "./components/card-body";
import FormGroup from "./components/form-group";
import InputText from "./components/input-text";
import Button from "./components/button";
import {generateLotteryNumbers} from "./utility";

export default function LotteryHooks() {
    const [numbers, setNumbers] = useState([]);
    const [column, setColumn] = useState(3);
    const handleChange = (event) => {
        setColumn(Number(event.target.value));
    }

    useEffect(() => {
        console.log("Component is up and running and waiting for your events!");
        draw();
        return () => {
           console.log("Component is destroyed!")
        };
    }, []);
    const draw = (event) => {
        let newNumbers = [...numbers];
        generateLotteryNumbers(column).forEach(row => newNumbers.push(row));
        setNumbers(newNumbers);
    }

    const reset = (event) => {
        setNumbers([]);
    }

    let numbersTable = "";
    if (numbers.length > 0) {
        numbersTable = (
            <Table className="table table-hover table-striped">
                <TableHeader
                    headerNames="Row Number,Number #1,Number #2,Number #3,Number #4,Number #5,Number #6"></TableHeader>
                <TableBody values={numbers}/>
            </Table>
        );
    }
    return (
        <Card>
            <CardHeader title="Lottery"/>
            <CardBody>
                <FormGroup>
                    <InputText label="Column"
                               htmlFor="column"
                               value={column}
                               handleInput={handleChange}></InputText>
                    <Button className="btn btn-primary" onClick={draw} label="Draw"></Button>
                    <Button className="btn btn-warning" onClick={reset} label="Reset"></Button>
                </FormGroup>
                {numbersTable}
            </CardBody>
        </Card>)
}
