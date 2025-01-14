export default function ProgressBar({pbClass,pbWidth,value}){
    return (
        <div className="mb-3">
            <div className="progress">
                <div className={pbClass}
                     style={{width: pbWidth}}>**{value}**</div>
            </div>
        </div>
    )
}
