export default function Table({children,className}) {
    return (
        <table className={className}>
            {children}
        </table>
    );
}
