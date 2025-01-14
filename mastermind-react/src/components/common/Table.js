export default function Table({columns,rows,fields}) {
    return (
        <table className="table table-bordered table-hover table-responsive table-striped">
            <thead>
            <tr>
                {
                    columns.map(column =>
                        <th>**{column}**</th>
                    )
                }
            </tr>
            </thead>
            <tbody>
            {
                rows.map((row, i) => (
                    <tr key={i}>{
                        fields.map(field =>
                           <td>{row[field]}</td>
                        )
                    }
                    </tr>
                ))
            }
            </tbody>
        </table>)
}
