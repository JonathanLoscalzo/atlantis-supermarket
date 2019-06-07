import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { action } from '../index'
import { Presentation } from '../presentation/Presentation'
import Spinner from '../../../../components/loading/spinner'

class ProductListPage extends React.Component {

    render() {
        return (
            <Spinner loading={this.props.loading}>
                <Presentation action={this.props.action} />
            </Spinner>
        )
    }
}

const mapStateToProps = (state) => {
    return ({
        loading: state.example.loading
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ action }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)


