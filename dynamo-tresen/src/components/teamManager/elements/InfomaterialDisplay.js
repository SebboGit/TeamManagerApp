import PdfDownload from "./PdfDownload";
import React from "react";


const InfomaterialDisplay = ({info}) => {
    return (info.length === 0 &&
        <p className="centerTeam">No infomaterial with the matching Id in "Infomaterial-Id" was found!</p> ||
        <>
            <table id="teamtable">
                <thead>
                <tr>
                    <th>Infomaterial-Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Url</th>
                    <th>Pdf</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>{info.id}</td>
                    <td>{info.name}</td>
                    <td>{info.description}</td>
                    <td>
                        <a href={info.url}>{info.url}</a>
                    </td>
                    <td>
                        <PdfDownload src={`data:application/pdf;base64,${info.pdf}`}>Pdf-Download</PdfDownload>
                    </td>
                </tr>
                </tbody>
            </table>
        </>
    )
}

export default InfomaterialDisplay;