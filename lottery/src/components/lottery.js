// stateful component -> js class
import React from "react";
import TableHeader from "./table-header";
import Table from "./table";
import TableBody from "./table-body";
import Card from "./card";
import CardHeader from "./card-header";
import CardBody from "./card-body";
import FormGroup from "./form-group";
import Button from "./button";
import InputText from "./input-text";

class Lottery extends React.PureComponent {
    constructor(props, context) {
        super(props, context);
        this.state = { // View Model --> View
            numbers: [],
            column: 3
        }

    }

    componentDidMount() {
        console.log("Component is up and running and waiting for your events!");
        this.draw()
    }

    generateRandomNumber = (min, max) => {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    generateOneLotteryNumber = () => {
        let numbers = [this.generateRandomNumber(1, 60)];
        while (numbers.length < 6) {
            let number = this.generateRandomNumber(1, 60);
            if (numbers.includes(number)) continue;
            numbers.push(number);
        }
        numbers.sort((x, y) => x - y);
        return numbers;
    }

    generateLotteryNumbers = (column) => {
        let numbers = [];
        for (let i = 0; i < column; i++) {
            numbers.push(this.generateOneLotteryNumber())
        }
        return numbers;
    }

    reset = (event) => {
        this.setState({numbers: [], column: this.state.column + 1})
    }

    draw = (event) => {
        let newNumbers = [...this.state.numbers];
        this.generateLotteryNumbers(this.state.column).forEach(row => newNumbers.push(row));
        this.setState({numbers: newNumbers}, () => {
            console.log(newNumbers)
        });
    }

    handleChange = (event) => {
        let newState = {...this.state};
        newState.column = Number(event.target.value);
        this.setState({column: Number(event.target.value)}, () => {
            console.log(this.state.column);
        }); // async
    }
    render = () => {
        let numbersTable = "";
        if (this.state.numbers.length > 0) {
            numbersTable = (
                <Table className="table table-hover table-striped">
                    <TableHeader headerNames="Row Number,Number #1,Number #2,Number #3,Number #4,Number #5,Number #6"></TableHeader>
                    <TableBody values={this.state.numbers} />
                </Table>
            );
        }
        return (
            <Card>
                <CardHeader title="Lottery" />
                <CardBody>
                    <FormGroup>
                        <InputText label="Column"
                                   htmlFor="column"
                                   value={this.state.column}
                                   handleInput={this.handleChange}></InputText>
                        <Button className="btn btn-primary" onClick={this.draw} label="Draw"></Button>
                        <Button className="btn btn-warning" onClick={this.reset} label="Reset"></Button>
                    </FormGroup>
                    {numbersTable}
                </CardBody>
            </Card>)
    }

}

export default Lottery;
