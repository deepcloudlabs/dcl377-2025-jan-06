import React from "react";

export default function TableBody({children,values}) {
    return (
        <tbody>
        {
            values.map((row, index) => (
                <tr key={index}>
                    <td>{index + 1}</td>
                    {
                        row.map(
                            value => <td key={value}>{value}</td>
                        )
                    }
                </tr>
            ))
        }
        {children}
        </tbody>
    )
}
