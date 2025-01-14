export default function ProgressBar({max, value}) {
    let pbWidth = `${100 * value / max}%`;
    let bgColor = "bg-primary";
    if (value < (max/4)){
        bgColor = "bg-danger";
    } else if (value < (max/2)){
        bgColor = "bg-warning";
    }
    return (
        <div className="progress">
            <div className={"progress-bar ".concat(bgColor)} style={{width: pbWidth}}>{value}</div>
        </div>
    );
}
