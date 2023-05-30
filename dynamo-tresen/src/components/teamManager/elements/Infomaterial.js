/**
 * element that represents a infomaterial within the infoTable
 * @param props
 * @return {JSX.Element}
 * @constructor
 */
import PdfDownload from "./PdfDownload";

export const Infomaterial = (props) => {
    return (
        <>
            <tr>
                <td>{props.id}</td>
                <td>{props.name}</td>
                <td>{props.description}</td>
                <td>
                    <a href={props.url}>{props.url}</a>
                </td>
                <td>
                    <PdfDownload src={`data:application/pdf;base64,${props.pdf}`} name={props.name}>Download
                        "{props.name}"</PdfDownload>
                </td>
            </tr>
        </>
    );
}