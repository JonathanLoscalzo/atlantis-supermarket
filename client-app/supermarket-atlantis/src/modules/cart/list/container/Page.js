import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { deleteItem } from '../index'
import Presentation from '../presentation/Presentation'
import Spinner from '../../../../components/loading/spinner'

class Page extends React.Component {

    render() {
        return (
            <Spinner loading={this.props.loading}>
                <Presentation {...this.props} />
            </Spinner>
        )
    }
}

const mapStateToProps = ({ cart }) => {
    return ({
        loading: cart.list.loading,
        items: cart.list.items
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ deleteItem }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(Page)


