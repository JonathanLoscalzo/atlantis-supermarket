import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { get_products } from '../index'

class ProductListPage extends React.Component {

    render() {
        const { list } = this.props;
        return (
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="title">Productos</h5>
                    </div>
                    <div class="card-body">
                        aloha
                    </div>
                </div>
            </div>
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


