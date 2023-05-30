import React from 'react';

class PdfDownload extends React.Component {
    render() {
        return (
            <a href={this.props.src}target="_blank" rel="noopener noreferrer" download={this.props.name}> {this.props.children}</a>
        )
    }
}

export default PdfDownload;