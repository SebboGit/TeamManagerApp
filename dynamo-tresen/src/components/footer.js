import React, {Component} from 'react';
import './css/footer.css'

class Footer extends Component {

    render() {
        return (
        <p className="footer">
            Softwareengineering 2 | {this.props.group} | {this.props.semester} | {this.props.seGroup}
        </p>
        )
    }
}

export default Footer;