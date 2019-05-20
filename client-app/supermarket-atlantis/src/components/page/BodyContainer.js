import React from 'react'

export default class BodyContainer extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div class="panel-header panel-header-sm">
                </div>
                <div className="content">
                    <div className="row">
                        {this.props.children}
                    </div>
                </div>
            </React.Fragment>
        )
    }
}