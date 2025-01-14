export default function Badge({label, value, bgColor}) {
    if(!label){
        return (
            <h4><span className={"badge ".concat(bgColor)}>{value}</span></h4>
        );
    }
    return (
        <h4>{label}:<span className={"badge ".concat(bgColor)}>{value}</span></h4>
    );
}
