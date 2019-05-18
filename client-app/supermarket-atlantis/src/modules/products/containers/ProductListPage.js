import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { get_products } from '../index'

class ProductListPage extends React.Component {

    render() {
        const { list } = this.props;
        return (
            <React.Fragment> cargo la vista de productos </React.Fragment>
        )
    }
}

const mapStateToProps = (state) => {
    return ({
        list: []
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ get_products }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)


