import React from "react";

export default function TableBody({children,values,attributes,keyAttribute}) {
    if (!values){
        return <tbody>{children}</tbody>;
    }
    return (
        <tbody>
        {
            values.map((value, index) => (
                <tr key={value[keyAttribute] + index.toString()}>
                    <td>{index + 1}</td>
                    {
                        attributes.split(",").map(
                            (attribute,index) => <td key={index}>{value[attribute]}</td>
                        )
                    }
                </tr>
            ))
        }
        {children}
        </tbody>
    )
}
