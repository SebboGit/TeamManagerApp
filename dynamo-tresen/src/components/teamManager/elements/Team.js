/**
 * Element that represents a sports team within the teamTable
 * @param props
 * @returns {JSX.Element}
 * @constructor
 *
 * @author Michael Fortenbacher
 */

export const Team = (props) => {
    return (
        <>
            <tr>
                <td>{props.id}</td>
                <td>{props.name}</td>
                <td>{props.memberCount}</td>
                <td>{props.memberIds}</td>
                <td>{props.infomaterialCount}</td>
                <td>{props.infomaterialIds}</td>
            </tr>
        </>
    );
}