export default function SelectBox({id, value, options, label, handleChange}) {
    return (
        <div className="form-floating">
            <select className="form-select"
                    id={id}
                    name={id}
                    value={value}
                    onChange={handleChange}>
                {
                    options.map( opt =>
                        <option key={opt} value={opt} label={opt}>{opt}</option>
                    )
                }
            </select>
            <label htmlFor={id}>{label}</label>
        </div>
    );
}
