import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import {
    deleteItem, getPaymentMethods, submit as create,
    changeSelectable,
    changePay,
} from '../index'
import Presentation from '../presentation/Presentation'
import Spinner from '../../../../components/loading/spinner'
// @ts-ignore
import _ from 'lodash';

class Page extends React.Component {

    componentWillMount() {
        this.props.getPaymentMethods();
    }

    render() {
        return (
            <Spinner loading={this.props.loading}>
                <Presentation {...this.props} />
            </Spinner>
        )
    }
}

const mapStateToProps = ({ cart, ...state }) => {
    return ({
        loading: cart.list.loading,
        items: cart.list.items,
        element: cart.list.element,
        total: cart.list.total,
        payment: cart.list.payment,
        change: cart.list.change,
        payments: _.concat([{ value: '', label: "Seleccione..." }], ...cart.list.payments.map(x => ({ value: x.id, label: x.name })))
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({
        deleteItem, getPaymentMethods, create,
        changeSelectable,
        changePay,
    }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(Page)


