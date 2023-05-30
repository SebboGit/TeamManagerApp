import React from 'react';

class DownloadLink extends React.Component {
    render() {
        return (
            <a href={this.props.src} target="_blank" rel="noopener noreferrer" download>{this.props.children}</a>
        )
    }
}

export default DownloadLink;