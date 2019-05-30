import React from 'react'

export default class BodyContainer extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div className="col">
                    {this.props.children}
                </div>
            </React.Fragment>
        )
    }
}