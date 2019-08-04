import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { reduxForm } from 'redux-form'

import {
    getProducts,
    onPageSizeChange,
    search, enableAdvanceSearch,
    addItemsToBasket
} from '../index'

// @ts-ignore
import { Switch, Route } from 'react-router-dom'

import Form from '../presentation/Form'
import { Spinner } from '../../../../shared/index'
import ProductList from '../presentation/ProductList';
import ProductView from '../../view/container/Page'
import { validator } from '../../../../shared'
import schema from '../schema'
import AddItems from 'modules/products/client/addItems/container/Page';

const df = () => (<div></div>)

class ProductListPage extends React.Component {
    componentDidMount() {
        //this.props.get_products()
    }

    render() {

        let urls = {
            view: `${this.props.match.url}/view/:id`,
            addItems: `${this.props.match.url}/addItems`
        }

        return (
            <Spinner loading={this.props.loading}>
                <SearchFrom
                    title="Búsqueda"
                    initialValues={this.props.element}
                    enableAdvanceSearch={this.props.enableAdvanceSearch}
                    onSubmit={(values) => { this.props.search(values); }}
                />
                <hr />
                <Switch>
                    <Route path={urls.view} component={ProductView} />
                    <Route path={urls.addItems} component={AddItems} />
                    <ProductList
                        fetchData={this.props.getProducts}
                        urls={urls}
                        {...this.props} />
                </Switch>
            </Spinner>
        )
    }
}

const mapStateToProps = ({ product: element }) => {
    return ({
        element: element.search.formSearch,
        loading: element.search.loading,
        defaultPageSize: element.search.defaultPageSize,
        data: element.search.data.rows,
        pages: element.search.data.pages,
        data_loading: element.search.data.loading,
    })
}

const SearchFrom = reduxForm({
    form: 'products/search',  // a unique identifier for this form
    validate: validator(schema),
    enableReinitialize: true
})(Form)

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({
        getProducts,
        onPageSizeChange,
        search,
        enableAdvanceSearch,
        addItemsToBasket
    }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)


