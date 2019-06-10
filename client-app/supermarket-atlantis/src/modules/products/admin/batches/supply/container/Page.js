import React from 'react'
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { Spinner } from '../../../../../shared'
import ElementRemove from '../presentation/Presentation'
import { load, supply, goBack } from '../index';

class ElementRemovePage extends React.Component {

    componentWillMount() {
        this.props.load(this.props.match.params.id)
    }

    render() {
        return (
            <Spinner loading={this.props.loading}>
                <ElementRemove {...this.props} />
            </Spinner>
        )
    }
}

const mapStateToProps = ({ batch: element }) => ({
    element: element.supply.element,
    loading: element.supply.loading,
    error: element.supply.error,
    isOpen: element.supply.isOpen
})

const mapDispatchToProps = (dispatch) => bindActionCreators({ load, supply, goBack }, dispatch)

export default connect(mapStateToProps, mapDispatchToProps)(ElementRemovePage)