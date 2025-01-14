import Container from "./common/container";
import Card from "./common/card";
import CardHeader from "./common/card-header";
import CardBody from "./common/card-body";
import {useEffect, useState} from "react";
import io from "socket.io-client";
import {Line} from "react-chartjs-2";
import {CategoryScale, Chart as ChartJS, Tooltip, Legend, LinearScale, LineElement, PointElement, Title} from "chart.js";
import SelectBox from "./common/select-box";
import FormGroup from "./common/form-group";

const socket = io("ws://localhost:5000");
const options = {
    responsive: false,
    animation: false,
    maintainAspectRatio: false,
    plugins: {
        legend: {
            position: 'top',
        },
        title: {
            display: true,
            text: 'BINANCE Market Data',
        }
    }
}

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);
export default function MarketApp(){
    const [trades, setTrades] = useState([]);
    const [indicator, setIndicator] = useState("price");
    const [symbol, setSymbol] = useState("BTCUSDT");
    const [symbols, setSymbols] = useState(["BTCUSDT", "ETHBTC"]);
    const [windowSize, setWindowSize] = useState(50);
    const [connected, setConnected] = useState(false);
    const [chartData, setChartData] = useState({
        labels: [],
        datasets: [{
            label: 'BTC-USDT Price',
            fill: false,
            backgroundColor: 'rgba(75,192,192,0.4)',
            borderColor: 'rgba(75,192,192,1)',
            borderDashOffset: 0.0,
            pointBorderColor: 'rgba(75,192,192,1)',
            pointBackgroundColor: '#fff',
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: 'rgba(75,192,192,1)',
            pointHoverBorderColor: 'rgba(220,220,220,1)',
            pointHoverBorderWidth: 2,
            pointRadius: 1,
            pointHitRadius: 10,
            data: []
        }]
    });
    useEffect(() => {
        socket.on("ticker", (trade) => {
            let newTrades = [...trades, trade];
            if (newTrades.length > windowSize){
                newTrades = newTrades.slice(newTrades.length-windowSize);
            }
            setTrades(newTrades);
            const newChartData = {...chartData};
            newChartData.datasets = [...chartData.datasets];
            newChartData.datasets[0].data = [...chartData.datasets[0].data, Number(trade[indicator])]
            if (newChartData.datasets[0].data.length > windowSize){
                newChartData.datasets[0].data.splice(0,newChartData.datasets[0].data.length - windowSize);
            }
            newChartData.labels = [...chartData.labels, trade.timestamp];
            if (newChartData.labels.length > windowSize){
                newChartData.labels.splice(0,newChartData.labels.length - windowSize);
            }
            setChartData(newChartData);
        })
    })
    return (
        <Container>
            <p></p>
            <Card>
                <CardHeader title="Market">
                    <FormGroup>
                    <SelectBox value={windowSize}
                               label={"Window Size"}
                               options={[25,50,100,250]}
                               id={"windowSize"}
                               handleChange={event => setWindowSize(Number(event.target.value))}></SelectBox>
                    </FormGroup>
                    <FormGroup>
                    <SelectBox value={indicator}
                               label={"Indicator"}
                               options={["price","quantity","volume","totalVolume"]}
                               id={"indicator"}
                               handleChange={event => setIndicator(event.target.value)}></SelectBox>
                    </FormGroup>
                </CardHeader>
                <CardBody>
                    <Line data={chartData}
                          width={1080}
                          height={640}
                          options={options}></Line>
                </CardBody>
            </Card>
        </Container>
    )
}
