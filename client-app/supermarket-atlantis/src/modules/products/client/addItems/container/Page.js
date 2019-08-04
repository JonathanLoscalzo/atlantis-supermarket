import React from 'react'
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { Spinner } from 'modules/shared';
import AddItemsComponent from '../presentation/AddItems'
import { goBack, setProduct, submit } from '../index';
// @ts-ignore
import _ from 'lodash';

class AddItemModal extends React.Component {

    componentWillMount() {
        if (_.hasIn(this.props.location, "state.product")) {
            this.props.setProduct(this.props.location.state.product)
        } else {
            this.props.history.replace('/shopping')
        }
    }

    render() {
        return (
            <Spinner loading={this.props.loading}>
                <AddItemsComponent {...this.props} />
            </Spinner>
        )
    }
}

const mapStateToProps = ({ product: element }) => ({
    element: element.addItems.element,
    loading: element.addItems.loading,
    error: element.addItems.error,
    isOpen: element.addItems.isOpen
})

const mapDispatchToProps = (dispatch) => bindActionCreators(
    { goBack, setProduct, submit }, dispatch)

export default connect(mapStateToProps, mapDispatchToProps)(AddItemModal)