import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { get_products } from '../index'
import { Switch, Route } from 'react-router-dom'

import Spinner from '../../../../../components/loading/spinner'
import ProductList from '../presentational/ProductList';
import { push, go, replace, goBack, goForward } from 'connected-react-router'

const df = () => (<div></div>)

class ProductListPage extends React.Component {
    componentDidMount() {
        this.props.get_products()
    }

    render() {

        let urls = {
            new: `${this.props.match.url}/new`,
            edit: `${this.props.match.url}/edit/:id`,
            remove: `${this.props.match.url}/remove/:id`,
            view: `${this.props.match.url}/view/:id`
        }

        return (
            <Spinner loading={this.props.loading}>
                <Switch>
                    <Route path={urls.new} component={df} />
                    <Route path={urls.edit} component={df} />
                    <ProductList {...this.props} urls={urls} />
                </Switch>
                <Route path={urls.remove} component={df} />
                <Route path={urls.view} component={df} />
            </Spinner>
        )
    }
}

const mapStateToProps = (state) => {
    return ({
        products: state.product.list.products
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ get_products, push }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)


