import React from "react";

// this is a stateless component
export default  function TableHeader({headerNames}) {
    return (
        <thead>
        <tr>
            {
                headerNames.split(",").map( headerName => (
                    <th key={headerName}>{headerName}</th>
                ))
            }
        </tr>
        </thead>
    );
}
